package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.service.CompanyService;
import pl.javarun.mywebshop.service.RuleService;
import pl.javarun.mywebshop.service.TypeService;
import pl.javarun.mywebshop.service.UserService;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 03.04.2020 13:10
 * *
 * @className: LoginController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;

    public LoginController(UserService userService, TypeService typeService, CompanyService companyService, RuleService ruleService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
    }

    @GetMapping()
    public ModelAndView loginUsername(@RequestParam(required = false) String error){
        ModelAndView modelAndView=new ModelAndView("login");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if(error != null) {
            modelAndView.addObject("error", true);
        }
        return modelAndView;
    }

//    @PostMapping()
//    public String loginUsername(@RequestParam String username, @RequestParam String password){
//        System.out.println("username: "+ username);
//        System.out.println("password: "+ password);
//        if(username.equals("admin"))
//        return "redirect:/panels/superpanel";
//        else return "redirect:/";
//    }

}
