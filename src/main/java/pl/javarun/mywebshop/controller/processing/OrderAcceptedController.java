package pl.javarun.mywebshop.controller.processing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 21.05.2020 21:45
 * *
 * @className: OrderAcceptedController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/orderCenter/accepted")
public class OrderAcceptedController {

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


    public OrderAcceptedController(UserService userService, TypeService typeService, CompanyService companyService,
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
    public ModelAndView showAcceptedOrders(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("processing/orderAccepted");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("ordersAccepted",webOrderService.getAllAcceptedTruePaidFalsePrepayment());
        return modelAndView;
    }

    @PostMapping
    public String checkPayment(@RequestParam int id, @RequestParam int paymentAmount, @RequestParam String paymentDate,
                               @RequestParam boolean paid){
        System.out.println("data: "+paymentDate);
        WebOrder order = webOrderService.getOrderById(id);
        order.setPaid(paid);
        order.setPaymentAmount(paymentAmount);
        order.setPaymentDate(LocalDate.parse(paymentDate));
        webOrderService.save(order);
        return "redirect:/orderCenter/accepted";
    }

}
