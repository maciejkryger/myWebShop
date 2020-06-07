package pl.javarun.mywebshop.controller.shopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.*;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 17.05.2020 09:43
 * *
 * @className: PaymentController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final ProductService productService;
    private final DeliveryOptionService deliveryOptionService;
    private final PaymentTypeService paymentTypeService;
    private final PaymentMethodService paymentMethodService;
    private final WishListService wishListService;


    public PaymentController(UserService userService, TypeService typeService, CompanyService companyService,
                             RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                             ProductService productService, DeliveryOptionService deliveryOptionService,
                             PaymentTypeService paymentTypeService, PaymentMethodService paymentMethodService,
                             WishListService wishListService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.webOrderService = webOrderService;
        this.webOrderItemService = webOrderItemService;
        this.productService = productService;
        this.deliveryOptionService = deliveryOptionService;
        this.paymentTypeService = paymentTypeService;
        this.paymentMethodService = paymentMethodService;
        this.wishListService = wishListService;
    }

    @GetMapping()
    public ModelAndView showPaymentInBasket(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("shopping/payment");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int webOrderId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
        int sumQuantity = webOrderItemService.calculateActualQuantityInUserBasket(webOrderId);
        double sumToPay = webOrderItemService.calculateActualSumToPayInUserBasket(webOrderId);
        int deliveryCostsToPay = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getDeliveryOption().getPrice();
        modelAndView.addObject("paymentMethods", paymentMethodService.getByPaymentTypeId(1));
        modelAndView.addObject("sumQuantity", sumQuantity);
        modelAndView.addObject("sumToPay", sumToPay);
        modelAndView.addObject("deliveryCostsToPay", deliveryCostsToPay);
        modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderId));
        try {
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(user.getId()).size());
        } catch (WishListNotExistException ex) {
            modelAndView.addObject("userWishListSize", 0);
        }
        return modelAndView;
    }

    @PostMapping
    public String savePaymentOption(HttpServletRequest httpServletRequest, @RequestParam int paymentMethodId) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        PaymentMethod paymentMethod = paymentMethodService.getById(paymentMethodId);
        WebOrder order = webOrderService.getOrderByUserIdAndConfirmedFalse(userId);
        order.setPaymentMethod(paymentMethod);
        webOrderService.save(order);
        //with transport
        if (order.getDeliveryOption().getPaymentType() == paymentTypeService.getById(1) && order.getDeliveryOption().getId() != 4) {
            return "redirect:/address";
        }
        //without transport - OWL(self-pickup)
        return "redirect:/confirmation";
    }
}
