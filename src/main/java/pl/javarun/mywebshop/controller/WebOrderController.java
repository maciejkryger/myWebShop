package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderItemNotExistException;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.model.*;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 04.04.2020 12:28
 * *
 * @className: OrderController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/basket")
public class WebOrderController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final ProductService productService;


    public WebOrderController(UserService userService, TypeService typeService, CompanyService companyService,
                              RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                              ProductService productService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.webOrderService = webOrderService;
        this.webOrderItemService = webOrderItemService;
        this.productService = productService;
    }

    @GetMapping()
    public ModelAndView showBasket(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("basket");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        try{
            int sumQuantity = calculateActualQuantityInUserBasket(userId);
            int sumToPay = calculateActualSumToPayInUserBasket(userId);
            modelAndView.addObject("productsInBasket", webOrderItemService.getOrderItemOrderId(webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId()));
            modelAndView.addObject("sumQuantity", sumQuantity);
            modelAndView.addObject("sumToPay", sumToPay);
        }catch (OrderNotExistException ex){
            modelAndView.addObject("productsInBasket", "");
            modelAndView.addObject("sumQuantity", "");
            modelAndView.addObject("sumToPay", "");
        }
        return modelAndView;
    }


    @PostMapping("/addFromList")
    public String addToBasketFromList(@RequestParam(required = false) Integer productId, HttpServletRequest httpServletRequest) {
        addProductToBasket(httpServletRequest, productId);
        String typeName = productService.getProductById(productId).getType().getName();
        return "redirect:/types/" + typeName + "#products";
    }


    @PostMapping("/addFromDetail")
    public String addToBasketFromDetail(@RequestParam(required = false) Integer productId,
                                        HttpServletRequest httpServletRequest) {
        addProductToBasket(httpServletRequest, productId);
        return "redirect:/details/" + productId;
    }

    @PostMapping("/addFromBasket")
    public String addToBasketFromBasket(@RequestParam(required = false) Integer productId,
                                        HttpServletRequest httpServletRequest) {
        addProductToBasket(httpServletRequest, productId);
        return "redirect:/basket/";
    }

    @PostMapping("/addFromWishList")
    public String addToBasketFromWishList(@RequestParam(required = false) Integer productId,
                                        HttpServletRequest httpServletRequest) {
        addProductToBasket(httpServletRequest, productId);
        return "redirect:/wishList/";
    }


    @PostMapping("/removeFromBasket")
    public String removeProductToWishListFromWishList(@RequestParam(required = false) Integer productId,
                                                      HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int ordersId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
        WebOrderItem webOrderItem = webOrderItemService.getOrderItemOrderIdAndProductId(ordersId, productId);
        if (webOrderItem.getQuantity() == 1) {
            webOrderItemService.delete(webOrderItem);
        } else {
            int quantity = webOrderItem.getQuantity();
            quantity--;
            webOrderItem.setQuantity(quantity);
            webOrderItemService.save(webOrderItem);
        }
        return "redirect:/basket";
    }

    @PostMapping("/removeAllQuantityFromBasket")
    public String removeAllProductToWishListFromWishList(@RequestParam(required = false) Integer productId,
                                                         HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int ordersId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
        WebOrderItem webOrderItem = webOrderItemService.getOrderItemOrderIdAndProductId(ordersId, productId);
        webOrderItemService.delete(webOrderItem);
        return "redirect:/basket";
    }

    private int calculateActualQuantityInUserBasket(int userId) {
        int result = 0;
        List<WebOrderItem> items = webOrderItemService.getOrderItemOrderId(webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId());
        for (int i = 0; i < items.size(); i++) {
            result += items.get(i).getQuantity();
        }
        return result;
    }

    private int calculateActualSumToPayInUserBasket(int userId) {
        int result = 0;
        List<WebOrderItem> items = webOrderItemService.getOrderItemOrderId(webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId());
        for (int i = 0; i < items.size(); i++) {
            result += (items.get(i).getQuantity() * items.get(i).getProductPrice());
        }
        return result;
    }

    private void addProductToBasket(HttpServletRequest httpServletRequest, Integer productId) {
        System.out.println("step1");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        WebOrder webOrder;
        WebOrderItem webOrderItem;
        System.out.println("productId: "+productId);
        Product product = productService.getProductById(productId);
        System.out.println("step2");
        try {
            webOrder = webOrderService.getOrderByUserIdAndConfirmedFalse(userId);
        } catch (OrderNotExistException ex) {
            webOrder = new WebOrder();
            webOrder.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
            webOrder.setUser(user);
            System.out.println("dodaje orders...");
            webOrderService.save(webOrder);
            System.out.println("koniec dodawania orders");
        }
        webOrder = webOrderService.getOrderByUserIdAndConfirmedFalse(userId);
        int orderId = webOrder.getId();
        System.out.println("step3 - numer orders="+orderId);
        try {
            System.out.println("step4");
            webOrderItem = webOrderItemService.getOrderItemOrderIdAndProductId(orderId, productId);
            System.out.println("step5");
            int quantity = webOrderItem.getQuantity();
            quantity++;
            webOrderItem.setQuantity(quantity);
            webOrderItem.setProductPrice(product.getPrice());
            System.out.println("koniec try");
        } catch (OrderItemNotExistException ex) {
            System.out.println("step6");
            webOrderItem = new WebOrderItem();
            System.out.println("step7");
            webOrderItem.setWebOrder(webOrder);
            System.out.println("step8");
            webOrderItem.setProduct(product);
            System.out.println("step9");
            webOrderItem.setProductPrice(product.getPrice());
            System.out.println("step10");
            webOrderItem.setQuantity(1);


        }
        System.out.println("step11 - zapisywanie ordersItem");
        webOrderItemService.save(webOrderItem);
        System.out.println("koniec");
    }

}
