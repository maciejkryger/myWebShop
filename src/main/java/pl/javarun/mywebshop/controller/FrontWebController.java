package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.Product;
import pl.javarun.mywebshop.service.*;

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
public class FrontWebController {

    private final UserService userService;
    private final ProductService productService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final ColorPerMaterialService colorPerMaterialService;
    private final RuleService ruleService;

    public FrontWebController(UserService userService, ProductService productService, TypeService typeService, CompanyService companyService, ColorPerMaterialService colorPerMaterialService, RuleService ruleService) {
        this.userService = userService;
        this.productService = productService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.colorPerMaterialService = colorPerMaterialService;
        this.ruleService = ruleService;
    }

    ModelAndView modelAndView;

    @GetMapping("/login")
    public ModelAndView loginUsername(@RequestParam(required = false) String error){
        modelAndView=new ModelAndView("login");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if(error != null) {
            modelAndView.addObject("error", true);
        }
        return modelAndView;
    }

//    @PostMapping("/login")
//    public String loginUsername(@RequestParam String username, @RequestParam String password){
//        System.out.println("username: "+ username);
//        System.out.println("password: "+ password);
//        if(username.equals("admin"))
//        return "redirect:/panels/superpanel";
//        else return "redirect:/";
//    }

    @GetMapping(value = {"", "/{id}"})
    public ModelAndView getIndexPage(@PathVariable(required = false) String id) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        return modelAndView;
    }


    @GetMapping("/types/{productType}")
    public ModelAndView getProductsListPage(@PathVariable String productType) {
        modelAndView = new ModelAndView("productsList");
        modelAndView.addObject("productType", typeService.getTypeByName(productType));
        modelAndView.addObject("productsCounter", productService.getProductsByTypeName(productType).size());
        modelAndView.addObject("products", productService.getProductsByTypeName(productType));
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getProductDetailPage(@PathVariable int id) {
        if (productService.getProductById(id) == null) {
            modelAndView = new ModelAndView("index");
        } else {
            modelAndView = new ModelAndView("productDetail");
            modelAndView.addObject("product", productService.getProductById(id));
            modelAndView.addObject("colors", colorPerMaterialService.getMaterialColorsByMaterialId(productService.getProductById(id).getMaterial().getId()));
        }
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("productType",typeService.getTypeById(productService.getProductById(id).getType().getId()));
        modelAndView.addObject("rules", ruleService.getAllRules());
        return modelAndView;
    }

    @GetMapping("/rules/{subject}")
    public ModelAndView showRegulationPage(@PathVariable String subject) {
        if (ruleService.getRuleByName(subject) == null) {
            modelAndView = new ModelAndView("index");
        } else {
            modelAndView = new ModelAndView("rules");
            modelAndView.addObject("rule", ruleService.getRuleByName(subject));
            modelAndView.addObject("text", ruleService.getRuleByName(subject).getDescriptionPl());
        }
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        return modelAndView;
    }

}
