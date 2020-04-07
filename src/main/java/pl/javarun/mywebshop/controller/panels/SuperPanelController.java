package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.*;
import pl.javarun.mywebshop.search.SearchColorPerMaterialModelOption;
import pl.javarun.mywebshop.search.SearchProductModelOption;
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

    ModelAndView modelAndView;
    ProductService productService;
    TypeService typeService;
    MaterialService materialService;
    MaterialColorService materialColorService;
    FasteningTypeService fasteningTypeService;
    FasteningColorService fasteningColorService;
    MakingTechniqueService makingTechniqueService;
    UserService userService;
    CompanyService companyService;
    RuleService ruleService;
    RoleService roleService;
    ColorPerMaterialService colorPerMaterialService;


    public SuperPanelController(ProductService productService, TypeService typeService, MaterialService materialService,
                                MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                                FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                                UserService userService, CompanyService companyService, RuleService ruleService,
                                RoleService roleService, ColorPerMaterialService colorPerMaterialService) {
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
    }


    @GetMapping("/superpanel")
    public ModelAndView openSuperPanel() {
        modelAndView = new ModelAndView("panels/superPanel");
        return modelAndView;
    }

    @GetMapping("/data/{table}")
    public ModelAndView openProductsDatabase(@PathVariable(required = true) String table,
                                             @RequestParam(required = false) String searchWhat,
                                             @RequestParam(required = false) String searchBy) {
        if (table.equals("products")) {
            modelAndView = new ModelAndView("panels/productTableManager");
            modelAndView.addObject("searchByOptions", SearchProductModelOption.values());
            if (searchWhat == null || searchBy == null || searchWhat.isEmpty() || searchBy.isEmpty()) {
                modelAndView.addObject("isFiltered", false);
                modelAndView.addObject("products", productService.getAllProducts());
            } else {
                modelAndView.addObject("isFiltered", true);
                modelAndView.addObject("products", productService.searchProducts(searchWhat, SearchProductModelOption.valueOf(searchBy)));
            }
            modelAndView.addObject("tableURL","panels/data/product");
        } else if (table.equals("types")) {
            modelAndView = new ModelAndView("panels/typeTableManager");
            modelAndView.addObject("types", typeService.getAllTypes());
        } else if (table.equals("materials")) {
            modelAndView = new ModelAndView("panels/materialTableManager");
            modelAndView.addObject("materials", materialService.getAllMaterials());
        } else if (table.equals("materialColors")) {
            modelAndView = new ModelAndView("panels/materialColorTableManager");
            modelAndView.addObject("materialColors", materialColorService.getAllMaterialColors());
        } else if (table.equals("fasteningTypes")) {
            modelAndView = new ModelAndView("panels/fasteningTypeTableManager");
            modelAndView.addObject("fasteningTypes", fasteningTypeService.getAllFasteningTypes());
        } else if (table.equals("fasteningColors")) {
            modelAndView = new ModelAndView("panels/fasteningColorTableManager");
            modelAndView.addObject("fasteningColors", fasteningColorService.getAllFasteningColors());
        } else if (table.equals("makingTechniques")) {
            modelAndView = new ModelAndView("panels/makingTechniqueTableManager");
            modelAndView.addObject("makingTechniques", makingTechniqueService.getAllMakingTechniques());
        } else if (table.equals("users")) {
            modelAndView = new ModelAndView("panels/userTableManager");
            modelAndView.addObject("users", userService.getAllUsers());
        } else if (table.equals("companies")) {
            modelAndView = new ModelAndView("panels/companyTableManager");
            modelAndView.addObject("companies", companyService.getAllCompanies());
        } else if (table.equals("rules")) {
            modelAndView = new ModelAndView("panels/ruleTableManager");
            modelAndView.addObject("rules", ruleService.getAllRules());
        } else if (table.equals("roles")) {
            modelAndView = new ModelAndView("panels/roleTableManager");
            modelAndView.addObject("roles", roleService.getAllRoles());
        } else if (table.equals("colorPerMaterials")) {
            modelAndView = new ModelAndView("panels/colorPerMaterialTableManager");
            modelAndView.addObject("searchByOptions", SearchColorPerMaterialModelOption.values());
            if (searchWhat == null || searchBy == null || searchWhat.isEmpty() || searchBy.isEmpty()) {
                modelAndView.addObject("isFiltered", false);
                modelAndView.addObject("colorPerMaterialList", colorPerMaterialService.getAllColorPerMaterial());
            } else {
                modelAndView.addObject("isFiltered", true);
                modelAndView.addObject("colorPerMaterialList", colorPerMaterialService.searchColorPerMaterial(searchWhat, SearchColorPerMaterialModelOption.valueOf(searchBy)));
            }
            modelAndView.addObject("tableURL","panels/data/colorPerMaterial");
        } else {
            modelAndView = new ModelAndView("panels/superPanel");
        }
        return modelAndView;
    }

    @PostMapping("data/colorPerMaterials")
    public String changeActiveC(@RequestParam Integer id, @RequestParam boolean active, @RequestParam(required = false) String searchWhat,
                               @RequestParam(required = false) String searchBy) {

        ColorPerMaterial colorPerMaterial = colorPerMaterialService.getColorPerMaterialById(id);
        colorPerMaterial.setActive(active);
        colorPerMaterialService.save(colorPerMaterial);
        if(searchWhat == null || searchBy == null){return  "redirect:/panels/data/colorPerMaterials"; }
    return  "redirect:/panels/data/colorPerMaterials/?searchWhat="+searchWhat+"&searchBy="+searchBy;
    }

    @PostMapping("data/products")
    public String changeActiveP(@RequestParam Integer id, @RequestParam boolean active, @RequestParam(required = false) String searchWhat,
                               @RequestParam(required = false) String searchBy) {
        Product product = productService.getProductById(id);
        product.setActive(active);
        productService.save(product);
        if(searchWhat == null || searchBy == null){return  "redirect:/panels/data/products"; }
        return  "redirect:/panels/data/products/?searchWhat="+searchWhat+"&searchBy="+searchBy;
    }


}
