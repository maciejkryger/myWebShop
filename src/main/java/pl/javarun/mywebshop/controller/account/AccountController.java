package pl.javarun.mywebshop.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.service.*;
import javax.servlet.http.HttpServletRequest;



/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.05.2020 17:48
 * *
 * @className: AccountController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final WishListService wishListService;

    public AccountController(UserService userService, TypeService typeService, CompanyService companyService,
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
    public ModelAndView showUserAccount(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("account/account");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        return modelAndView;
    }

    @PostMapping()
    public String account() {
        return "redirect:/account";
    }


}
