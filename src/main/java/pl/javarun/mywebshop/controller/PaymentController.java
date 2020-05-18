package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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


    public PaymentController(UserService userService, TypeService typeService, CompanyService companyService,
                             RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                             ProductService productService, DeliveryOptionService deliveryOptionService,
                             PaymentTypeService paymentTypeService, PaymentMethodService paymentMethodService) {
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
    }

    @GetMapping()
    public ModelAndView showDeliveryInBasket(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("payment");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int sumQuantity = calculateActualQuantityInUserBasket(userId);
        int sumToPay = calculateActualSumToPayInUserBasket(userId);
        int deliveryCostsToPay = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getDeliveryOption().getPrice();
        modelAndView.addObject("paymentMethods", paymentMethodService.getByPaymentTypeId(1));
        modelAndView.addObject("sumQuantity", sumQuantity);
        modelAndView.addObject("sumToPay", sumToPay);
        modelAndView.addObject("deliveryCostsToPay", deliveryCostsToPay);
        return modelAndView;
    }

    @PostMapping
    public String saveDeliveryOption(HttpServletRequest httpServletRequest, @RequestParam int paymentMethodId) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        PaymentMethod paymentMethod = paymentMethodService.getById(paymentMethodId);
        WebOrder order = webOrderService.getOrderByUserIdAndConfirmedFalse(userId);
        order.setPaymentMethod(paymentMethod);
        webOrderService.save(order);
        if (order.getDeliveryOption().getPaymentType() == paymentTypeService.getById(1)) {
            return "redirect:/address";
        }
        return "redirect:/confirmation";
    }


    private int calculateActualQuantityInUserBasket(int userId) {
        int result = 0;
        List<WebOrderItem> items = webOrderItemService.getOrderItemOrderId(webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId());
        for (WebOrderItem item : items) {
            result += item.getQuantity();
        }
        return result;
    }

    private int calculateActualSumToPayInUserBasket(int userId) {
        int result = 0;
        List<WebOrderItem> items = webOrderItemService.getOrderItemOrderId(webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId());
        for (WebOrderItem item : items) {
            result += (item.getQuantity() * item.getProductPrice());
        }
        return result;
    }

}