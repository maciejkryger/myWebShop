package pl.javarun.mywebshop.controller;

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
public class ProductController {

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


    public ProductController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    public ModelAndView editProductItem(@PathVariable (required = false) Integer id) {
        modelAndView = new ModelAndView("panels/productItemManager");
        if (id==null){
            System.out.println("petla z nullem");
            modelAndView.addObject("product", new Product());
        } else  {
            System.out.println("petla bez nulla");
            modelAndView.addObject("product", productService.getProductById(id));
        }
        modelAndView.addObject("types",typeService.getAllTypes());
        modelAndView.addObject("makingTechniques",makingTechniqueService.getAllMakingTechniques());
        modelAndView.addObject("materials",materialService.getAllMaterials());
        modelAndView.addObject("materialColors",materialColorService.getAllMaterialColors());
        modelAndView.addObject("fasteningTypes",fasteningTypeService.getAllFasteningTypes());
        modelAndView.addObject("fasteningColors",fasteningColorService.getAllFasteningColors());
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
            @RequestParam String description, @RequestParam String descriptionPl)
    {
        Product product;
        if(id!=null && id !=0){
            System.out.println("produkt z bazy od id "+id);
            product = productService.getProductById(id);
        }else{
            System.out.println("nowy produkt");
//            System.out.println(name+namePl+typeId+materialId+materialColorId+fasteningTypeId+fasteningColorId+length+width+price+description+descriptionPl);
            product = new Product();
            id = productService.getAllProducts().size();
            product.setId(id+1);
        }
        System.out.println("przed setterami");
        product.setName(name);
        System.out.println("po set name");
        product.setNamePl(namePl);
        System.out.println("po set namePL");
        product.setType(typeService.getTypeById(typeId));
        System.out.println("po set type");
        product.setMakingTechnique(makingTechniqueService.getMakingTechniqueById(makingTechniqueId));
        System.out.println("po set technika");
        product.setMaterial(materialService.getMaterialById(materialId));
        System.out.println("po set material");
        product.setMaterialColor(materialColorService.getMaterialColorsById(materialColorId));
        System.out.println("po set material color");
        product.setFasteningType(fasteningTypeService.getFasteningTypeById(fasteningTypeId));
        System.out.println("po set typ zapiecia");
        product.setFasteningColor(fasteningColorService.getFasteningColorById(fasteningColorId));
        System.out.println("po set kolor zapiecia");
        product.setLength(length);
        System.out.println("po set length");
        product.setWidth(width);
        System.out.println("po set width");
        product.setPrice(price);
        System.out.println("po setPice");
        product.setDescription(description);
        System.out.println("po set descripton");
        product.setDescriptionPl(descriptionPl);
        System.out.println("po wszystkich setterach przed zapisem");
        productService.saveProduct(product);
        System.out.println("po zapisie");
        return "redirect:/panels/data/products";
    }
}
