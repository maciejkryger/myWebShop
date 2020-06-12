package pl.javarun.mywebshop.controller.shopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderItemNotExistException;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.PromoCodeNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.*;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 04.04.2020 12:28
 * *
 * @className: WebOrderController
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
    private final PromoCodeService promoCodeService;
    private final ProductCounterService productCounterService;


    public WebOrderController(UserService userService, TypeService typeService, CompanyService companyService,
                              RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                              ProductService productService, WishListService wishListService, PromoCodeService promoCodeService,
                              ProductCounterService productCounterService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.webOrderService = webOrderService;
        this.webOrderItemService = webOrderItemService;
        this.productService = productService;
        this.wishListService = wishListService;
        this.promoCodeService = promoCodeService;
        this.productCounterService= productCounterService;
    }

    @GetMapping()
    public ModelAndView showBasket(HttpServletRequest httpServletRequest, @PathParam("wrongCode") boolean wrongCode,
                                   @PathParam("codeIsNotActive") boolean codeIsNotActive) {
        ModelAndView modelAndView = new ModelAndView("shopping/basket");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if (wrongCode) modelAndView.addObject("wrongCode", true);
        if (codeIsNotActive) modelAndView.addObject("codeIsNotActive", true);
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        try {
            int webOrderId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
            List<WebOrderItem> webOrderItemList = webOrderItemService.getOrderItemByOrderId(webOrderId);
            modelAndView.addObject("productsInBasket", webOrderItemList);
            modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderId));
            int sumQuantity = webOrderItemService.calculateActualQuantityInUserBasket(webOrderId);
            double sumToPay = webOrderItemService.calculateActualSumToPayInUserBasket(webOrderId);
            if (webOrderItemList.size() != 0) {
                int discountCounter = 0;
                int discountSum = 0;
                for (WebOrderItem item : webOrderItemList) {
                    if (item.getDiscount() > 0) {
                        discountSum += item.getDiscount();
                        discountCounter++;
                    }
                }
                if (webOrderItemList.size() == discountCounter && discountSum / webOrderItemList.size() == webOrderItemList.get(0).getDiscount()) {
                    modelAndView.addObject("discount", webOrderItemList.get(0).getDiscount());
                }
            }
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            modelAndView.addObject("sumToPay", sumToPay);
            modelAndView.addObject("sumQuantity", sumQuantity);
        } catch (OrderNotExistException ex) {
            modelAndView.addObject("productsInBasketSize", 0);
            modelAndView.addObject("productsInBasket", "");
            modelAndView.addObject("sumQuantity", "");
            modelAndView.addObject("sumToPay", "");
        }
        try {
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(user.getId()).size());
        } catch (WishListNotExistException ex) {
            modelAndView.addObject("userWishListSize", 0);
        }
        return modelAndView;
    }

    @PostMapping("/discount")
    public String addDiscount(HttpServletRequest httpServletRequest,
                              @RequestParam(required = false) String discountCode) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        PromoCode promoCode;
        try {
            promoCode = promoCodeService.getPromoCodeByPromoCode(discountCode);
        } catch (PromoCodeNotExistException ex) {
            return "redirect:/basket?wrongCode=true";
        }
        if (!promoCode.isActive()) {
            return "redirect:/basket?codeIsNotActive=true";
        }
        int webOrderId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
        for (WebOrderItem item : webOrderItemService.getOrderItemByOrderId(webOrderId)) {
            if (item.getDiscount() < promoCode.getDiscount()) {
                item.setDiscount(promoCode.getDiscount());
                webOrderItemService.save(item);
            }
        }
        return "redirect:/basket";
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
        if (product.getDiscount() > 0) {
            webOrderItem.setDiscount(product.getDiscount());
        }
//        countBasketStatistic(productId);
        webOrderItemService.save(webOrderItem);
    }

    private void countBasketStatistic(int id){
        ProductCounter productCounter;
        try {
            productCounter = productCounterService.getByProductId(id);
            if((Integer)productCounter.getBasket()==null){
                productCounter.setBasket(1);
            }else {
                int basketCounter = productCounter.getBasket();
                basketCounter++;
                productCounter.setBasket(basketCounter);
            }
        }catch (Exception ex){
            productCounter = new ProductCounter();
            productCounter.setProduct(productService.getProductById(id));
            productCounter.setBasket(1);
        }
        productCounterService.save(productCounter);
    }
}
