package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.Product;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 18:50
 * *
 * @className: ProductController
 * *
 * *
 ******************************************************/

@Controller
@RequestMapping("/panels/data/product")
public class ProductManageController {

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


    public ProductManageController(ProductService productService, TypeService typeService, MaterialService materialService,
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


    @GetMapping({"/{id}", "/new"})
    public ModelAndView editProductItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/productItemManager");
        if (id == null) {
            System.out.println("petla z nullem");
            modelAndView.addObject("product", new Product());
        } else {
            System.out.println("petla bez nulla");
            modelAndView.addObject("product", productService.getProductById(id));
        }
        modelAndView.addObject("types", typeService.getAllTypes());
        modelAndView.addObject("makingTechniques", makingTechniqueService.getAllMakingTechniques());
        modelAndView.addObject("materials", materialService.getAllMaterials());
        modelAndView.addObject("materialColors", materialColorService.getAllMaterialColors());
        modelAndView.addObject("fasteningTypes", fasteningTypeService.getAllFasteningTypes());
        modelAndView.addObject("fasteningColors", fasteningColorService.getAllFasteningColors());
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveDatabaseItem(
            @RequestParam(required = false) Integer id,
            @RequestParam String name, @RequestParam String namePl,
            @RequestParam Integer typeId, @RequestParam Integer makingTechniqueId,
            @RequestParam Integer materialId, @RequestParam Integer materialColorId,
            @RequestParam Integer fasteningTypeId, @RequestParam Integer fasteningColorId,
            @RequestParam Double length, @RequestParam Double width, @RequestParam Integer price,
            @RequestParam String description, @RequestParam String descriptionPl) {
        Product product;
        if (id != null && id != 0) {
            product = productService.getProductById(id);
        } else {
            product = new Product();
        }
        product.setName(name);
        product.setNamePl(namePl);
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
        product.setDescriptionPl(descriptionPl);
        productService.saveProduct(product);
        return "redirect:/panels/data/products";
    }
}
