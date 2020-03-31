package pl.javarun.mywebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.MaterialColor;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 20:14
 * *
 * @className: MaterialColorController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/materialColor/")
public class MaterialColorController {

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


    public MaterialColorController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    public ModelAndView editMaterialColorsItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/materialColorItemManager");
        if (id == null) {
            modelAndView.addObject("materialColor", new MaterialColor());
        } else {
            modelAndView.addObject("materialColor", materialColorService.getMaterialColorsById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveMaterialColorsIdItem(@PathVariable(required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        MaterialColor materialColor;
        if(id != null && id !=0){
            materialColor = materialColorService.getMaterialColorsById(id);
        } else {
            materialColor = new MaterialColor();
        }
        materialColor.setName(name);
        materialColor.setNamePl(namePl);
        materialColorService.save(materialColor);
        return "redirect:/panels/data/materialColors";
    }
}
