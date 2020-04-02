package pl.javarun.mywebshop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    public String saveUserItem(@RequestParam String username, @RequestParam String firstName,
                               @RequestParam String lastName, @RequestParam String email,
                               @RequestParam Integer roleId,  @RequestParam Boolean active) {
        User user;
        if(userService.getUserByUsername(username)==null){
            user= new User();
            PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(username);
            user.setPassword(hashedPassword);
            user.setDeleted(false);
            user.setToken("addedByAdmin");
        }else {
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

    @GetMapping("/changePassword")
    public ModelAndView changePasswordView(@PathParam("username") String username,
                                           @PathParam("passwordChanged") boolean passwordChanged,
                                           @PathParam("newPasswordsNotTheSame") boolean newPasswordsNotTheSame){
        modelAndView = new ModelAndView("panels/userChangePassword");
        modelAndView.addObject("username", username);
        if (newPasswordsNotTheSame) {
            modelAndView.addObject("newPasswordsNotTheSame", true);
        } else if(passwordChanged){
            modelAndView.addObject("passwordChanged",true);
        }
        return modelAndView;
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String username,
                                 @RequestParam String newPassword, @RequestParam String newPassword2){
        System.out.println("username: "+username);
        System.out.println("new password: "+newPassword);
        System.out.println("repeated new password: "+newPassword2);
        if (!newPassword.equals(newPassword2)){
            return "redirect:/panels/data/user/changePassword?username="+username+"&newPasswordsNotTheSame=true";
        } else {
        User user = userService.getUserByUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        userService.saveUser(user);
            return "redirect:/panels/data/user/changePassword?username="+username+"&passwordChanged=true";
        }
    }
}

