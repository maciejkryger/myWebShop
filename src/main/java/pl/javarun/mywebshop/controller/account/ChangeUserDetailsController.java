package pl.javarun.mywebshop.controller.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 04.04.2020 13:32
 * *
 * @className: ChangeUserDetailsController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/account/changeUserDetails")
public class ChangeUserDetailsController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final RoleService roleService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final WishListService wishListService;
    private final ConfigDataService configDataService;
    @Value("${mail_href}")
    private String href;

    public ChangeUserDetailsController(UserService userService, TypeService typeService, CompanyService companyService,
                                       RuleService ruleService, RoleService roleService, WishListService wishListService,
                                       WebOrderItemService webOrderItemService, WebOrderService webOrderService,
                                       ConfigDataService configDataService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.roleService = roleService;
        this.wishListService = wishListService;
        this.webOrderItemService = webOrderItemService;
        this.webOrderService = webOrderService;
        this.configDataService=configDataService;
    }


    @GetMapping()
    public ModelAndView changeUserDetailsPage(@SessionAttribute User user,
                                              @PathParam("success") boolean success, @PathParam("username") String username,
                                              @PathParam("noFirstName") boolean noFirstName, @PathParam("firstName") String firstName,
                                              @PathParam("noLastName") boolean noLastName, @PathParam("lastName") String lastName,
                                              @PathParam("noEmail") boolean noEmail, @PathParam("email") String email,
                                              @PathParam("withoutRulesAcceptation") boolean withoutRulesAcceptation) {
        ModelAndView modelAndView = new ModelAndView("account/changeUserDetails");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("RulesAcceptedStatement",String.format(configDataService.getConfigDataByName("RulesAcceptedStatement").getValue(), href));
        modelAndView.addObject("MarketingAgreedStatement",configDataService.getConfigDataByName("MarketingAgreedStatement").getValue());
        if (user != null && username == null) username = user.getUsername();
        if (user != null) {
            modelAndView.addObject("user", userService.getUserByUsername(username));
        }
        if (success) modelAndView.addObject("success", true);
        if (noFirstName) modelAndView.addObject("noFirstName", true);
        if (noLastName) modelAndView.addObject("noLastName", true);
        if (noEmail) modelAndView.addObject("noEmail", true);
        if (withoutRulesAcceptation) modelAndView.addObject("withoutRulesAcceptation",true);
        int userId = user.getId();
        try {
            int webOrderId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
            modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderId));
        } catch (OrderNotExistException ex) {
            modelAndView.addObject("productsInBasketSize", 0);
        }
        try {
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(userId).size());
        } catch (WishListNotExistException ex) {
            modelAndView.addObject("userWishListSize", 0);
        }
        return modelAndView;
    }

    @PostMapping()
    public String changeUserDetails(@RequestParam(required = false) String username,
                                    @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) String email, @RequestParam(required = false) String phone,
                                    @RequestParam(required = false) boolean rulesAccepted,
                                    @RequestParam(required = false) boolean marketingAgreed) {
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || rulesAccepted == false) {
            String firstNameAnswer;
            String lastNameAnswer;
            String emailAnswer;
            String wrongRulesAgreedAnswer;

            if (firstName == "") {
                firstNameAnswer = "noFirstName=true";
            } else {
                firstNameAnswer = "firstName=" + firstName;
            }
            if (lastName == "") {
                lastNameAnswer = "noLastName=true";
            } else {
                lastNameAnswer = "lastName=" + lastName;
            }
            if (email == "") {
                emailAnswer = "noEmail=true";
            } else {
                emailAnswer = "email=" + email;
            }
            if (!rulesAccepted) {
                wrongRulesAgreedAnswer = "withoutRulesAcceptation=true";
            } else {
                wrongRulesAgreedAnswer = "";
            }
            return "redirect:/account/changeUserDetails?username=" + username + "&" + firstNameAnswer + "&" +
                    lastNameAnswer + "&" + emailAnswer + "&" + wrongRulesAgreedAnswer;
        }

        User user = userService.getUserByUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRulesAccepted(rulesAccepted);
        if(user.isMarketingAgreed()!=marketingAgreed) {
            user.setMarketingAgreed(marketingAgreed);
            user.setUpdateAgreedDate(new Timestamp(System.currentTimeMillis()));
        }
        userService.saveUser(user);
        return "redirect:/account/changeUserDetails?username=" + username + "&success=true";

    }

}
