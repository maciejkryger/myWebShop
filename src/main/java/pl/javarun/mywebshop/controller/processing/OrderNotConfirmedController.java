package pl.javarun.mywebshop.controller.processing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.model.WebOrderItem;
import pl.javarun.mywebshop.service.*;

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
@RequestMapping("/orderCenter/notConfirmed")
public class OrderNotConfirmedController {

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


    public OrderNotConfirmedController(UserService userService, TypeService typeService, CompanyService companyService,
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
    public ModelAndView showNotConfirmed(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("processing/orderNotConfirmed");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("notConfirmedOrders",webOrderService.getAllConfirmedFalse());
        modelAndView.addObject("ordersValue", getOrdersValueConfirmedFalse());
        modelAndView.addObject("ordersQuantity",getOrdersQuantityConfirmedFalse());
        return modelAndView;
    }

    private int getOrdersValueConfirmedFalse(){
        int result=0;
        List<WebOrder> orders = webOrderService.getAllConfirmedFalse();
        List<WebOrderItem> items = webOrderItemService.getAllOrders();
        for (WebOrder order: orders) {
            for (WebOrderItem item: items){
                if(order.getId()==item.getWebOrder().getId()){
                    result+=item.getProductPrice();
                }
            }
        }
        return result;
    }

    private int getOrdersQuantityConfirmedFalse(){
        int result=0;
        List<WebOrder> orders = webOrderService.getAllConfirmedFalse();
        List<WebOrderItem> items = webOrderItemService.getAllOrders();
        for (WebOrder order: orders) {
            for (WebOrderItem item: items){
                if(order.getId()==item.getWebOrder().getId()){
                    result+=item.getQuantity();
                }
            }
        }
        return result;
    }


}
