package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.MakingTechniqueNotExistException;
import pl.javarun.mywebshop.model.MakingTechnique;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 20:26
 * *
 * @className: MakingTechniqueController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/makingTechnique/")
public class MakingTechniqueManageController {

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


    public MakingTechniqueManageController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    public ModelAndView editMakingTechniqueItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/makingTechniqueItemManager");
        if (id == null) {
            modelAndView.addObject("makingTechnique", new MakingTechnique());
        } else {
            modelAndView.addObject("makingTechnique", makingTechniqueService.getMakingTechniqueById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveMakingTechniqueIdItem(@RequestParam(required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        MakingTechnique makingTechnique;
        try {
            makingTechnique = makingTechniqueService.getMakingTechniqueById(id);
        } catch (MakingTechniqueNotExistException ex){
            makingTechnique = new MakingTechnique();
        }
        makingTechnique.setName(name);
        makingTechnique.setNamePl(namePl);
        makingTechniqueService.save(makingTechnique);
        return "redirect:/panels/data/makingTechniques";
    }
}
