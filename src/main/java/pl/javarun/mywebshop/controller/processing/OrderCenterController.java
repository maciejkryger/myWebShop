package pl.javarun.mywebshop.controller.processing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.EmailOrderChangeStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 21.05.2020 19:45
 * *
 * @className: OrderCenterController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/orderCenter")
public class OrderCenterController {

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
    private final StatusService statusService;
    private final EmailOrderChangeStatus emailOrderChangeStatus;


    public OrderCenterController(UserService userService, TypeService typeService, CompanyService companyService,
                                 RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                                 ProductService productService, DeliveryOptionService deliveryOptionService,
                                 PaymentTypeService paymentTypeService, AddressService addressService,StatusService statusService,
                                 EmailOrderChangeStatus emailOrderChangeStatus) {
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
        this.statusService=statusService;
        this.emailOrderChangeStatus=emailOrderChangeStatus;
    }

    @GetMapping()
    public ModelAndView inputAddress(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("processing/orderCenter");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("notConfirmedOrdersSize", webOrderService.getAllConfirmedFalse().size());
        modelAndView.addObject("ordersNewSize", webOrderService.getAllConfirmedTrueNotAccepted().size());
        modelAndView.addObject("ordersAcceptedSize", webOrderService.getAllAcceptedTruePaidFalsePrepayment().size());
        modelAndView.addObject("ordersReadySize", webOrderService.getAllReady().size());
        modelAndView.addObject("ordersSentSize", webOrderService.getAllSentOrReadyToSelfPickUpOrders().size());
        modelAndView.addObject("ordersDeliveredSize", webOrderService.getAllCompletedAndDeliveryDateNotNull().size());
//        updateStatusInAllOrders();
        return modelAndView;
    }

    private void updateStatusInAllOrders() {
        List<WebOrder> orders = webOrderService.getAllOrders();
        for (WebOrder order : orders) {
            webOrderService.updateStatus(order, statusService, emailOrderChangeStatus);
        }
    }


}
