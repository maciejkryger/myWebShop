package pl.javarun.mywebshop.controller.shopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.AddressNotExistException;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.exception.WishListNotExistException;
import pl.javarun.mywebshop.model.*;
import pl.javarun.mywebshop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

import static pl.javarun.mywebshop.util.InputValidator.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.05.2020 20:33
 * *
 * @className: AddressController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/address")
public class InputAddressController {


    private final TypeService typeService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final WebOrderService webOrderService;
    private final WebOrderItemService webOrderItemService;
    private final AddressService addressService;
    private final WishListService wishListService;


    public InputAddressController(UserService userService, TypeService typeService, CompanyService companyService,
                                  RuleService ruleService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                                  ProductService productService, DeliveryOptionService deliveryOptionService,
                                  PaymentTypeService paymentTypeService, AddressService addressService,
                                  WishListService wishListService) {
        this.typeService = typeService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.webOrderService = webOrderService;
        this.webOrderItemService = webOrderItemService;
        this.addressService = addressService;
        this.wishListService = wishListService;
    }

    @GetMapping()
    public ModelAndView inputAddress(HttpServletRequest httpServletRequest,
                                     @PathParam("streetWrong") boolean streetWrong, @PathParam("streetEmpty") boolean streetEmpty,
                                     @PathParam("houseNoWrong") boolean houseNoWrong, @PathParam("houseNoEmpty") boolean houseNoEmpty,
                                     @PathParam("postCodeWrong") boolean postCodeWrong, @PathParam("postCodeEmpty") boolean postCodeEmpty,
                                     @PathParam("cityWrong") boolean cityWrong, @PathParam("cityEmpty") boolean cityEmpty) {
        ModelAndView modelAndView = new ModelAndView("shopping/addressOrderInput");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int webOrderId = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getId();
        double sumToPay = webOrderItemService.calculateActualSumToPayInUserBasket(webOrderId);
        int deliveryCostsToPay = webOrderService.getOrderByUserIdAndConfirmedFalse(userId).getDeliveryOption().getPrice();
        modelAndView.addObject("sumToPay", sumToPay);
        modelAndView.addObject("deliveryCostsToPay", deliveryCostsToPay);
        try {
            Address address = addressService.getByUser(user);
            modelAndView.addObject("address", address);
        } catch (AddressNotExistException ignored) {}
        if (streetWrong) modelAndView.addObject("streetWrong", true);
        if (streetEmpty) modelAndView.addObject("streetEmpty", true);
        if (houseNoWrong) modelAndView.addObject("houseNoWrong", true);
        if (houseNoEmpty) modelAndView.addObject("houseNoEmpty", true);
        if (postCodeWrong) modelAndView.addObject("postCodeWrong", true);
        if (postCodeEmpty) modelAndView.addObject("postCodeEmpty", true);
        if (cityWrong) modelAndView.addObject("cityWrong", true);
        if (cityEmpty) modelAndView.addObject("cityEmpty", true);
        modelAndView.addObject("productsInBasketSize", webOrderItemService.calculateActualQuantityInUserBasket(webOrderId));
        try {
            modelAndView.addObject("userWishListSize", wishListService.getAllWishListByUserId(user.getId()).size());
        } catch (WishListNotExistException ex) {
            modelAndView.addObject("userWishListSize", 0);
        }
        return modelAndView;
    }

    @PostMapping
    public String saveDeliveryOption(HttpServletRequest httpServletRequest, @RequestParam String street,
                                     @RequestParam String houseNo, @RequestParam(required = false) String flatNo,
                                     @RequestParam String postCode, @RequestParam String city) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
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
        if (!nameValidator(street) || street == null || street.isEmpty() || !houseNoValidator(houseNo) || houseNo.isEmpty() || houseNo == null ||
                !postCodeValidator(postCode) || postCode.isEmpty() || postCode == null || !nameValidator(city) || city.isEmpty() || city == null) {
            String streetAnswerWrong;
            String streetAnswerEmpty;
            String houseNumberAnswerWrong;
            String houseNumberAnswerEmpty;
            String postCodeAnswerWrong;
            String postCodeAnswerEmpty;
            String cityAnswerWrong;
            String cityAnswerEmpty;
            if (!nameValidator(street)) {
                streetAnswerWrong = "streetWrong=true";
            } else {
                streetAnswerWrong = "";
            }
            if (street == null || street.isEmpty()) {
                streetAnswerEmpty = "streetEmpty=true";
            } else {
                streetAnswerEmpty = "";
            }
            if (!houseNoValidator(houseNo)) {
                houseNumberAnswerWrong = "houseNoWrong=true";
            } else {
                houseNumberAnswerWrong = "";
            }
            if (houseNo == null || houseNo.isEmpty()) {
                houseNumberAnswerEmpty = "houseNoEmpty=true";
            } else {
                houseNumberAnswerEmpty = "";
            }
            if (!postCodeValidator(postCode)) {
                postCodeAnswerWrong = "postCodeWrong=true";
            } else {
                postCodeAnswerWrong = "";
            }
            if (postCode == null || postCode.isEmpty()) {
                postCodeAnswerEmpty = "postCodeEmpty=true";
            } else {
                postCodeAnswerEmpty = "";
            }
            if (!nameValidator(city)) {
                cityAnswerWrong = "cityWrong=true";
            } else {
                cityAnswerWrong = "";
            }
            if (city == null || city.isEmpty()) {
                cityAnswerEmpty = "cityEmpty=true";
            } else {
                cityAnswerEmpty = "";
            }
            return "redirect:/address?" + streetAnswerWrong + "&" + streetAnswerEmpty + "&" + houseNumberAnswerWrong + "&" + houseNumberAnswerEmpty +
                    "&" + postCodeAnswerWrong + "&" + postCodeAnswerEmpty + "&" + cityAnswerWrong + "&" + cityAnswerEmpty;
        }
        return "redirect:/confirmation";
    }

}
