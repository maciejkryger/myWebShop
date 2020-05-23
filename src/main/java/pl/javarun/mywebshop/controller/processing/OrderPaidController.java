package pl.javarun.mywebshop.controller.processing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.service.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 21.05.2020 23:55
 * *
 * @className: OrderPaidController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/orderCenter/paid")
public class OrderPaidController {

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


    public OrderPaidController(UserService userService, TypeService typeService, CompanyService companyService,
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
    public ModelAndView showPaid(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("processing/orderPaid");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("ordersPaid",webOrderService.getAllPaidTrueCompletedFalse());

        return modelAndView;
    }

    @PostMapping
    public String completeOrder(@RequestParam int id, @RequestParam boolean completed,
                                @RequestParam(required = false) String shipmentNumber, @RequestParam(required = false) String shipmentDate) {
        WebOrder order = webOrderService.getOrderById(id);
        order.setCompleted(completed);
        order.setCompleteDate(Timestamp.valueOf(LocalDateTime.now()));
        if(shipmentNumber !=null && !shipmentNumber.isEmpty()){
            order.setShipmentNumber(shipmentNumber);
            order.setShipmentDate(LocalDate.parse(shipmentDate));
        }
        webOrderService.save(order);
        return "redirect:/orderCenter/paid";
    }




}
