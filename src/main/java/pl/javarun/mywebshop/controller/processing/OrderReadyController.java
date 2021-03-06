package pl.javarun.mywebshop.controller.processing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.EmailOrderChangeStatus;

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
public class OrderReadyController {

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
    private final EmailOrderChangeStatus emailOrderChangeStatus;
    private final StatusService statusService;


    public OrderReadyController(UserService userService, TypeService typeService, CompanyService companyService,
                                RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                                ProductService productService, DeliveryOptionService deliveryOptionService,
                                PaymentTypeService paymentTypeService, AddressService addressService,
                                EmailOrderChangeStatus emailOrderChangeStatus, StatusService statusService) {
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
        this.emailOrderChangeStatus = emailOrderChangeStatus;
        this.statusService = statusService;
    }

    @GetMapping({"", "/{id}"})
    public ModelAndView showPaid(@PathVariable(required = false) Integer id) {
        ModelAndView modelAndView = new ModelAndView("processing/orderReady");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("ordersReady", webOrderService.getAllReady());
        if (id != null) {
            User user = webOrderService.getOrderById(id).getUser();
            modelAndView.addObject("address", addressService.getByUser(user));
        }
        return modelAndView;
    }

    @PostMapping
    public String completeOrder(@RequestParam int id, @RequestParam boolean completed, @RequestParam boolean isItToSend,
                                @RequestParam(required = false) String shipmentNumber, @RequestParam(required = false) String shipmentDate) {
        WebOrder order = webOrderService.getOrderById(id);
        if(!isItToSend) {
            order.setCompleted(completed);
            order.setCompleteDate(Timestamp.valueOf(LocalDateTime.now()));
        }
        if (shipmentNumber != null && !shipmentNumber.isEmpty()) {
            order.setCompleted(completed);
            order.setCompleteDate(Timestamp.valueOf(LocalDateTime.now()));
            order.setShipmentNumber(shipmentNumber);
            order.setShipmentDate(LocalDate.parse(shipmentDate));
        }
        webOrderService.save(order);
        webOrderService.updateStatus(order, statusService, emailOrderChangeStatus);
        return "redirect:/orderCenter/paid";
    }


}
