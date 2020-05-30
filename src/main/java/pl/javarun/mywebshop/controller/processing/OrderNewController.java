package pl.javarun.mywebshop.controller.processing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.model.WebOrderItem;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.EmailOrderChangeStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 21.05.2020 21:45
 * *
 * @className: OrderAcceptedController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/orderCenter/new")
public class OrderNewController {

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


    public OrderNewController(UserService userService, TypeService typeService, CompanyService companyService,
                              RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                              ProductService productService, DeliveryOptionService deliveryOptionService,
                              PaymentTypeService paymentTypeService, AddressService addressService,
                              EmailOrderChangeStatus emailOrderChangeStatus,StatusService statusService) {
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
        this.emailOrderChangeStatus=emailOrderChangeStatus;
        this.statusService=statusService;
    }

    @GetMapping()
    public ModelAndView showNewOrders() {
        ModelAndView modelAndView = new ModelAndView("processing/orderNew");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("ordersNew",webOrderService.getAllConfirmedTrueNotAccepted());
        return modelAndView;
    }


    @PostMapping()
    public String acceptNewOrder(@RequestParam(required = false) int id,@RequestParam(required = false) boolean accepted,
                                 HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        WebOrder order = webOrderService.getOrderById(id);
        List<WebOrderItem> orderItems = webOrderItemService.getOrderItemByOrderId(id);
        order.setAccepted(accepted);
        order.setAcceptDate(Timestamp.valueOf(LocalDateTime.now()));
        order.setAcceptUser(user);
        webOrderService.save(order);
        webOrderService.updateStatus(order, statusService , emailOrderChangeStatus);
        return "redirect:/orderCenter/new";
    }

}
