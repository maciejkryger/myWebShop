package pl.javarun.mywebshop.controller.shopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.AddressNotExistException;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.model.WebOrderItem;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.EmailOrderConfirmation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static pl.javarun.mywebshop.util.InputValidator.phoneValidator;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.05.2020 22:47
 * *
 * @className: WebOrderConfirmationController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/confirmation")
public class WebOrderConfirmationController {

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
    private final EmailOrderConfirmation emailOrderConfirmation;
    private int sumToPay;
    private int sumQuantity;
    private int deliveryCostsToPay;
    private final WishListService wishListService;

    public WebOrderConfirmationController(UserService userService, TypeService typeService, CompanyService companyService,
                                          RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                                          ProductService productService, DeliveryOptionService deliveryOptionService,
                                          PaymentTypeService paymentTypeService, AddressService addressService,
                                          EmailOrderConfirmation emailOrderConfirmation, WishListService wishListService) {
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
        this.emailOrderConfirmation=emailOrderConfirmation;
        this.wishListService=wishListService;
    }

    @GetMapping()
    public ModelAndView confirmation(HttpServletRequest httpServletRequest, @PathParam("phoneEmpty") boolean phoneEmpty,
                                     @PathParam("phoneWrong") boolean phoneWrong) {
        ModelAndView modelAndView = new ModelAndView("shopping/confirmation");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if(phoneEmpty) modelAndView.addObject("phoneEmpty", true);
        if(phoneWrong) modelAndView.addObject("phoneWrong",true);
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        WebOrder webOrder = webOrderService.getOrderByUserIdAndConfirmedFalse(userId);
        sumToPay = webOrderItemService.calculateActualSumToPayInUserBasket(webOrder.getId());
        sumQuantity = webOrderItemService.calculateActualQuantityInUserBasket(webOrder.getId());
        deliveryCostsToPay = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getDeliveryOption().getPrice();
        modelAndView.addObject("sumToPay", sumToPay);
        modelAndView.addObject("sumQuantity", sumQuantity);
        modelAndView.addObject("deliveryCostsToPay", deliveryCostsToPay);
        modelAndView.addObject("productsInBasket", webOrderItemService.getOrderItemByOrderId(webOrder.getId()));
        modelAndView.addObject("webOrder",webOrder);
        modelAndView.addObject("productsInBasketSize", sumQuantity);
        try{
            modelAndView.addObject("address", addressService.getByUser(user));
        }catch (AddressNotExistException ignored){}
        try {
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(user.getId()).size());
        } catch (OrderNotExistException ex) {
            modelAndView.addObject("userWishListSize", 0);
        }
        return modelAndView;
    }

    @PostMapping
    public String saveDeliveryOption(HttpServletRequest httpServletRequest, @RequestParam int orderId,
                                           @RequestParam(required = false) String phone, @RequestParam(required = false) String comment) {
        if(phone==null || phone.isEmpty()){
            return "redirect:/confirmation?phoneEmpty=true";
        }
        if(!phoneValidator(phone)){
            return "redirect:/confirmation?phoneWrong=true";
        }
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        user.setPhone(phone);
        userService.saveUser(user);
        WebOrder webOrder = webOrderService.getOrderById(orderId);
        webOrder.setConfirmed(true);
        webOrder.setComment(comment);
        webOrder.setConfirmDate(Timestamp.valueOf(LocalDateTime.now()));
        webOrder.setOrderNumber(webOrder.getId()+"/"+LocalDateTime.now().getMonthValue()+"/"+LocalDateTime.now().getYear());
        webOrderService.save(webOrder);
        emailOrderConfirmation.send(user,webOrder,webOrderItemService.getOrderItemByOrderId(webOrder.getId()),
                sumToPay,sumQuantity,deliveryCostsToPay);

        return  "redirect:/confirmation/finished?ono="+webOrder.getOrderNumber();
    }

    @GetMapping("/finished")
    public ModelAndView showOrderNumber(HttpServletRequest httpServletRequest,@PathParam(value="ono") String ono){
        ModelAndView modelAndView = new ModelAndView("shopping/confirmationWithOrderNumber");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("orderNumber",ono);
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        modelAndView.addObject("productsInBasketSize", 0);
        try {
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(user.getId()).size());
        } catch (OrderNotExistException ex) {
            modelAndView.addObject("userWishListSize", 0);
        }
        return modelAndView;
    }
}
