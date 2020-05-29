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
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 04.04.2020 12:28
 * *
 * @className: BasketController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/search")
public class SearchController {

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

    public SearchController(UserService userService, TypeService typeService, CompanyService companyService,
                            RuleService ruleService, MaterialColorService materialColorService,
                            MaterialService materialService, FasteningTypeService fasteningTypeService,
                            ProductService productService, WebOrderService webOrderService, WebOrderItemService webOrderItemService,
                            WishListService wishListService) {
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
    }

    @GetMapping()
    public ModelAndView searchWindow(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        modelAndView.addObject("materialColors", materialColorService.getAllMaterialColors());
        modelAndView.addObject("materials", materialService.getAllMaterials());
        modelAndView.addObject("fasteningTypes", fasteningTypeService.getAllFasteningTypes());
        int userId = 0;
        int webOrderId = 0;
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

    @PostMapping
    public ModelAndView getSearchList(@RequestParam(required = false) Integer materialId, @RequestParam(required = false) Integer materialColorId,
                                      @RequestParam(required = false) Integer typeId, @RequestParam(required = false) Integer fasteningTypeId,
                                      @RequestParam(required = false) Integer priceFrom, @RequestParam(required = false) Integer priceTo) {
        ModelAndView modelAndView = new ModelAndView("searchProductsList");
        modelAndView.addObject("company", companyService.getCompanyData());
        modelAndView.addObject("productTypesList", typeService.getAllTypes());
        modelAndView.addObject("rules", ruleService.getAllRules());
        products = getSearchProducts(materialId, materialColorId, typeId, fasteningTypeId, priceFrom, priceTo);
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    private List<Product> getSearchProducts(Integer materialId, Integer materialColorId, Integer typeId,
                                            Integer fasteningTypeId, Integer priceFrom, Integer priceTo) {
        products = null;
        if (materialId == null && materialColorId == null && typeId == null && fasteningTypeId == null && priceFrom == null && priceTo == null) {
            products = productService.getAllActiveProducts();
        }
        if (materialId != null && materialColorId == null && typeId == null && fasteningTypeId == null && priceFrom == null) {
            products = productService.getActiveProductsByMaterialId(materialId);
        }
        if (materialId == null && materialColorId != null && typeId == null && fasteningTypeId == null && priceFrom == null && priceTo == null) {
            products = productService.getActiveProductsByMaterialColorId(materialColorId);
        }
        if (materialId == null && materialColorId == null && typeId != null && fasteningTypeId == null && priceFrom == null && priceTo == null) {
            products = productService.getActiveProductsByTypeId(typeId);
        }
        if (materialId == null && materialColorId == null && typeId == null && fasteningTypeId != null && priceFrom == null && priceTo == null) {
            products = productService.getActiveProductsByFasteningTypeId(fasteningTypeId);
        }
        if (materialId == null && materialColorId == null && typeId == null && fasteningTypeId == null && priceFrom != null && priceTo == null) {
            products = productService.getActiveProductsByPriceAbove(priceFrom);
        }
        if (materialId == null && materialColorId == null && typeId == null && fasteningTypeId == null && priceFrom == null && priceTo != null) {
            products = productService.getActiveProductsByPriceUpTo(priceTo);
        }
        if (materialId == null && materialColorId == null && typeId == null && fasteningTypeId == null && priceFrom != null && priceTo != null) {
            products = productService.getActiveProductsByPriceBetween(priceFrom, priceTo);
        }
        //-------------------------------------------------------------------------------------
        if (materialId != null && materialColorId != null && typeId == null && fasteningTypeId == null && priceFrom == null && priceTo == null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorId(materialId, materialColorId);
        }
        if (materialId != null && materialColorId != null && typeId != null && fasteningTypeId == null && priceFrom == null && priceTo == null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorIdAndTypeId(materialId, materialColorId, typeId);
        }
        if (materialId != null && materialColorId != null && typeId != null && fasteningTypeId != null && priceFrom == null && priceTo == null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorIdAndTypeIdAndFasteningTypeId(materialId, materialColorId, typeId, fasteningTypeId);
        }
        if (materialId != null && materialColorId != null && typeId != null && fasteningTypeId != null && priceFrom != null && priceTo == null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorIdAndTypeIdAndFasteningTypeIdAndPriceAbove(materialId, materialColorId, typeId, fasteningTypeId, priceFrom);
        }
        if (materialId != null && materialColorId != null && typeId != null && fasteningTypeId != null && priceFrom == null && priceTo != null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorIdAndTypeIdAndFasteningTypeIdAndPriceUpTo(materialId, materialColorId, typeId, fasteningTypeId, priceTo);
        }
        if (materialId != null && materialColorId != null && typeId != null && fasteningTypeId != null && priceFrom != null && priceTo != null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorIdAndTypeIdAndFasteningTypeIdAndPriceBetween(materialId, materialColorId, typeId, fasteningTypeId, priceFrom, priceTo);
        }
        //---------------------------------------------------------------------------------------------
        if (materialId != null && materialColorId != null && typeId == null && fasteningTypeId != null && priceFrom == null && priceTo == null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorIdAndFasteningTypeId(materialId, materialColorId, fasteningTypeId);
        }
        if (materialId != null && materialColorId != null && typeId == null && fasteningTypeId != null && priceFrom != null && priceTo != null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorIdAndFasteningTypeIdAndPriceBetween(materialId, materialColorId, fasteningTypeId, priceFrom, priceTo);
        }
        if (materialId != null && materialColorId != null && typeId == null && fasteningTypeId != null && priceFrom == null && priceTo != null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorIdAndFasteningTypeIdAndPriceUpTo(materialId, materialColorId, fasteningTypeId, priceTo);
        }
        if (materialId != null && materialColorId != null && typeId == null && fasteningTypeId != null && priceFrom != null && priceTo == null) {
            products = productService.getActiveProductsByMaterialIdAndMaterialColorIdAndFasteningTypeIdAndPriceAbove(materialId, materialColorId, fasteningTypeId, priceFrom);
        }
        //------------------------------------------------------------------------------------------------
        if (materialId == null && materialColorId == null && typeId != null && fasteningTypeId != null && priceFrom == null && priceTo == null) {
            products = productService.getActiveProductsByTypeIdAndFasteningId(typeId, fasteningTypeId);
        }
        if (materialId == null && materialColorId == null && typeId != null && fasteningTypeId != null && priceFrom != null && priceTo == null) {
            products = productService.getActiveProductsByTypeIdAndFasteningIdAndPriceAbove(typeId, fasteningTypeId, priceFrom);
        }
        if (materialId == null && materialColorId == null && typeId != null && fasteningTypeId != null && priceFrom == null && priceTo != null) {
            products = productService.getActiveProductsByTypeIdAndFasteningIdAndPriceUpTo(typeId, fasteningTypeId, priceTo);
        }
        if (materialId == null && materialColorId == null && typeId != null && fasteningTypeId != null && priceFrom != null && priceTo != null) {
            products = productService.getActiveProductsByTypeIdAndFasteningIdAndPriceBetween(typeId, fasteningTypeId, priceFrom, priceTo);
        }
        //----------------------------------------------------------------------------------------------
        if (materialId == null && materialColorId != null && typeId != null && fasteningTypeId != null && priceFrom != null && priceTo != null) {
            products = productService.getActiveProductsByMaterialColorIdAndTypeIdAndFasteningIdAndPriceBetween(materialColorId, typeId, fasteningTypeId, priceFrom, priceTo);
        }
        if (materialId != null && materialColorId == null && typeId != null && fasteningTypeId != null && priceFrom != null && priceTo != null) {
            products = productService.getActiveProductsByMaterialIdAndTypeIdAndFasteningIdAndPriceBetween(materialId, typeId, fasteningTypeId, priceFrom, priceTo);
        }
        if (materialId != null && materialColorId == null && typeId == null && fasteningTypeId != null && priceFrom != null && priceTo != null) {
            products = productService.getActiveProductsByMaterialIdAndFasteningIdAndPriceBetween(materialId, fasteningTypeId, priceFrom, priceTo);
        }
        if (materialId != null && materialColorId == null && typeId == null && fasteningTypeId == null && priceFrom != null && priceTo != null) {
            products = productService.getActiveProductsByMaterialIdAndPriceBetween(materialId, priceFrom, priceTo);
        }
        if (materialId != null && materialColorId == null && typeId == null && fasteningTypeId == null && priceFrom != null && priceTo == null) {
            products = productService.getActiveProductsByMaterialIdAndPriceAbove(materialId, priceFrom);
        }
        if (materialId != null && materialColorId == null && typeId == null && fasteningTypeId == null && priceFrom == null && priceTo != null) {
            products = productService.getActiveProductsByMaterialIdAndPriceUpTo(materialId, priceTo);
        }
        return products;
    }
}
