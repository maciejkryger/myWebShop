package pl.javarun.mywebshop.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.AddressNotExistException;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.Address;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.*;

import javax.websocket.server.PathParam;

import static pl.javarun.mywebshop.util.InputValidator.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.05.2020 18:42
 * *
 * @className: ChangeUserAddressController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/changeUserAddress")
public class ChangeUserAddressController {

    private final UserService userService;
    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final RoleService roleService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final WishListService wishListService;
    private final AddressService addressService;

    public ChangeUserAddressController(UserService userService, TypeService typeService, CompanyService companyService,
                                       RuleService ruleService, RoleService roleService, WishListService wishListService,
                                       WebOrderItemService webOrderItemService, WebOrderService webOrderService,
                                       AddressService addressService) {
        this.userService = userService;
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.roleService = roleService;
        this.wishListService = wishListService;
        this.webOrderItemService = webOrderItemService;
        this.webOrderService = webOrderService;
        this.addressService = addressService;
    }


    @GetMapping()
    public ModelAndView changeUserAddressPage(@SessionAttribute User user,
                                              @PathParam("success") boolean success, @PathParam("username") String username,
                                              @PathParam("streetWrong") boolean streetWrong,
                                              @PathParam("houseNoWrong") boolean houseNoWrong,
                                              @PathParam("postCodeWrong") boolean postCodeWrong,
                                              @PathParam("cityWrong") boolean cityWrong ) {
        ModelAndView modelAndView = new ModelAndView("account/changeUserAddress");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        if (streetWrong) modelAndView.addObject("streetWrong", true);
        if (houseNoWrong) modelAndView.addObject("houseNoWrong", true);
        if (postCodeWrong) modelAndView.addObject("postCodeWrong", true);
        if (cityWrong) modelAndView.addObject("cityWrong", true);
        Address address;
        if (user != null) {
            modelAndView.addObject("user", userService.getUserByUsername(user.getUsername()));
        }
        try {
            address = addressService.getByUser(user);

        } catch (AddressNotExistException ex) {
            address = new Address();
        }
        modelAndView.addObject("address", address);

        if (success) modelAndView.addObject("success", true);
        int userId = user.getId();
        try {
            int webOrderId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
            modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderId));
        } catch (OrderNotExistException ex) {
            modelAndView.addObject("productsInBasketSize", 0);
        }
        try {
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(userId).size());
        } catch (WishListNotExistException ex) {
            modelAndView.addObject("userWishListSize", 0);
        }
        return modelAndView;
    }

    @PostMapping()
    public String changeUserDetails(@SessionAttribute User user,
                                    @RequestParam(required = false) String street, @RequestParam(required = false) String houseNo,
                                    @RequestParam(required = false) String flatNo, @RequestParam(required = false) String postCode,
                                    @RequestParam(required = false) String city) {
        if (!nameValidator(street) ||  !houseNoValidator(houseNo)  || !postCodeValidator(postCode) || !nameValidator(city)) {
            String streetAnswerWrong;
            String houseNumberAnswerWrong;
            String postCodeAnswerWrong;
            String cityAnswerWrong;
            if (!nameValidator(street)) {
                streetAnswerWrong = "streetWrong=true";
            } else {
                streetAnswerWrong = "";
            }

            if (!houseNoValidator(houseNo)) {
                houseNumberAnswerWrong = "houseNoWrong=true";
            } else {
                houseNumberAnswerWrong = "";
            }

            if (!postCodeValidator(postCode)) {
                postCodeAnswerWrong = "postCodeWrong=true";
            } else {
                postCodeAnswerWrong = "";
            }

            if (!nameValidator(city)) {
                cityAnswerWrong = "cityWrong=true";
            } else {
                cityAnswerWrong = "";
            }

            return "redirect:/changeUserAddress?" + streetAnswerWrong + "&"  + houseNumberAnswerWrong + "&" + postCodeAnswerWrong +
                    "&" + cityAnswerWrong ;
        }

        Address address;
        try {
            address = addressService.getByUser(user);
        } catch (AddressNotExistException ex) {
            address = new Address();
        }
        address.setUser(user);
        address.setStreet(street);
        address.setHouseNo(houseNo);
        address.setFlatNo(flatNo);
        address.setPostCode(postCode);
        address.setCity(city);
        addressService.save(address);
        return "redirect:/changeUserAddress?success=true";
    }

}
