package pl.javarun.mywebshop.controller.processing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.05.2020 23:28
 * *
 * @className: AddressController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/orderCenter/address")
public class OrderAddressController {

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

    public OrderAddressController(UserService userService, TypeService typeService, CompanyService companyService,
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

    @PostMapping()
    public ModelAndView showPaid(@RequestParam int id) {
        User user = webOrderService.getOrderById(id).getUser();
        ModelAndView modelAndView = new ModelAndView("processing/orderDeliveryAddress");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("address",addressService.getByUser(user));
        return modelAndView;
    }



}
