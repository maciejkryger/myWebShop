package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 03.04.2020 13:57
 * *
 * @className: ProductTypeController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/types")
public class ProductTypeController {

    private final UserService userService;
    private final ProductService productService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final ColorPerMaterialService colorPerMaterialService;
    private final RuleService ruleService;

    public ProductTypeController(UserService userService, ProductService productService, TypeService typeService, CompanyService companyService, ColorPerMaterialService colorPerMaterialService, RuleService ruleService) {
        this.userService = userService;
        this.productService = productService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.colorPerMaterialService = colorPerMaterialService;
        this.ruleService = ruleService;
    }

    @GetMapping("/{productType}")
    public ModelAndView getProductsListPage(@PathVariable String productType) {
        ModelAndView modelAndView = new ModelAndView("productsList");
        modelAndView.addObject("productType", typeService.getTypeByName(productType));
        modelAndView.addObject("productsCounter", productService.getActiveProductsByTypeName(productType).size());
        modelAndView.addObject("products", productService.getActiveProductsByTypeName(productType));
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        return modelAndView;
    }
}
