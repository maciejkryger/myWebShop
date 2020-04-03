package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 03.04.2020 12:56
 * *
 * @className: RegisterController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;

    public RegisterController(UserService userService, TypeService typeService, CompanyService companyService, RuleService ruleService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
    }


    @GetMapping()
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        return modelAndView;
    }

    @PostMapping()
    public String registerUser(){
        return "redirect:/register?success=true";
    }



}
