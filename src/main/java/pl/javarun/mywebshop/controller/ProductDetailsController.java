package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.ProductCounter;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;

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
    private final WishListService wishListService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final ConfigDataService configDataService;
    private final ProductCounterService productCounterService;

    public ProductDetailsController(UserService userService, ProductService productService, ColorPerMaterialService colorPerMaterialService,
                                    TypeService typeService, CompanyService companyService, RuleService ruleService,
                                    WishListService wishListService, WebOrderItemService webOrderItemService,
                                    WebOrderService webOrderService, ConfigDataService configDataService,
                                    ProductCounterService productCounterService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.productService = productService;
        this.colorPerMaterialService = colorPerMaterialService;
        this.wishListService = wishListService;
        this.webOrderItemService = webOrderItemService;
        this.webOrderService = webOrderService;
        this.configDataService=configDataService;
        this.productCounterService=productCounterService;
    }

    @GetMapping("/{id}")
    public ModelAndView getProductDetailPage(@PathVariable int id, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int groupId;
        if (productService.getProductById(id) == null) {
            modelAndView = new ModelAndView("index");
        } else {
            modelAndView = new ModelAndView("productDetail");
            modelAndView.addObject("product", productService.getProductById(id));
            modelAndView.addObject("colors", colorPerMaterialService.getMaterialColorsByMaterialId(productService.getProductById(id).getMaterial().getId()));
            try {
                groupId = productService.getProductById(id).getMainProduct().getId();
                modelAndView.addObject("productsGroup", productService.getActiveProductsGroupByMainId(groupId));
            } catch (Exception ex) {
                modelAndView.addObject("productsGroup", productService.getActiveProductsGroupByMainId(id));
            }
        }

//        countVisitStatistic(id);

        if (user == null) {
            modelAndView.addObject("userWishListProduct", null);
            modelAndView.addObject("productsInBasketSize", 0);
            modelAndView.addObject("userWishListSize", 0);
        } else {
            int userId = user.getId();
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
            modelAndView.addObject("userWishListProduct", wishListService.getWishListByUserIdAndProductId(user.getId(), id));
        }
        int newProductPeriod = Integer.parseInt(configDataService.getConfigDataByName("newProductPeriod").getValue());
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("productType", typeService.getTypeById(productService.getProductById(id).getType().getId()));
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("newProductPeriod", Timestamp.valueOf(LocalDateTime.now().minus(Period.ofDays(newProductPeriod))));
        return modelAndView;
    }


    private void countVisitStatistic(int id){
        ProductCounter productCounter;
        try {
            productCounter= productCounterService.getByProductId(id);
            int visitCounter = productCounter.getVisit();
            visitCounter++;
            productCounter.setVisit(visitCounter);
        }catch (Exception ex){
            productCounter = new ProductCounter();
            productCounter.setProduct(productService.getProductById(id));
            productCounter.setVisit(1);
        }
        productCounterService.save(productCounter);
    }


}
