package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.UserService;
import pl.javarun.mywebshop.util.EmailContactForm;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 24.04.2020 18:41
 * *
 * @className: UserActivationController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/activation")
public class UserActivationController {

    UserService userService;

    public UserActivationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String activeUser(@PathParam("regId") String regId, @PathParam("username") String username){
        System.out.println(username);
        System.out.println(regId);
        User user = userService.getUserByUsername(username);
        if(user.isActive()==true){
            return "redirect:/?userWasActive=true";
        }
        if(user.getToken().equals(regId)){
            user.setActive(true);
            user.setActivationDate(new Timestamp(System.currentTimeMillis()));
            userService.saveUser(user);
            return "redirect:/?userIsActive=true";
        }
        else
            return "redirect:/";
    }


}
