package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.UserNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.EmailRegister;
import pl.javarun.mywebshop.util.TokenAlgorithm;
import pl.javarun.mywebshop.util.PasswordUtil;

import javax.security.auth.message.callback.PrivateKeyCallback;
import javax.websocket.server.PathParam;
import java.sql.Timestamp;



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
    private final RoleService roleService;

    public RegisterController(UserService userService, TypeService typeService, CompanyService companyService,
                              RuleService ruleService, RoleService roleService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.roleService = roleService;
    }


    @GetMapping()
    public ModelAndView showRegisterPage(@PathParam("success") boolean success, @PathParam("userExist") boolean userExist,
                                         @RequestParam("userExistButNotActive") boolean userExistButNotActive,
                                         @PathParam("noUsername") boolean noUsername, @PathParam("username") String username,
                                         @PathParam("noPassword") boolean noPassword,
                                         @PathParam("noFirstName") boolean noFirstName, @PathParam("firstName") String firstName,
                                         @PathParam("noLastName") boolean noLastName, @PathParam("lastName") String lastName,
                                         @PathParam("noEmail") boolean noEmail, @PathParam("email") String email) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if (success) modelAndView.addObject("success", true);
        if (userExist) modelAndView.addObject("userExist", true);
        if(userExistButNotActive) modelAndView.addObject("userExistButNotActive",true);
        if (noUsername) modelAndView.addObject("noUsername", true);
        if (noPassword) modelAndView.addObject("noPassword", true);
        if (noFirstName) modelAndView.addObject("noFirstName", true);
        if (noLastName) modelAndView.addObject("noLastName", true);
        if (noEmail) modelAndView.addObject("noEmail", true);
        if (username!=null) modelAndView.addObject("username", username);
        if (firstName!=null) modelAndView.addObject("firstName", firstName);
        if (lastName!=null) modelAndView.addObject("lastName", lastName);
        if (email!=null) modelAndView.addObject("email", email);
        return modelAndView;
    }

    @PostMapping()
    public String registerUser(@RequestParam(required = false) String username, @RequestParam(required = false) String password,
                               @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) String email, @RequestParam(required = false) String contextPath) {
        System.out.println("Dane rejestracyjne: " + username + ": " + password + " : " + firstName + " " + lastName + ", " + email);
        System.out.println(contextPath);
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            String usernameAnswer;
            String passwordAnswer;
            String firstNameAnswer;
            String lastNameAnswer;
            String emailAnswer;
            if (username == "") {
                usernameAnswer = "noUsername=true";
            } else {
                usernameAnswer = "username="+username;
            }
            if (password == "") {
                passwordAnswer = "noPassword=true";
            } else {
                passwordAnswer = "";
            }
            if (firstName == "") {
                firstNameAnswer = "noFirstName=true";
            } else {
                firstNameAnswer = "firstName="+firstName;
            }
            if (lastName == "") {
                lastNameAnswer = "noLastName=true";
            } else {
                lastNameAnswer = "lastName="+lastName;
            }
            if (email == "") {
                emailAnswer = "noEmail=true";
            } else {
                emailAnswer = "email="+email;
            }
            return "redirect:/register?" + usernameAnswer + "&" + passwordAnswer + "&" + firstNameAnswer + "&" + lastNameAnswer + "&" + emailAnswer;
        }

        try {
            User user = userService.getUserByUsername(username);
            if(!user.isActive()){
                return "redirect:/register?userExistButNotActive=true";
            }
            return "redirect:/register?userExist=true";
        } catch (UserNotExistException ex) {
            PasswordUtil passwordUtil = new PasswordUtil();
            String hashedPassword = passwordUtil.hashPassword(password);
            TokenAlgorithm tokenAlgorithm = new TokenAlgorithm();
            String token = tokenAlgorithm.generate();
            User user = new User();
            user.setUsername(username);
            user.setPassword(hashedPassword);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setRole(roleService.getRoleById(3));
            user.setCreationDate(new Timestamp(System.currentTimeMillis()));
            user.setActive(false);
            user.setActivationDate(null);
            user.setDeleted(false);
            user.setDeletingDate(null);
            user.setToken(token);
            userService.saveUser(user);
            EmailRegister emailRegister = new EmailRegister();
            emailRegister.send(username,firstName, email,token);
            return "redirect:/register?success=true";
        }
    }

}
