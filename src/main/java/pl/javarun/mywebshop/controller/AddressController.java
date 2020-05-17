package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.AddressNotExistException;
import pl.javarun.mywebshop.model.*;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.05.2020 20:33
 * *
 * @className: AddressController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/address")
public class AddressController {

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


    public AddressController(UserService userService, TypeService typeService, CompanyService companyService,
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
        ModelAndView modelAndView = new ModelAndView("addressOrderInput");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int sumToPay = calculateActualSumToPayInUserBasket(userId);
        int deliveryCostsToPay = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getDeliveryOption().getPrice();
        modelAndView.addObject("sumToPay", sumToPay);
        modelAndView.addObject("deliveryCostsToPay", deliveryCostsToPay);
        try{
            Address address= addressService.getByUser(user);
            modelAndView.addObject("address",address);
        }catch (AddressNotExistException ex){}

        return modelAndView;
    }

    @PostMapping
    public String saveDeliveryOption(HttpServletRequest httpServletRequest, @RequestParam String street,
                                     @RequestParam String houseNo, @RequestParam(required = false) String flatNo,
                                     @RequestParam String postCode, @RequestParam String city) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        Address address;
        try {
            address = addressService.getByUser(user);
        } catch (AddressNotExistException ex) {
            address = new Address();
        }
        address.setUser(user);
        address.setStreet(street);
        address.setHouseNo(houseNo);
        address.setFlatNo(flatNo);
        address.setPostCode(postCode);
        address.setCity(city);
        addressService.save(address);
        return "redirect:/confirmation";
    }


    private int calculateActualSumToPayInUserBasket(int userId) {
        int result = 0;
        List<WebOrderItem> items = webOrderItemService.getOrderItemOrderId(webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId());
        for (WebOrderItem item : items) {
            result += (item.getQuantity() * item.getProductPrice());
        }
        return result;
    }

}
