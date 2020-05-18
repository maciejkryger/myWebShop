package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.MaterialColorNotExistException;
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
public class MaterialColorManageController {

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


    public MaterialColorManageController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    public String saveMaterialColorsIdItem(@RequestParam(required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        MaterialColor materialColor;
        try {
            materialColor = materialColorService.getMaterialColorsById(id);
        } catch (MaterialColorNotExistException ex){
            materialColor = new MaterialColor();
        }
        materialColor.setName(name);
        materialColor.setNamePl(namePl);
        materialColorService.save(materialColor);
        return "redirect:/panels/data/materialColors";
    }
}
