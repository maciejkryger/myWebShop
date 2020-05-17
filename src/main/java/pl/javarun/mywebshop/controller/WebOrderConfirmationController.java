package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.AddressNotExistException;
import pl.javarun.mywebshop.model.Address;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.model.WebOrderItem;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.05.2020 22:47
 * *
 * @className: WebOrderConfirmationController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/confirmation")
public class WebOrderConfirmationController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final ProductService productService;
    private final DeliveryOptionService deliveryOptionService;
    private final PaymentTypeService paymentTypeService;
    private final AddressService addressService;


    public WebOrderConfirmationController(UserService userService, TypeService typeService, CompanyService companyService,
                                          RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                                          ProductService productService, DeliveryOptionService deliveryOptionService,
                                          PaymentTypeService paymentTypeService, AddressService addressService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.webOrderService = webOrderService;
        this.webOrderItemService = webOrderItemService;
        this.productService = productService;
        this.deliveryOptionService = deliveryOptionService;
        this.paymentTypeService = paymentTypeService;
        this.addressService = addressService;
    }

    @GetMapping()
    public ModelAndView inputAddress(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("confirmation");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int sumToPay = calculateActualSumToPayInUserBasket(userId);
        int sumQuantity = calculateActualQuantityInUserBasket(userId);
        int deliveryCostsToPay = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getDeliveryOption().getPrice();
        WebOrder webOrder = webOrderService.getOrderByUserIdAndConfirmedFalse(userId);
        modelAndView.addObject("sumToPay", sumToPay);
        modelAndView.addObject("sumQuantity", sumQuantity);
        modelAndView.addObject("deliveryCostsToPay", deliveryCostsToPay);
        modelAndView.addObject("address", addressService.getByUser(user));
        modelAndView.addObject("productsInBasket", webOrderItemService.getOrderItemOrderId(webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId()));
        modelAndView.addObject("webOrder",webOrder);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView saveDeliveryOption(HttpServletRequest httpServletRequest, @RequestParam int orderId) {
        ModelAndView modelAndView = new ModelAndView("confirmationWithOrderNumber");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        WebOrder webOrder = webOrderService.getOrderById(orderId);
        webOrder.setConfirmed(true);
        webOrder.setConfirmedDate(Timestamp.valueOf(LocalDateTime.now()));
        webOrder.setOrderNumber(webOrder.getId()+"/"+LocalDateTime.now().getMonthValue()+"/"+LocalDateTime.now().getYear());
        webOrderService.save(webOrder);
        modelAndView.addObject("orderNumber",webOrder.getOrderNumber());
        return modelAndView;
    }


    private int calculateActualSumToPayInUserBasket(int userId) {
        int result = 0;
        List<WebOrderItem> items = webOrderItemService.getOrderItemOrderId(webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId());
        for (int i = 0; i < items.size(); i++) {
            result += (items.get(i).getQuantity() * items.get(i).getProductPrice());
        }
        return result;
    }

    private int calculateActualQuantityInUserBasket(int userId) {
        int result = 0;
        List<WebOrderItem> items = webOrderItemService.getOrderItemOrderId(webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId());
        for (int i = 0; i < items.size(); i++) {
            result += items.get(i).getQuantity();
        }
        return result;
    }

}
