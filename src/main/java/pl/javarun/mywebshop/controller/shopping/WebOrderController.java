package pl.javarun.mywebshop.controller.shopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderItemNotExistException;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
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
    private final WishListService wishListService;


    public WebOrderController(UserService userService, TypeService typeService, CompanyService companyService,
                              RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                              ProductService productService, WishListService wishListService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.webOrderService = webOrderService;
        this.webOrderItemService = webOrderItemService;
        this.productService = productService;
        this.wishListService = wishListService;
    }

    @GetMapping()
    public ModelAndView showBasket(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("shopping/basket");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        try {
            int webOrderId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
            modelAndView.addObject("productsInBasket", webOrderItemService.getOrderItemByOrderId(webOrderId));
            modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderId));
            int sumQuantity = webOrderItemService.calculateActualQuantityInUserBasket(webOrderId);
            int sumToPay = webOrderItemService.calculateActualSumToPayInUserBasket(webOrderId);
            modelAndView.addObject("sumQuantity", sumQuantity);
            modelAndView.addObject("sumToPay", sumToPay);
            } catch (OrderNotExistException ex) {
            modelAndView.addObject("productsInBasketSize", 0);
            modelAndView.addObject("productsInBasket", "");
            modelAndView.addObject("sumQuantity", "");
            modelAndView.addObject("sumToPay", "");
          }
        try {
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(user.getId()).size());
        }catch (WishListNotExistException ex){
            modelAndView.addObject("userWishListSize", 0);
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


    private void addProductToBasket(HttpServletRequest httpServletRequest, Integer productId) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        WebOrder webOrder;
        WebOrderItem webOrderItem;
        Product product = productService.getProductById(productId);
        try {
            webOrder = webOrderService.getOrderByUserIdAndConfirmedFalse(userId);
        } catch (OrderNotExistException ex) {
            webOrder = new WebOrder();
            webOrder.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
            webOrder.setUser(user);
            webOrderService.save(webOrder);
        }
        webOrder = webOrderService.getOrderByUserIdAndConfirmedFalse(userId);
        int orderId = webOrder.getId();
        try {
            webOrderItem = webOrderItemService.getOrderItemOrderIdAndProductId(orderId, productId);
            int quantity = webOrderItem.getQuantity();
            quantity++;
            webOrderItem.setQuantity(quantity);
            webOrderItem.setProductPrice(product.getPrice());
        } catch (OrderItemNotExistException ex) {
            webOrderItem = new WebOrderItem();
            webOrderItem.setWebOrder(webOrder);
            webOrderItem.setProduct(product);
            webOrderItem.setProductPrice(product.getPrice());
            webOrderItem.setQuantity(1);


        }
        webOrderItemService.save(webOrderItem);
    }

}