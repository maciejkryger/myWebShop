package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.Product;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 09.06.2020 22:13
 * *
 * @className: NewProductController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/new")
public class NewProductController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final MaterialColorService materialColorService;
    private final MaterialService materialService;
    private final FasteningTypeService fasteningTypeService;
    private final ProductService productService;
    private List<Product> products;
    private final WishListService wishListService;
    private final WebOrderItemService webOrderItemService;
    private final WebOrderService webOrderService;
    private final ConfigDataService configDataService;

    public NewProductController(UserService userService, TypeService typeService, CompanyService companyService,
                                RuleService ruleService, MaterialColorService materialColorService,
                                MaterialService materialService, FasteningTypeService fasteningTypeService,
                                ProductService productService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                                WishListService wishListService, ConfigDataService configDataService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.materialColorService = materialColorService;
        this.materialService = materialService;
        this.fasteningTypeService = fasteningTypeService;
        this.productService = productService;
        this.wishListService = wishListService;
        this.webOrderItemService = webOrderItemService;
        this.webOrderService = webOrderService;
        this.configDataService=configDataService;
    }

    @GetMapping()
    public ModelAndView searchWindow(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("newList");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("newProducts",productService.getActiveProductsByNewPeriod());
        int newProductPeriod = Integer.parseInt(configDataService.getConfigDataByName("newProductPeriod").getValue());
        modelAndView.addObject("newProductPeriod", Timestamp.valueOf(LocalDateTime.now().minus(Period.ofDays(newProductPeriod))));
        int userId;
        int webOrderId;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            modelAndView.addObject("productsInBasketSize", 0);
            modelAndView.addObject("userWishListSize", 0);
        } else {
            userId = user.getId();
            try {
                webOrderId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
                modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderId));
            } catch (OrderNotExistException ex) {
                modelAndView.addObject("productsInBasketSize", 0);
            }
            try {
                modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(userId).size());
            } catch (WishListNotExistException ex) {
                modelAndView.addObject("userWishListSize", 0);
            }
        }
        return modelAndView;
    }




}
