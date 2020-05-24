package pl.javarun.mywebshop.controller.processing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.AddressNotExistException;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.model.WebOrderItem;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.05.2020 19:45
 * *
 * @className: OrderItemsController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/orderCenter/orderItems")
public class OrderItemsController {

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


    public OrderItemsController(UserService userService, TypeService typeService, CompanyService companyService,
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

    @GetMapping("/{id}")
    public ModelAndView inputAddress(HttpServletRequest httpServletRequest, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("processing/orderItems");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("orderItems",webOrderItemService.getOrderItemByOrderId(id));
        modelAndView.addObject("sumToPay", calculateActualSumToPayByOrderId(id));
        modelAndView.addObject("sumQuantity", calculateActualQuantityOrderId(id));
        WebOrder webOrder = webOrderService.getOrderById(id);
        modelAndView.addObject("webOrder",webOrder);
        try{
            modelAndView.addObject("address", addressService.getByUser(webOrder.getUser()));
        }catch (AddressNotExistException ignored){}
        return modelAndView;
    }

    private int calculateActualSumToPayByOrderId(int id) {
        int result = 0;
        List<WebOrderItem> items = webOrderItemService.getOrderItemByOrderId(id);
        for (WebOrderItem item : items) {
            result += (item.getQuantity() * item.getProductPrice());
        }
        return result;
    }

    private int calculateActualQuantityOrderId(int id) {
        int result = 0;
        List<WebOrderItem> items = webOrderItemService.getOrderItemByOrderId(id);
        for (WebOrderItem item : items) {
            result += item.getQuantity();
        }
        return result;
    }
}
