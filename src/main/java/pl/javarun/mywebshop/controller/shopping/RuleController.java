package pl.javarun.mywebshop.controller.shopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.UserNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 03.04.2020 13:43
 * *
 * @className: RuleController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/rules")
public class RuleController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final WishListService wishListService;
    private final WebOrderItemService webOrderItemService;
    private final WebOrderService webOrderService;

    public RuleController(UserService userService, TypeService typeService, CompanyService companyService,
                          RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                          WishListService wishListService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.wishListService = wishListService;
        this.webOrderItemService = webOrderItemService;
        this.webOrderService = webOrderService;
    }

    @GetMapping("/{subject}")
    public ModelAndView showRegulationPage(HttpServletRequest httpServletRequest, @PathVariable String subject) {
        ModelAndView modelAndView;
        if (ruleService.getRuleByName(subject) == null) {
            modelAndView = new ModelAndView("index");
        } else {
            modelAndView = new ModelAndView("rules");
            modelAndView.addObject("rule", ruleService.getRuleByName(subject));
            modelAndView.addObject("text", ruleService.getRuleByName(subject).getDescriptionPl());
        }
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        try {
            modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderService, user.getId()));
        } catch (OrderNotExistException ex) {
            modelAndView.addObject("productsInBasketSize", 0);
        }
        try {
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(user.getId()).size());
        } catch (WishListNotExistException ex) {
            modelAndView.addObject("userWishListSize", 0);
        }

        return modelAndView;
    }
}
