package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.*;
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

    @GetMapping("/data/{table}")
    public ModelAndView openProductsDatabase(@PathVariable(required = true) String table) {
        if (table.equals("products")) {
            modelAndView = new ModelAndView("panels/productTableManager");
            modelAndView.addObject("products", productService.getAllProducts());
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
        } else if (table.equals("company")) {
            modelAndView = new ModelAndView("panels/companyTableManager");
            modelAndView.addObject("companies", companyService.getAllCompanies());
        } else if (table.equals("rules")) {
            modelAndView = new ModelAndView("panels/ruleTableManager");
            modelAndView.addObject("rules", ruleService.getAllRules());
        } else {
            modelAndView = new ModelAndView("panels/superPanel");
        }
        return modelAndView;
    }

    @GetMapping("/data/product/{id}")
    public ModelAndView editProductItem(@PathVariable int id) {
        modelAndView = new ModelAndView("panels/productItemManager");
        modelAndView.addObject("product", productService.getProductById(id));
        modelAndView.addObject("types",typeService.getAllTypes());
        modelAndView.addObject("makingTechniques",makingTechniqueService.getAllMakingTechniques());
        modelAndView.addObject("materials",materialService.getAllMaterials());
        modelAndView.addObject("materialColors",materialColorService.getAllMaterialColors());
        modelAndView.addObject("fasteningTypes",fasteningTypeService.getAllFasteningTypes());
        modelAndView.addObject("fasteningColors",fasteningColorService.getAllFasteningColors());
        return modelAndView;
    }

    @PostMapping("/data/products/save/{id}")
    public String saveDatabaseItem(@PathVariable Integer id, @RequestParam String name,
                                   @RequestParam Integer typeId, @RequestParam Integer makingTechniqueId,
                                   @RequestParam Integer materialId, @RequestParam Integer materialColorId,
                                   @RequestParam Integer fasteningTypeId, @RequestParam Integer fasteningColorId,
                                   @RequestParam Double length, @RequestParam Double width, @RequestParam Integer price,
                                   @RequestParam String description) {
        Product product = productService.getProductById(id);
        product.setName(name);
        product.setType(typeService.getTypeById(typeId));
        product.setMakingTechnique(makingTechniqueService.getMakingTechniqueById(makingTechniqueId));
        product.setMaterial(materialService.getMaterialById(materialId));
        product.setMaterialColor(materialColorService.getMaterialColorsById(materialColorId));
        product.setFasteningType(fasteningTypeService.getFasteningTypeById(fasteningTypeId));
        product.setFasteningColor(fasteningColorService.getFasteningColorById(fasteningColorId));
        product.setLength(length);
        product.setWidth(width);
        product.setPrice(price);
        product.setDescription(description);
        productService.saveProduct(product);
        return "redirect:/panels/data/products";
    }

    @GetMapping("/data/type/{id}")
    public ModelAndView editTypeItem(@PathVariable int id) {
        modelAndView = new ModelAndView("panels/typeItemManager");
        modelAndView.addObject("type", typeService.getTypeById(id));
        return modelAndView;
    }

    @PostMapping("/data/types/save/{id}")
    public String saveTypeItem(@PathVariable Integer id, @RequestParam String name,@RequestParam String namePl) {
        Type type = typeService.getTypeById(id);
        type.setName(name);
        type.setNamePl(namePl);
        typeService.save(type);
        return "redirect:/panels/data/types";
    }
    @GetMapping("/data/material/{id}")
    public ModelAndView editMaterialItem(@PathVariable int id) {
        modelAndView = new ModelAndView("panels/materialItemManager");
        modelAndView.addObject("material", materialService.getMaterialById(id));
        return modelAndView;
    }

    @PostMapping("/data/materials/save/{id}")
    public String saveMaterialItem(@PathVariable Integer id, @RequestParam String name,@RequestParam String namePl) {
        Material material = materialService.getMaterialById(id);
        material.setName(name);
        material.setNamePl(namePl);
        materialService.save(material);
        return "redirect:/panels/data/materials";
    }

    @GetMapping("/data/materialColor/{id}")
    public ModelAndView editMaterialColorsItem(@PathVariable int id) {
        modelAndView = new ModelAndView("panels/materialColorItemManager");
        modelAndView.addObject("materialColor", materialColorService.getMaterialColorsById(id));
        return modelAndView;
    }

    @PostMapping("/data/materialColors/save/{id}")
    public String saveMaterialColorsIdItem(@PathVariable Integer id, @RequestParam String name,@RequestParam String namePl) {
        MaterialColor materialColor = materialColorService.getMaterialColorsById(id);
        materialColor.setName(name);
        materialColor.setNamePl(namePl);
        materialColorService.save(materialColor);
        return "redirect:/panels/data/materialColors";
    }

    @GetMapping("/data/makingTechnique/{id}")
    public ModelAndView editMakingTechniqueItem(@PathVariable int id) {
        modelAndView = new ModelAndView("panels/makingTechniqueItemManager");
        modelAndView.addObject("makingTechnique", makingTechniqueService.getMakingTechniqueById(id));
        return modelAndView;
    }

    @PostMapping("/data/makingTechniques/save/{id}")
    public String saveMakingTechniqueIdItem(@PathVariable Integer id, @RequestParam String name,@RequestParam String namePl) {
        MakingTechnique makingTechnique = makingTechniqueService.getMakingTechniqueById(id);
        makingTechnique.setName(name);
        makingTechnique.setNamePl(namePl);
        makingTechniqueService.save(makingTechnique);
        return "redirect:/panels/data/makingTechniques";
    }

    @GetMapping("/data/fasteningType/{id}")
    public ModelAndView editFasteningTypetem(@PathVariable int id) {
        modelAndView = new ModelAndView("panels/fasteningTypeItemManager");
        modelAndView.addObject("fasteningType", makingTechniqueService.getMakingTechniqueById(id));
        return modelAndView;
    }

    @PostMapping("/data/fasteningTypes/save/{id}")
    public String saveFasteningTypeIdItem(@PathVariable Integer id, @RequestParam String name,@RequestParam String namePl) {
        FasteningType fasteningType = fasteningTypeService.getFasteningTypeById(id);
        fasteningType.setName(name);
        fasteningType.setNamePl(namePl);
        fasteningTypeService.save(fasteningType);
        return "redirect:/panels/data/fasteningTypes";
    }

    @GetMapping("/data/fasteningColor/{id}")
    public ModelAndView editFasteningColorItem(@PathVariable int id) {
        modelAndView = new ModelAndView("panels/fasteningColorItemManager");
        modelAndView.addObject("fasteningColor", makingTechniqueService.getMakingTechniqueById(id));
        return modelAndView;
    }

    @PostMapping("/data/fasteningColors/save/{id}")
    public String saveFasteningColorIdItem(@PathVariable Integer id, @RequestParam String name,@RequestParam String namePl) {
        FasteningColor fasteningColor = fasteningColorService.getFasteningColorById(id);
        fasteningColor.setName(name);
        fasteningColor.setNamePl(namePl);
        fasteningColorService.save(fasteningColor);
        return "redirect:/panels/data/fasteningColors";
    }

}
