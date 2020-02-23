package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.Product;
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


    public SuperPanelController(ProductService productService, TypeService typeService, MaterialService materialService,
                                MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                                FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                                UserService userService, CompanyService companyService, RuleService ruleService) {
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
    }


    @GetMapping("/superpanel")
    public ModelAndView openSuperPanel() {
        modelAndView = new ModelAndView("panels/superPanel");
        return modelAndView;
    }

    @GetMapping("/data/{name}")
    public ModelAndView openProductsDatabase(@PathVariable(required = true) String name) {
        if (name.equals("products")) {
            modelAndView = new ModelAndView("panels/productTableManager");
            modelAndView.addObject("products", productService.getAllProducts());
        } else if (name.equals("types")) {
            modelAndView = new ModelAndView("panels/typeTableManager");
            modelAndView.addObject("types", typeService.getAllTypes());
        } else if (name.equals("materials")) {
            modelAndView = new ModelAndView("panels/materialTableManager");
            modelAndView.addObject("materials", materialService.getAllMaterials());
        } else if (name.equals("materialColors")) {
            modelAndView = new ModelAndView("panels/materialColorTableManager");
            modelAndView.addObject("materialColors", materialColorService.getAllMaterialColors());
        } else if (name.equals("fasteningTypes")) {
            modelAndView = new ModelAndView("panels/fasteningTypeTableManager");
            modelAndView.addObject("fasteningTypes", fasteningTypeService.getAllFasteningTypes());
        } else if (name.equals("fasteningColors")) {
            modelAndView = new ModelAndView("panels/fasteningColorTableManager");
            modelAndView.addObject("fasteningColors", fasteningColorService.getAllFasteningColors());
        } else if (name.equals("makingTechnique")) {
            modelAndView = new ModelAndView("panels/makingTechniqueTableManager");
            modelAndView.addObject("makingTechniques", makingTechniqueService.getAllMakingTechniques());
        } else if (name.equals("users")) {
            modelAndView = new ModelAndView("panels/userTableManager");
            modelAndView.addObject("users", userService.getAllUsers());
        } else if (name.equals("company")) {
            modelAndView = new ModelAndView("panels/companyTableManager");
            modelAndView.addObject("companies", companyService.getAllCompanies());
        } else if (name.equals("rules")) {
            modelAndView = new ModelAndView("panels/ruleTableManager");
            modelAndView.addObject("rules", ruleService.getAllRules());
        } else {
            modelAndView = new ModelAndView("panels/superPanel");
        }
        return modelAndView;
    }

    @GetMapping("/data/product/{id}")
    public ModelAndView editDatabaseItem(@PathVariable int id) {
        modelAndView = new ModelAndView("panels/productItemManager");
        modelAndView.addObject("product", productService.getProductById(id));
        return modelAndView;
    }

    @PostMapping(value = {"/data/products/add", "/panels/data/products/{id}"})
    public String saveDatabaseItem(@ModelAttribute Product product, @PathVariable(required = false) Integer id) {
        if (id != null) {
            product.setId(id);
        }
        return "redirect:/panels/data/products";
    }
}
