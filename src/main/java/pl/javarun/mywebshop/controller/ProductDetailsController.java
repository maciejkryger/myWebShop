package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 03.04.2020 13:52
 * *
 * @className: ProductDetailsController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/details")
public class ProductDetailsController {

    private final UserService userService;
    private final ProductService productService;
    private final ColorPerMaterialService colorPerMaterialService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;

    public ProductDetailsController(UserService userService, ProductService productService, ColorPerMaterialService colorPerMaterialService,
                                    TypeService typeService, CompanyService companyService, RuleService ruleService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.productService = productService;
        this.colorPerMaterialService = colorPerMaterialService;
    }

    @GetMapping("/{id}")
    public ModelAndView getProductDetailPage(@PathVariable int id) {
        ModelAndView modelAndView;
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
}
