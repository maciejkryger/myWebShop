package pl.javarun.mywebshop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.UserNotExistException;
import pl.javarun.mywebshop.model.Product;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.PasswordUtil;

import javax.websocket.server.PathParam;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.02.2020 01:05
 * *
 * @className: HomepageController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/")
public class HomePageController {

    private final UserService userService;
    private final ProductService productService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final ColorPerMaterialService colorPerMaterialService;
    private final RuleService ruleService;

    public HomePageController(UserService userService, ProductService productService, TypeService typeService, CompanyService companyService, ColorPerMaterialService colorPerMaterialService, RuleService ruleService) {
        this.userService = userService;
        this.productService = productService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.colorPerMaterialService = colorPerMaterialService;
        this.ruleService = ruleService;
    }

    @GetMapping(value = {"", "/{id}"})
    public ModelAndView getIndexPage(@PathVariable(required = false) String id,
                                     @PathParam("userIsActive") Boolean userIsActive,
                                     @PathParam("userWasActive") Boolean userWasActive,
                                     @PathParam("userNotExist") Boolean userNotExist,
                                     @PathParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if(userIsActive!=null){
            modelAndView.addObject("userIsActive",userIsActive);
        }
        if(userWasActive!=null){
            modelAndView.addObject("userWasActive",userWasActive);
        }
        if(userNotExist!=null){
            modelAndView.addObject("userNotExist",userNotExist);
        }
        if(username!=null){
            modelAndView.addObject("username",username);
        }
        return modelAndView;
    }
}
