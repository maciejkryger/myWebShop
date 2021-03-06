package pl.javarun.mywebshop.controller.panels;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import pl.javarun.mywebshop.exception.UserNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.PasswordUtil;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.sql.Timestamp;
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
public class UserManageController {

    private ModelAndView modelAndView;
    private final ProductService productService;
    private final TypeService typeService;
    private final MaterialService materialService;
    private final MaterialColorService materialColorService;
    private final FasteningTypeService fasteningTypeService;
    private final FasteningColorService fasteningColorService;
    private final MakingTechniqueService makingTechniqueService;
    private final UserService userService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final RoleService roleService;


    public UserManageController(ProductService productService, TypeService typeService, MaterialService materialService,
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
        this.roleService = roleService;
    }

    @GetMapping({"/{id}", "/new"})
    public ModelAndView editUserItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/userItemManager");
        modelAndView.addObject("roles", roleService.getAllRoles());
        if (id == null) {
            modelAndView.addObject("user", new User());
        } else {
            modelAndView.addObject("user", userService.getUserById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveUserItem(@RequestParam String username, @RequestParam String firstName,
                               @RequestParam String lastName, @RequestParam String email,
                               @RequestParam Integer roleId, @RequestParam Boolean active,
                               @RequestParam (required = false) String phone) {
        User user;
        try{
            user = userService.getUserByUsername(username);
        }catch (UserNotExistException ex){
            user = new User();
            user.setUsername(username);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(username);
            user.setPassword(hashedPassword);
            user.setToken("addedByAdmin_"+username);
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        if(phone!=null){
            user.setPhone(phone);
        }
        user.setRole(roleService.getRoleById(roleId));
        user.setCreationDate(new Timestamp(System.currentTimeMillis()));
        user.setActive(active);
        user.setActivationDate(null);
        user.setDeleted(false);
        user.setDeletingDate(null);
        userService.saveUser(user);
        return "redirect:/panels/data/users";
    }

    @GetMapping("/changePassword")
    public ModelAndView changePasswordView(@PathParam("username") String username,
                                           @PathParam("passwordChanged") boolean passwordChanged,
                                           @PathParam("newPasswordsNotTheSame") boolean newPasswordsNotTheSame) {
        modelAndView = new ModelAndView("panels/userChangePassword");
        modelAndView.addObject("username", username);
        if (newPasswordsNotTheSame) {
            modelAndView.addObject("newPasswordsNotTheSame", true);
        } else if (passwordChanged) {
            modelAndView.addObject("passwordChanged", true);
        }
        return modelAndView;
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String username,
                                 @RequestParam String newPassword, @RequestParam String newPassword2) {

        if (!newPassword.equals(newPassword2)) {
            return "redirect:/panels/data/user/changePassword?username=" + username + "&newPasswordsNotTheSame=true";
        } else {
            User user = userService.getUserByUsername(username);
            String hashedPassword = PasswordUtil.hashPassword(newPassword);
            user.setPassword(hashedPassword);
            userService.saveUser(user);
            return "redirect:/panels/data/user/changePassword?username=" + username + "&passwordChanged=true";
        }
    }
}

