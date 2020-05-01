package pl.javarun.mywebshop.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
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
import pl.javarun.mywebshop.util.EmailChangePassword;
import pl.javarun.mywebshop.util.PasswordUtil;

import javax.websocket.server.PathParam;

import static pl.javarun.mywebshop.util.InputValidator.emailValidator;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 01.05.2020 00:04
 * *
 * @className: ForgetPasswordController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;

    public ForgetPasswordController(UserService userService, TypeService typeService, CompanyService companyService, RuleService ruleService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
    }

    @GetMapping()
    public ModelAndView forgetPasswordView(@PathParam("userNotExist") boolean userNotExist,
                                           @PathParam("emailSent") boolean emailSent,
                                           @PathParam("noEmail") boolean noEmail,
                                           @PathParam("wrongEmailChar") boolean wrongEmailChar,
                                           @PathParam("email") String email){
        ModelAndView modelAndView = new ModelAndView("forgetPassword");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if (userNotExist) modelAndView.addObject("userNotExist",true);
        if (emailSent) modelAndView.addObject("emailSent",true);
        if (noEmail) modelAndView.addObject("noEmail",true);
        if (wrongEmailChar) modelAndView.addObject("wrongEmailChar",true);
        modelAndView.addObject("email",email);

        return modelAndView;
    }


    @PostMapping()
    public String forgetPasswordMethod(@RequestParam(required = false) String email){
        if (email.isEmpty() || !emailValidator(email)) {
            String emailAnswer;
            String wrongEmailAnswer;
            if (!emailValidator(email)) {
                wrongEmailAnswer = "wrongEmailChar=true";
            } else {
                wrongEmailAnswer = "";
            }
            if (email.isEmpty()) {
                emailAnswer = "noEmail=true";
            } else {
                emailAnswer = "email=" + email;
            }
            return "redirect:/forgetPassword?" + emailAnswer + "&" + wrongEmailAnswer;
        }
        try{
            User user = userService.getUserByEmail(email);
            EmailChangePassword emailChangePassword = new EmailChangePassword();
            emailChangePassword.send(user.getUsername(),user.getFirstName(),email, user.getToken());
        }catch (UserNotExistException ex){
            return "redirect:/forgetPassword?email="+email+"&userNotExist=true";
        }
        return  "redirect:/forgetPassword?emailSent=true";
    }




}
