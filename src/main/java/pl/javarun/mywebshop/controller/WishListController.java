package pl.javarun.mywebshop.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.model.WishList;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 12:28
 * *
 * @className: WishListController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/wishList")
public class WishListController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final WishListService wishListService;
    private final ProductService productService;


    public WishListController(UserService userService, TypeService typeService, CompanyService companyService,
                              RuleService ruleService, WishListService wishListService, ProductService productService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.wishListService = wishListService;
        this.productService = productService;
    }

    @GetMapping()
    public ModelAndView showWishList(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("wishList");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("userWishList", wishListService.getAllWishListByUserId(user.getId()));

        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("userWishList", wishListService.getAllWishListByUserId(user.getId()));
        return modelAndView;
    }

    @PostMapping("/addFromList")
    public String addProductToWishListFromMainList(@RequestParam(required = false) Integer productId,
                                                   HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("wishList");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId =(Integer)user.getId();
        if (userId != null && productId != null) {
            WishList wishList;
            if (wishListService.getWishListByUserIdAndProductId(userId, productId) == null) {
                wishList = new WishList();
            } else {
                wishList = wishListService.getWishListByUserIdAndProductId(userId, productId);
            }
            wishList.setProduct(productService.getProductById(productId));
            wishList.setUser(userService.getUserById(userId));
            wishListService.save(wishList);

            String typeName = productService.getProductById(productId).getType().getName();
            return "redirect:/types/" + typeName;
        }
        return "redirect:/";
    }


    @PostMapping("/removeFromList")
    public String removeProductToWishListFromMainList(@RequestParam(required = false) Integer productId,
                                                      HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("wishList");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId =(Integer)user.getId();
        if ((Integer)user.getId() != null && productId != null) {
            WishList wishList = wishListService.getWishListByUserIdAndProductId(userId, productId);
            wishListService.delete(wishList);
            String typeName = productService.getProductById(productId).getType().getName();
            return "redirect:/types/" + typeName;
        }
        return "redirect:/";
    }

    @PostMapping("/removeFromWishList")
    public String removeProductToWishListFromWishList(@RequestParam(required = false) Integer productId,
                                                      HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId =(Integer)user.getId();
        WishList wishList = wishListService.getWishListByUserIdAndProductId(userId, productId);
        wishListService.delete(wishList);
        return "redirect:/wishList";

    }


    @PostMapping("/addFromDetail")
    public String addProductToWishListFromProductDetail(@RequestParam(required = false) int productId,
                                                        HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId =(Integer)user.getId();
        if (wishListService.getWishListByUserIdAndProductId(user.getId(), productId) == null) {
            WishList wishList;
            if (wishListService.getWishListByUserIdAndProductId(userId, productId) == null) {
                wishList = new WishList();
            } else {
                wishList = wishListService.getWishListByUserIdAndProductId(userId, productId);
            }
            wishList.setProduct(productService.getProductById(productId));
            wishList.setUser(userService.getUserById(user.getId()));
            wishListService.save(wishList);
        }
        return "redirect:/details/" + productId;
    }

    @PostMapping("/removeFromDetail")
    public String removeProductToWishListFromProductDetail(@RequestParam(required = false) int productId,
                                                           HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        WishList wishList = wishListService.getWishListByUserIdAndProductId(user.getId(), productId);
        wishListService.delete(wishList);
        return "redirect:/details/" + productId;
    }

}
