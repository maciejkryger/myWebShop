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

import javax.websocket.server.PathParam;
import java.sql.Timestamp;

import static pl.javarun.mywebshop.util.InputValidator.*;


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
    private final EmailRegister emailRegister;

    public RegisterController(UserService userService, TypeService typeService, CompanyService companyService,
                              RuleService ruleService, RoleService roleService, EmailRegister emailRegister) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.roleService = roleService;
        this.emailRegister=emailRegister;
    }


    @GetMapping()
    public ModelAndView showRegisterPage(@PathParam("success") boolean success, @PathParam("userExist") boolean userExist,
                                         @PathParam("userExistButNotActive") boolean userExistButNotActive,
                                         @PathParam("noUsername") boolean noUsername, @PathParam("username") String username,
                                         @PathParam("noPassword") boolean noPassword,
                                         @PathParam("noFirstName") boolean noFirstName, @PathParam("firstName") String firstName,
                                         @PathParam("noLastName") boolean noLastName, @PathParam("lastName") String lastName,
                                         @PathParam("noEmail") boolean noEmail, @PathParam("email") String email,
                                         @PathParam("wrongUsernameChar") boolean wrongUsernameChar,
                                         @PathParam("wrongPasswordChar") boolean wrongPasswordChar,
                                         @PathParam("wrongFirstNameChar") boolean wrongFirstNameChar,
                                         @PathParam("wrongLastNameChar") boolean wrongLastNameChar,
                                         @PathParam("wrongEmailChar") boolean wrongEmailChar,
                                         @PathParam("emailExist") boolean emailExist) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if (success) modelAndView.addObject("success", true);
        if (userExist) modelAndView.addObject("userExist", true);
        if (userExistButNotActive) modelAndView.addObject("userExistButNotActive", true);
        if (noUsername) modelAndView.addObject("noUsername", true);
        if (noPassword) modelAndView.addObject("noPassword", true);
        if (noFirstName) modelAndView.addObject("noFirstName", true);
        if (noLastName) modelAndView.addObject("noLastName", true);
        if (noEmail) modelAndView.addObject("noEmail", true);
        if (username != null) modelAndView.addObject("username", username);
        if (firstName != null) modelAndView.addObject("firstName", firstName);
        if (lastName != null) modelAndView.addObject("lastName", lastName);
        if (email != null) modelAndView.addObject("email", email);
        if (wrongUsernameChar) modelAndView.addObject("wrongUsernameChar", true);
        if (wrongPasswordChar) modelAndView.addObject("wrongPasswordChar", true);
        if (wrongFirstNameChar) modelAndView.addObject("wrongFirstNameChar", true);
        if (wrongLastNameChar) modelAndView.addObject("wrongLastNameChar", true);
        if (wrongEmailChar) modelAndView.addObject("wrongEmailChar", true);
        if (emailExist) modelAndView.addObject("emailExist", true);
        return modelAndView;
    }

    @PostMapping()
    public String registerUser(@RequestParam(required = false) String username, @RequestParam(required = false) String password,
                               @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) String email) {

            if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
                    || !nameValidator(firstName) || !nameValidator(lastName) || !nameValidator(username)
                    || !passwordValidator(password) || !emailValidator(email))  {
                String usernameAnswer;
                String passwordAnswer;
                String firstNameAnswer;
                String lastNameAnswer;
                String emailAnswer;
                String wrongFirstNameAnswer;
                String wrongLastNameAnswer;
                String wrongUsernameAnswer;
                String wrongPasswordAnswer;
                String wrongEmailAnswer;

                if (!nameValidator(firstName)) {
                    wrongFirstNameAnswer = "wrongFirstNameChar=true";
                } else {
                    wrongFirstNameAnswer = "";
                }
                if (!nameValidator(lastName)) {
                    wrongLastNameAnswer = "wrongLastNameChar=true";
                } else {
                    wrongLastNameAnswer = "";
                }
                if (!nameValidator(username)) {
                    wrongUsernameAnswer = "wrongUsernameChar=true";
                } else {
                    wrongUsernameAnswer = "";
                }
                if (!passwordValidator(password)) {
                    wrongPasswordAnswer = "wrongPasswordChar=true";
                } else {
                    wrongPasswordAnswer = "";
                }
                if (!emailValidator(email)) {
                    wrongEmailAnswer = "wrongEmailChar=true";
                } else {
                    wrongEmailAnswer = "";
                }
                if (username.isEmpty()) {
                    usernameAnswer = "noUsername=true";
                } else {
                    usernameAnswer = "username=" + username;
                }
                if (password.isEmpty()) {
                    passwordAnswer = "noPassword=true";
                } else {
                    passwordAnswer = "";
                }
                if (firstName.isEmpty()) {
                    firstNameAnswer = "noFirstName=true";
                } else {
                    firstNameAnswer = "firstName=" + firstName;
                }
                if (lastName.isEmpty()) {
                    lastNameAnswer = "noLastName=true";
                } else {
                    lastNameAnswer = "lastName=" + lastName;
                }
                if (email.isEmpty()) {
                    emailAnswer = "noEmail=true";
                } else {
                    emailAnswer = "email=" + email;
                }

                return "redirect:/register?" + usernameAnswer + "&" + passwordAnswer + "&" + firstNameAnswer + "&" +
                        lastNameAnswer + "&" + emailAnswer + "&" + wrongUsernameAnswer + "&" + wrongPasswordAnswer + "&" +
                        wrongFirstNameAnswer + "&" + wrongLastNameAnswer + "&" + wrongEmailAnswer;
            }


            try {
                try {
                    User user = userService.getUserByEmail(email);
                    if (user != null) {
                        return "redirect:/register?username="+username+"&firstName="+firstName+"&lastName="+lastName+"&email="+email+"&emailExist=true";
                    }
                } catch (UserNotExistException ex){
                User user = userService.getUserByUsername(username);
                if (!user.isActive()) {
                    return "redirect:/register?userExistButNotActive=true";
                }
                return "redirect:/register?userExist=true";}
            } catch (UserNotExistException ex1) {
                String hashedPassword = PasswordUtil.hashPassword(password);
                String token = TokenAlgorithm.generate();
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
                emailRegister.send(username, firstName, email, token);

            }


        return "redirect:/register?success=true";
    }

}
