package pl.javarun.mywebshop.controller.processing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.EmailOrderChangeStatus;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 21.05.2020 23:45
 * *
 * @className: OrderSentController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/orderCenter/sent")
public class OrderSentController {

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



    public OrderSentController(UserService userService, TypeService typeService, CompanyService companyService,
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
    public ModelAndView showSentOrders(HttpServletRequest httpServletRequest, @PathParam("orderId") Integer orderId) {
        ModelAndView modelAndView = new ModelAndView("processing/orderSent");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("ordersSent", webOrderService.getAllSentOrReadyToSelfPickUpOrders());
        if (orderId != null) {
            WebOrder order=webOrderService.getOrderById(orderId);
            int paymentAmount = webOrderItemService.calculateActualSumToPayInUserBasket(orderId)+order.getDeliveryOption().getPrice();
            modelAndView.addObject("paymentAmount", paymentAmount);
            modelAndView.addObject("orderId",orderId);
        }
        return modelAndView;
    }

    @PostMapping()
    public String confirmDeliveryDate(@RequestParam int id, @RequestParam(required = false) String deliveryDate,
                                      @RequestParam(required = false) Integer paymentAmount) {
        WebOrder order = webOrderService.getOrderById(id);
        if(paymentAmount==null && deliveryDate==null){
            return "redirect:/orderCenter/sent?orderId="+id;
        }
        if (paymentAmount != null) {
            order.setDeliveryDate(LocalDate.parse(deliveryDate));
            order.setPaymentAmount(paymentAmount);
            order.setPaid(true);
        }
        if(paymentAmount==null && deliveryDate!=null){
            order.setDeliveryDate(LocalDate.parse(deliveryDate));
        }
        webOrderService.save(order);
        webOrderService.updateStatus(order, statusService , emailOrderChangeStatus);
        return "redirect:/orderCenter/sent";
    }


}
