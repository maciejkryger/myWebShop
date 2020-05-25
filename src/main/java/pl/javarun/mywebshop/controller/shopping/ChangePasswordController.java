package pl.javarun.mywebshop.controller.shopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.UserNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.PasswordUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import static pl.javarun.mywebshop.util.InputValidator.passwordValidator;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 03.04.2020 13:04
 * *
 * @className: ChangePassword
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/changePassword")
public class ChangePasswordController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final WishListService wishListService;

    public ChangePasswordController(UserService userService, TypeService typeService, CompanyService companyService,
                                    RuleService ruleService,WebOrderItemService webOrderItemService, WebOrderService webOrderService,
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
    public ModelAndView changePasswordView(@PathParam("wrongPassword") boolean wrongPassword,
                                           @PathParam("passwordChanged") boolean passwordChanged,
                                           @PathParam("newPasswordsNotTheSame") boolean newPasswordsNotTheSame,
                                           @PathParam("userNotExist") boolean userNotExist,
                                           @PathParam("wrongPasswordChar") boolean wrongPasswordChar,
                                           @PathParam("noOldPassword") boolean noOldPassword,
                                           @PathParam("noNewPassword") boolean noNewPassword,
                                           @PathParam("noNewPassword2") boolean noNewPassword2,
                                           HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("changePassword");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if (userNotExist) modelAndView.addObject("userNotExist", userNotExist);
        if (newPasswordsNotTheSame) modelAndView.addObject("newPasswordsNotTheSame", true);
        if (wrongPassword) modelAndView.addObject("wrongPassword", true);
        if (passwordChanged) modelAndView.addObject("passwordChanged", true);
        if (wrongPasswordChar) modelAndView.addObject("wrongPasswordChar", true);
        if (noOldPassword) modelAndView.addObject("noOldPassword", true);
        if (noNewPassword) modelAndView.addObject("noNewPassword", true);
        if (noNewPassword2) modelAndView.addObject("noNewPassword2", true);
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        try {
            modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderService, userId));
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(user.getId()).size());
        } catch (OrderNotExistException ex) {
            modelAndView.addObject("productsInBasketSize", 0);
            modelAndView.addObject("userWishListSize", 0);
        }
        return modelAndView;
    }

    @PostMapping()
    public String changePassword(@RequestParam String username, @RequestParam String oldPassword,
                                 @RequestParam String newPassword, @RequestParam String newPassword2) {
        if (oldPassword.isEmpty() || newPassword.isEmpty() || newPassword2.isEmpty()|| !passwordValidator(newPassword)) {
            String wrongPasswordAnswer;
            String oldPasswordAnswer;
            String newPasswordAnswer;
            String newPasswordAnswer2;
            if (!passwordValidator(newPassword)) {
                wrongPasswordAnswer = "wrongPasswordChar=true";
            } else {
                wrongPasswordAnswer = "";
            }
            if (oldPassword.isEmpty()) {
                oldPasswordAnswer = "noOldPassword=true";
            } else {
                oldPasswordAnswer = "";
            }
            if (newPassword.isEmpty()) {
                newPasswordAnswer = "noNewPassword=true";
            } else {
                newPasswordAnswer = "";
            }
            if (newPassword2.isEmpty()) {
                newPasswordAnswer2 = "noNewPassword2=true";
            } else {
                newPasswordAnswer2 = "";
            }
            return "redirect:/changePassword?"+ oldPasswordAnswer + "&" + newPasswordAnswer + "&" + newPasswordAnswer2 + "&" + wrongPasswordAnswer;
    }
        if (!newPassword.equals(newPassword2)) {
            return "redirect:/changePassword?newPasswordsNotTheSame=true";
        }

        String hashedPassword = PasswordUtil.hashPassword(newPassword);

        try {
            User user = userService.getUserByUsername(username);

            if (!newPassword.equals(newPassword2)) {
                return "redirect:/changePassword?newPasswordsNotTheSame=true";
            }

            if (PasswordUtil.checkPassword(oldPassword, user.getPassword())) {
                user = userService.getUserByUsername(username);
                user.setPassword(hashedPassword);
                userService.saveUser(user);
                return "redirect:/changePassword?passwordChanged=true";
            }
            return "redirect:/changePassword?wrongPassword=true";

        } catch (UserNotExistException ex) {
            return "redirect:/changePassword?userNotExist=true";
        }
    }


}
