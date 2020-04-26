package pl.javarun.mywebshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.javarun.mywebshop.service.*;
import pl.javarun.mywebshop.util.EmailContactForm;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 24.04.2020 17:13
 * *
 * @className: ContactController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/contactMessage")
public class ContactController {

    CompanyService companyService;


    public ContactController(CompanyService companyService) {
        this.companyService= companyService;
    }

    @PostMapping()
    public String contactMail(@RequestParam String name, @RequestParam String email,
                              @RequestParam String subject, @RequestParam String content){
        EmailContactForm emailContactForm = new EmailContactForm();
        emailContactForm.send(companyService.getCompanyData().getEmail(), name,email,subject,content);
        return "redirect:/";
    }



}
