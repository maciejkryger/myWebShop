package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.Material;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 19:03
 * *
 * @className: MaterialController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/material")
public class MaterialController {

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


    public MaterialController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    @GetMapping({"/{id}","/new"})
    public ModelAndView editMaterialItem(@PathVariable (required = false) Integer id) {
        modelAndView = new ModelAndView("panels/materialItemManager");
        if(id==null){
            modelAndView.addObject("material",new Material());
        }else {
            modelAndView.addObject("material", materialService.getMaterialById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveMaterialItem(@PathVariable (required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        Material material;
        if(id==null){
            material = new Material();
        }else {
            material = materialService.getMaterialById(id);
        }
        material.setName(name);
        material.setNamePl(namePl);
        materialService.save(material);
        return "redirect:/panels/data/materials";
    }
}
