package pl.javarun.mywebshop.controller;

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

import static pl.javarun.mywebshop.util.InputValidator.passwordValidator;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 01.05.2020 17:21
 * *
 * @className: ChangePasswordByTokenController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/changePasswordByToken")
public class ChangePasswordByTokenController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;

    public ChangePasswordByTokenController(UserService userService, TypeService typeService, CompanyService companyService, RuleService ruleService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
    }


    @GetMapping()
    public ModelAndView changePasswordView(@PathParam("regId") String regId, @PathParam("email") String email,
                                           @PathParam("passwordChanged") boolean passwordChanged,
                                           @PathParam("newPasswordsNotTheSame") boolean newPasswordsNotTheSame,
                                           @PathParam("wrongPasswordChar") boolean wrongPasswordChar,
                                           @PathParam("noPassword") boolean noPassword,
                                           @PathParam("userNotExist") boolean userNotExist,
                                           @PathParam("noSuccess") boolean noSuccess) {
        ModelAndView modelAndView = new ModelAndView("changePasswordByToken");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("email", email);
        modelAndView.addObject("token", regId);
        if (newPasswordsNotTheSame) modelAndView.addObject("newPasswordsNotTheSame", true);
        if (passwordChanged) modelAndView.addObject("passwordChanged", true);
        if (noPassword) modelAndView.addObject("noPassword", true);
        if (wrongPasswordChar) modelAndView.addObject("wrongPasswordChar", true);
        if (userNotExist) modelAndView.addObject("userNotExist", true);
        if (noSuccess) modelAndView.addObject("noSuccess", true);
        return modelAndView;
    }

    @PostMapping()
    public String changePassword(@RequestParam String newPassword, @RequestParam String newPassword2,
                                 @RequestParam String token, @RequestParam String email) {
        if (newPassword.isEmpty() || !passwordValidator(newPassword)) {
            String wrongPasswordAnswer;
            String passwordAnswer;
            if (!passwordValidator(newPassword)) {
                wrongPasswordAnswer = "wrongPasswordChar=true";
            } else {
                wrongPasswordAnswer = "";
            }
            if (newPassword.isEmpty()) {
                passwordAnswer = "noPassword=true";
            } else {
                passwordAnswer = "";
            }
            return "redirect:/changePasswordByToken?regId=" + token + "&email=" + email + "&" + passwordAnswer + "&" + wrongPasswordAnswer;
        }
        if (!newPassword.equals(newPassword2)) {
            return "redirect:/changePasswordByToken?regId=" + token + "&email=" + email + "&newPasswordsNotTheSame=true";
        }

        String hashedPassword = PasswordUtil.hashPassword(newPassword);
        try {
            User user = userService.getUserByToken(token);
            if (!user.getEmail().equals(email)) {
                return "redirect:/changePasswordByToken?noSuccess=true";
            }
            user.setPassword(hashedPassword);
            userService.saveUser(user);
            return "redirect:/changePasswordByToken?passwordChanged=true";
        } catch (UserNotExistException ex) {
            return "redirect:/changePasswordByToken?userNotExist=true";
        }
    }
}
