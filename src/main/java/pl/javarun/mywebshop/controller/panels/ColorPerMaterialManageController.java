package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.ColorPerMaterial;
import pl.javarun.mywebshop.model.MaterialColor;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 05.04.2020 22:05
 * *
 * @className: ColorPerMaterialManageController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/colorPerMaterial/")
public class ColorPerMaterialManageController {

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
    private final ColorPerMaterialService colorPerMaterialService;


    public ColorPerMaterialManageController(ProductService productService, TypeService typeService, MaterialService materialService,
                                            MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                                            FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                                            UserService userService, CompanyService companyService, RuleService ruleService,
                                            ColorPerMaterialService colorPerMaterialService) {
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
        this.colorPerMaterialService=colorPerMaterialService;
    }

    @GetMapping({"/{id}", "/new"})
    public ModelAndView editMaterialColorsItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/colorPerMaterialItemManager");
        if (id == null) {
            ColorPerMaterial colorPerMaterial = new ColorPerMaterial();
            colorPerMaterial.setActive(true);
            modelAndView.addObject("colorPerMaterial", colorPerMaterial);
        } else {
            modelAndView.addObject("colorPerMaterial", colorPerMaterialService.getColorPerMaterialById(id));
        }
        modelAndView.addObject("materialColors",materialColorService.getAllMaterialColors());
        modelAndView.addObject("materials",materialService.getAllMaterials());

        return modelAndView;
    }

    @PostMapping("/save")
    public String saveMaterialColorsIdItem(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer materialId,
                                           @RequestParam (required = false)Integer materialColorId, @RequestParam boolean active,
                                           @RequestParam (required = false)String availability) {
        ColorPerMaterial colorPerMaterial;

        if (id != null && id != 0) {
            colorPerMaterial = colorPerMaterialService.getColorPerMaterialById(id);
        } else {
            colorPerMaterial = new ColorPerMaterial();
        }
        colorPerMaterial.setMaterial(materialService.getMaterialById(materialId));
        colorPerMaterial.setMaterialColor(materialColorService.getMaterialColorsById(materialColorId));
        colorPerMaterial.setActive(active);
        colorPerMaterial.setAvailability(availability);
        colorPerMaterialService.save(colorPerMaterial);
        return "redirect:/panels/data/colorPerMaterials";
    }
}
