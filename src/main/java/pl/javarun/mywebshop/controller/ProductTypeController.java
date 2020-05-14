package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.UserNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.model.WishList;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private final WishListService wishListService;
    private final ConfigDataService configDataService;

    public ProductTypeController(UserService userService, ProductService productService, TypeService typeService,
                                 CompanyService companyService, ColorPerMaterialService colorPerMaterialService,
                                 RuleService ruleService, WishListService wishListService, ConfigDataService configDataService) {
        this.userService = userService;
        this.productService = productService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.colorPerMaterialService = colorPerMaterialService;
        this.ruleService = ruleService;
        this.wishListService = wishListService;
        this.configDataService=configDataService;
    }

    @GetMapping("/{productType}")
    public ModelAndView getProductsListPage(@PathVariable String productType, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("productsList");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            modelAndView.addObject("userWishList", "");
        } else {
            modelAndView.addObject("userWishList", wishListService.getAllWishListByUserId(user.getId()));
        }
        int newProductPeriod = Integer.valueOf(configDataService.getConfigDataByName("newProductPeriod").getValue());
        modelAndView.addObject("productType", typeService.getTypeByName(productType));
        modelAndView.addObject("productsCounter", productService.getActiveProductsByTypeName(productType).size());
        modelAndView.addObject("products", productService.getActiveProductsByTypeName(productType));
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("newProductPeriod", Timestamp.valueOf(LocalDateTime.now().minus(Period.ofDays(newProductPeriod))));

        return modelAndView;
    }
}
