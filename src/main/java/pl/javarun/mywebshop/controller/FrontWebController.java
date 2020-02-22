package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.service.CompanyService;
import pl.javarun.mywebshop.service.ProductService;
import pl.javarun.mywebshop.service.TypeService;
import pl.javarun.mywebshop.service.UserService;

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

    public FrontWebController(UserService userService, ProductService productService, TypeService typeService, CompanyService companyService) {
        this.userService = userService;
        this.productService = productService;
        this.typeService=typeService;
        this.companyService=companyService;
    }

    @GetMapping("")
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("company",companyService.getCompanyData());
        modelAndView.addObject("productTypesList",typeService.getAllTypes());
        return modelAndView;
    }

    @GetMapping("/{productType}")
    public ModelAndView getProductsListPage(@PathVariable String productType) {
        ModelAndView modelAndView = new ModelAndView("productsList");
        modelAndView.addObject("company",companyService.getCompanyData());
        modelAndView.addObject("productTypesList",typeService.getAllTypes());
        modelAndView.addObject("productType",typeService.getTypeByName(productType));
        modelAndView.addObject("productsCounter", productService.getProductsByTypeName(productType).size());
        modelAndView.addObject("products", productService.getProductsByTypeName(productType));
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getProductDetailPage(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("productDetail");
        modelAndView.addObject("company",companyService.getCompanyData());
        modelAndView.addObject("productTypesList",typeService.getAllTypes());
        modelAndView.addObject("product",productService.getProductById(id));
        return modelAndView;
    }

    @GetMapping("/regulations")
    public ModelAndView showRegulationPage() {
        ModelAndView modelAndView = new ModelAndView("regulations");
        modelAndView.addObject("company",companyService.getCompanyData());
        modelAndView.addObject("productTypesList",typeService.getAllTypes());
        return modelAndView;
    }

    @GetMapping("/payment")
    public ModelAndView showPaymentPage() {
        ModelAndView modelAndView = new ModelAndView("payment");
        modelAndView.addObject("company",companyService.getCompanyData());
        modelAndView.addObject("productTypesList",typeService.getAllTypes());
        return modelAndView;
    }

    @GetMapping("/shipping")
    public ModelAndView showShippingCostPage() {
        ModelAndView modelAndView = new ModelAndView("shipping");
        modelAndView.addObject("company",companyService.getCompanyData());
        modelAndView.addObject("productTypesList",typeService.getAllTypes());
        return modelAndView;
    }

    @GetMapping("/return")
    public ModelAndView showReturnRulesPage() {
        ModelAndView modelAndView = new ModelAndView("return");
        modelAndView.addObject("company",companyService.getCompanyData());
        modelAndView.addObject("productTypesList",typeService.getAllTypes());
        return modelAndView;
    }
}
