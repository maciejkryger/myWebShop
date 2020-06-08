package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.*;
import pl.javarun.mywebshop.search.SearchColorPerMaterialModelOption;
import pl.javarun.mywebshop.search.SearchProductModelOption;
import pl.javarun.mywebshop.search.SearchUserModelOption;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 17:41
 * *
 * @className: SuperPanelController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/")
public class SuperPanelController {

    private ModelAndView modelAndView;
    private final ProductService productService;
    private final TypeService typeService;
    private final MaterialService materialService;
    private final MaterialColorService materialColorService;
    private final FasteningTypeService fasteningTypeService;
    private final FasteningColorService fasteningColorService;
    private final MakingTechniqueService makingTechniqueService;
    private final UserService userService;
    private final CompanyService companyService;
    private final RuleService ruleService;
    private final RoleService roleService;
    private final ColorPerMaterialService colorPerMaterialService;
    private final ConfigDataService configDataService;
    private final PaymentMethodService paymentMethodService;
    private final DeliveryOptionService deliveryOptionService;
    private final PromoCodeService promoCodeService;


    public SuperPanelController(ProductService productService, TypeService typeService, MaterialService materialService,
                                MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                                FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                                UserService userService, CompanyService companyService, RuleService ruleService,
                                RoleService roleService, ColorPerMaterialService colorPerMaterialService,
                                ConfigDataService configDataService,PaymentMethodService paymentMethodService,
                                DeliveryOptionService deliveryOptionService,PromoCodeService promoCodeService) {
        this.productService = productService;
        this.typeService = typeService;
        this.materialService = materialService;
        this.materialColorService = materialColorService;
        this.fasteningTypeService = fasteningTypeService;
        this.fasteningColorService = fasteningColorService;
        this.makingTechniqueService = makingTechniqueService;
        this.userService = userService;
        this.companyService = companyService;
        this.ruleService = ruleService;
        this.roleService = roleService;
        this.colorPerMaterialService = colorPerMaterialService;
        this.configDataService=configDataService;
        this.paymentMethodService=paymentMethodService;
        this.deliveryOptionService=deliveryOptionService;
        this.promoCodeService=promoCodeService;
    }


    @GetMapping("/superpanel")
    public ModelAndView openSuperPanel() {
        modelAndView = new ModelAndView("panels/superPanel");
        return modelAndView;
    }

