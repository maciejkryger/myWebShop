package pl.javarun.mywebshop.controller.panels;

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
public class MaterialManageController {

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


    public MaterialManageController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    public ModelAndView editMaterialItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/materialItemManager");
        if (id == null) {
            modelAndView.addObject("material", new Material());
        } else {
            modelAndView.addObject("material", materialService.getMaterialById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveMaterialItem(@RequestParam(required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        Material material;
        if (id != null && id != 0) {
            material = materialService.getMaterialById(id);
        } else {
            material = new Material();
        }
        material.setName(name);
        material.setNamePl(namePl);
        materialService.save(material);
        return "redirect:/panels/data/materials";
    }
}
