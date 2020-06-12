package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.Type;
import pl.javarun.mywebshop.model.Counter;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;

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
    private final ConfigDataService configDataService;
    private final WishListService wishListService;
    private final WebOrderItemService webOrderItemService;
    private final WebOrderService webOrderService;
    private final CounterService counterService;

    public ProductTypeController(UserService userService, ProductService productService, TypeService typeService,
                                 CompanyService companyService, ColorPerMaterialService colorPerMaterialService,
                                 RuleService ruleService, WishListService wishListService, ConfigDataService configDataService,
                                 WebOrderItemService webOrderItemService, WebOrderService webOrderService,
                                 CounterService counterService) {
        this.userService = userService;
        this.productService = productService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.colorPerMaterialService = colorPerMaterialService;
        this.ruleService = ruleService;
        this.configDataService = configDataService;
        this.wishListService = wishListService;
        this.webOrderItemService = webOrderItemService;
        this.webOrderService = webOrderService;
        this.counterService = counterService;
    }

    @GetMapping("/{productType}")
    public ModelAndView getProductsListPage(@PathVariable String productType, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("productsList");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            modelAndView.addObject("products", productService.getActiveProductsByTypeName(productType));
            modelAndView.addObject("productsInBasketSize", 0);
            modelAndView.addObject("userWishListSize", 0);
        } else {
            int userId = user.getId();
            modelAndView.addObject("products", productService.getActiveProductsByTypeName(productType));
            try {
                int webOrderId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
                modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderId));
            } catch (OrderNotExistException ex) {
                modelAndView.addObject("productsInBasketSize", 0);
            }
            try {
                modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(user.getId()).size());
            } catch (WishListNotExistException ex) {
                modelAndView.addObject("userWishListSize", 0);
            }
        }
        int newProductPeriod = Integer.parseInt(configDataService.getConfigDataByName("newProductPeriod").getValue());
        modelAndView.addObject("productType", typeService.getTypeByName(productType));
        modelAndView.addObject("productsCounter", productService.getActiveProductsByTypeName(productType).size());
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("newProductPeriod", Timestamp.valueOf(LocalDateTime.now().minus(Period.ofDays(newProductPeriod))));

//        countVisitStatistic(productType);

        return modelAndView;
    }

    private void countVisitStatistic(String productTypename){
        Counter counter;
        System.out.println(productTypename);
        Type type= typeService.getTypeByName(productTypename);
        System.out.println(type.getName());

        try {
            System.out.println("try 1");
            counter = counterService.getByTypeName(type.getId());
            int visitCounter = counter.getVisit();
            visitCounter++;
            counter.setVisit(visitCounter);
            System.out.println("try2");

        }catch (Exception ex){
            System.out.println("catch");
            counter = new Counter();
            System.out.println("dodano nowy");
            counter.setType(type.getId());
            System.out.println("set type");
            counter.setVisit(1);
            System.out.println("set visit");
        }

        System.out.println("save");
        counterService.save(counter);

    }
}
