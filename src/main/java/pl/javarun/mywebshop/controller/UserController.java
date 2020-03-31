package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.Type;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 20:38
 * *
 * @className: UserController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/user/")
public class UserController {

    ModelAndView modelAndView;
    ProductService productService;
    TypeService typeService;
    MaterialService materialService;
    MaterialColorService materialColorService;
    FasteningTypeService fasteningTypeService;
    FasteningColorService fasteningColorService;
    MakingTechniqueService makingTechniqueService;
    UserService userService;
    CompanyService companyService;
    RuleService ruleService;
    RoleService roleService;


    public UserController(ProductService productService, TypeService typeService, MaterialService materialService,
                          MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                          FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                          UserService userService, CompanyService companyService, RuleService ruleService, RoleService roleService) {
        this.productService = productService;
        this.typeService = typeService;
        this.materialService = materialService;
        this.materialColorService = materialColorService;
        this.fasteningTypeService = fasteningTypeService;
        this.fasteningColorService = fasteningColorService;
        this.makingTechniqueService = makingTechniqueService;
        this.userService = userService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.roleService=roleService;
    }

    @GetMapping({"/{username}","/new"})
    public ModelAndView editUserItem(@PathVariable (required = false) String username) {
        modelAndView = new ModelAndView("panels/userItemManager");
        modelAndView.addObject("roles",roleService.getAllRoles());
        if (username==null){
          modelAndView.addObject("user", new User());
        } else {
        modelAndView.addObject("user", userService.getUserByUsername(username));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveUserItem(@RequestParam (required = false) String username, @RequestParam String firstName,
                               @RequestParam String lastName, @RequestParam String email,
                               @RequestParam Integer roleId,  @RequestParam Boolean active) {
        User user;
        if(userService.getUserByUsername(username)==null){
            System.out.println("is null, new user");
            user = new User();
            user.setUsername(username);
        } else {
            System.out.println("existing user, !=null");
            user = userService.getUserByUsername(username);
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(roleService.getRoleById(roleId));
        user.setActive(active);
        userService.saveUser(user);
        return "redirect:/panels/data/users";
    }
}