    @GetMapping("/data/{table}")
    public ModelAndView openProductsDatabase(@PathVariable String table,
                                             @RequestParam(required = false) String searchWhat,
                                             @RequestParam(required = false) String searchBy) {
        boolean searchNullEmptyChecker = searchWhat == null || searchBy == null || searchWhat.isEmpty() || searchBy.isEmpty();
        switch (table) {
            case "products":
                modelAndView = new ModelAndView("panels/productTableManager");
                modelAndView.addObject("searchByOptions", SearchProductModelOption.values());
                if (searchNullEmptyChecker) {
                    modelAndView.addObject("isFiltered", false);
                    modelAndView.addObject("products", productService.getAllProducts());
                } else {
                    modelAndView.addObject("isFiltered", true);
                    modelAndView.addObject("products", productService.searchProducts(searchWhat, SearchProductModelOption.valueOf(searchBy)));
                }
                modelAndView.addObject("tableURL", "panels/data/product");
                break;
            case "types":
                modelAndView = new ModelAndView("panels/typeTableManager");
                modelAndView.addObject("types", typeService.getAllTypes());
                break;
            case "materials":
                modelAndView = new ModelAndView("panels/materialTableManager");
                modelAndView.addObject("materials", materialService.getAllMaterials());
                break;
            case "materialColors":
                modelAndView = new ModelAndView("panels/materialColorTableManager");
                modelAndView.addObject("materialColors", materialColorService.getAllMaterialColors());
                break;
            case "fasteningTypes":
                modelAndView = new ModelAndView("panels/fasteningTypeTableManager");
                modelAndView.addObject("fasteningTypes", fasteningTypeService.getAllFasteningTypes());
                break;
            case "fasteningColors":
                modelAndView = new ModelAndView("panels/fasteningColorTableManager");
                modelAndView.addObject("fasteningColors", fasteningColorService.getAllFasteningColors());
                break;
            case "makingTechniques":
                modelAndView = new ModelAndView("panels/makingTechniqueTableManager");
                modelAndView.addObject("makingTechniques", makingTechniqueService.getAllMakingTechniques());
                break;
            case "users":
                modelAndView = new ModelAndView("panels/userTableManager");
                modelAndView.addObject("searchByOptions", SearchUserModelOption.values());
                if (searchNullEmptyChecker) {
                    modelAndView.addObject("isFiltered", false);
                    modelAndView.addObject("users", userService.getAllUsers());
                } else {
                    modelAndView.addObject("isFiltered", true);
                    modelAndView.addObject("users", userService.searchUser(searchWhat, SearchUserModelOption.valueOf(searchBy)));
                }
                modelAndView.addObject("tableURL", "panels/data/user");
                break;
            case "companies":
                modelAndView = new ModelAndView("panels/companyTableManager");
                modelAndView.addObject("companies", companyService.getAllCompanies());
                break;
            case "rules":
                modelAndView = new ModelAndView("panels/ruleTableManager");
                modelAndView.addObject("rules", ruleService.getAllRules());
                break;
            case "roles":
                modelAndView = new ModelAndView("panels/roleTableManager");
                modelAndView.addObject("roles", roleService.getAllRoles());
                break;
            case "colorPerMaterials":
                modelAndView = new ModelAndView("panels/colorPerMaterialTableManager");
                modelAndView.addObject("searchByOptions", SearchColorPerMaterialModelOption.values());
                if (searchNullEmptyChecker) {
                    modelAndView.addObject("isFiltered", false);
                    modelAndView.addObject("colorPerMaterialList", colorPerMaterialService.getAllColorPerMaterial());
                } else {
                    modelAndView.addObject("isFiltered", true);
                    modelAndView.addObject("colorPerMaterialList", colorPerMaterialService.searchColorPerMaterial(searchWhat, SearchColorPerMaterialModelOption.valueOf(searchBy)));
                }
                modelAndView.addObject("tableURL", "panels/data/colorPerMaterial");
                break;
            case "config":
                modelAndView = new ModelAndView("panels/configManager");
                modelAndView.addObject("configs", configDataService.getAllConfigs());
                break;
            case "paymentMethods":
                modelAndView = new ModelAndView("panels/paymentMethodTableManager");
                modelAndView.addObject("paymentMethods", paymentMethodService.getAllPaymentMethod());
                break;
            case "deliveryOptions":
                modelAndView = new ModelAndView("panels/deliveryOptionTableManager");
                modelAndView.addObject("deliveryOptions", deliveryOptionService.getAllDeliveryOptions());
                break;
            case "promoCodes":
                modelAndView = new ModelAndView("panels/promoCodeTableManager");
                modelAndView.addObject("promoCodes", promoCodeService.getAllPromoCodes());
                break;
            default:
                modelAndView = new ModelAndView("panels/superPanel");
                break;
        }
        return modelAndView;
    }

    @PostMapping("data/colorPerMaterials")
    public String changeActiveC(@RequestParam Integer id, @RequestParam boolean active, @RequestParam(required = false) String searchWhat,
                                @RequestParam(required = false) String searchBy) {

        ColorPerMaterial colorPerMaterial = colorPerMaterialService.getColorPerMaterialById(id);
        colorPerMaterial.setActive(active);
        colorPerMaterialService.save(colorPerMaterial);
        if (searchWhat == null || searchBy == null) {
            return "redirect:/panels/data/colorPerMaterials";
        }
        return "redirect:/panels/data/colorPerMaterials/?searchWhat=" + searchWhat + "&searchBy=" + searchBy;
    }

    @PostMapping("data/products")
    public String changeActiveP(@RequestParam Integer id, @RequestParam boolean active, @RequestParam(required = false) String searchWhat,
                                @RequestParam(required = false) String searchBy) {
        Product product = productService.getProductById(id);
        product.setActive(active);
        productService.save(product);
        if (searchWhat == null || searchBy == null) {
            return "redirect:/panels/data/products";
        }
        return "redirect:/panels/data/products/?searchWhat=" + searchWhat + "&searchBy=" + searchBy;
    }


}
