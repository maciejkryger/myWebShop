package pl.javarun.mywebshop.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.websocket.server.PathParam;

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

    public ChangeUserDetailsController(UserService userService, TypeService typeService, CompanyService companyService,
                                       RuleService ruleService, RoleService roleService, WishListService wishListService,
                                       WebOrderItemService webOrderItemService, WebOrderService webOrderService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.roleService = roleService;
        this.wishListService = wishListService;
        this.webOrderItemService = webOrderItemService;
        this.webOrderService = webOrderService;
    }


    @GetMapping()
    public ModelAndView changeUserDetailsPage(@SessionAttribute User user,
                                              @PathParam("success") boolean success, @PathParam("username") String username,
                                              @PathParam("noFirstName") boolean noFirstName, @PathParam("firstName") String firstName,
                                              @PathParam("noLastName") boolean noLastName, @PathParam("lastName") String lastName,
                                              @PathParam("noEmail") boolean noEmail, @PathParam("email") String email) {
        ModelAndView modelAndView = new ModelAndView("account/changeUserDetails");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if (user!=null && username==null) username=user.getUsername();
        if (user!=null){modelAndView.addObject("user",userService.getUserByUsername(username)); }
        if (success) modelAndView.addObject("success", true);
        if (noFirstName) modelAndView.addObject("noFirstName", true);
        if (noLastName) modelAndView.addObject("noLastName", true);
        if (noEmail) modelAndView.addObject("noEmail", true);
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
                               @RequestParam(required = false) String email, @RequestParam(required = false) String phone) {
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            String firstNameAnswer;
            String lastNameAnswer;
            String emailAnswer;

            if (firstName == "") {
                firstNameAnswer = "noFirstName=true";
            } else {
                firstNameAnswer = "firstName="+firstName;
            }
            if (lastName == "") {
                lastNameAnswer = "noLastName=true";
            } else {
                lastNameAnswer = "lastName="+lastName;
            }
            if (email == "") {
                emailAnswer = "noEmail=true";
            } else {
                emailAnswer = "email="+email;
            }
            return "redirect:/account/changeUserDetails?username=" + username + "&" + firstNameAnswer + "&" + lastNameAnswer + "&" + emailAnswer;
        }

            User user = userService.getUserByUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhone(phone);
            userService.saveUser(user);
            return "redirect:/account/changeUserDetails?username="+username+"&success=true";

    }

}
