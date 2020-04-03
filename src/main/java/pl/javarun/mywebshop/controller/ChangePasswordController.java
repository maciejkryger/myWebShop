package pl.javarun.mywebshop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.UserNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.CompanyService;
import pl.javarun.mywebshop.service.RuleService;
import pl.javarun.mywebshop.service.TypeService;
import pl.javarun.mywebshop.service.UserService;
import pl.javarun.mywebshop.util.PasswordUtil;

import javax.websocket.server.PathParam;



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

    public ChangePasswordController(UserService userService, TypeService typeService, CompanyService companyService, RuleService ruleService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
    }


    @GetMapping()
    public ModelAndView changePasswordView(@PathParam("wrongPassword") boolean wrongPassword, @PathParam("passwordChanged") boolean passwordChanged,
                                           @PathParam("newPasswordsNotTheSame") boolean newPasswordsNotTheSame,
                                           @PathParam("userNotExist") boolean userNotExist){
        ModelAndView modelAndView = new ModelAndView("changePassword");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if(userNotExist){
            modelAndView.addObject("userNotExist",userNotExist);
        } else if (newPasswordsNotTheSame) {
            modelAndView.addObject("newPasswordsNotTheSame", true);
        }else if (wrongPassword){
            modelAndView.addObject("wrongPassword",true);
        }else if(passwordChanged){
            modelAndView.addObject("passwordChanged",true);
        }
        return modelAndView;
    }

    @PostMapping()
    public String changePassword(@RequestParam String username, @RequestParam String oldPassword,
                                 @RequestParam String newPassword, @RequestParam String newPassword2) {
        System.out.println("username: " + username);
        System.out.println("old password: " + oldPassword);
        System.out.println("new password: " + newPassword);
        System.out.println("repeated new password: " + newPassword2);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(newPassword);
        PasswordUtil passwordUtil = new PasswordUtil();

        try {
            User user = userService.getUserByUsername(username);

            if (!newPassword.equals(newPassword2)) {
                System.out.println("nowe hasla nie sa takie same");
                return "redirect:/changePassword?newPasswordsNotTheSame=true";
            }

            if (passwordUtil.checkPassword(oldPassword, user.getPassword())) {
                System.out.println("zmiana hasła");
                System.out.println(hashedPassword);
                user = userService.getUserByUsername(username);
                user.setPassword(hashedPassword);
                userService.saveUser(user);
                return "redirect:/changePassword?passwordChanged=true";
            }
            System.out.println(hashedPassword);
            System.out.println(user.getPassword());
            System.out.println("bledne haslo");
            return "redirect:/changePassword?wrongPassword=true";

        } catch (UserNotExistException ex) {
            System.out.println("bledny uzytkownik");
            return "redirect:/changePassword?userNotExist=true";
        }
    }
}
