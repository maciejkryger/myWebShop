package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.Company;
import pl.javarun.mywebshop.model.Type;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 22:01
 * *
 * @className: CompanyController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/company/")
public class CompanyController {

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


    public CompanyController(ProductService productService, TypeService typeService, MaterialService materialService,
                             MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                             FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                             UserService userService, CompanyService companyService, RuleService ruleService) {
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
    }

    @GetMapping({"/"})
    public ModelAndView editCompanyItem() {
        modelAndView = new ModelAndView("panels/companyItemManager");
        modelAndView.addObject("company", companyService.getCompanyData());
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveCompanyItem(@RequestParam Integer id,
                                  @RequestParam String name, @RequestParam String address,
                                  @RequestParam String postCode, @RequestParam String city,
                                  @RequestParam String email, @RequestParam String phone,
                                  @RequestParam String taxNumber, @RequestParam String accountNumber){
        Company company = companyService.getCompanyData();
        company.setName(name);
        company.setAddress(address);
        company.setPostCode(postCode);
        company.setCity(city);
        company.setEmail(email);
        company.setPhone(phone);
        company.setTaxNumber(taxNumber);
        company.setAccountNumber(accountNumber);
        companyService.save(company);
        return "redirect:/panels/data/companies";
    }
}

