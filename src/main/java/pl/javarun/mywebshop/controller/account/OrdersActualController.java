package pl.javarun.mywebshop.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.05.2020 22:04
 * *
 * @className: OrdersActualController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/account/actual")
public class OrdersActualController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final WishListService wishListService;

    public OrdersActualController(UserService userService, TypeService typeService, CompanyService companyService,
                                  RuleService ruleService, WebOrderItemService webOrderItemService, WebOrderService webOrderService,
                                  WishListService wishListService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.webOrderItemService = webOrderItemService;
        this.webOrderService = webOrderService;
        this.wishListService = wishListService;
    }


    @GetMapping()
    public ModelAndView showActualOrders(HttpServletRequest httpServletRequest, @RequestParam(required = false) Integer orderId) {
        ModelAndView modelAndView = new ModelAndView("account/ordersActual");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("actualOrders",webOrderService.getAllActualOrdersByUser(user));

        return modelAndView;
    }




}
