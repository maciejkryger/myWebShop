package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.FasteningTypeNotExistException;
import pl.javarun.mywebshop.model.FasteningType;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 20:30
 * *
 * @className: FasteningTypeController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/fasteningType/")
public class FasteningTypeManageController {

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


    public FasteningTypeManageController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    public ModelAndView editFasteningTypeItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/fasteningTypeItemManager");
        if (id == null) {
            modelAndView.addObject("fasteningType", new FasteningType());
        } else {
            modelAndView.addObject("fasteningType", fasteningTypeService.getFasteningTypeById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveFasteningTypeIdItem(@RequestParam(required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        FasteningType fasteningType;
        try {
            fasteningType = fasteningTypeService.getFasteningTypeById(id);
        } catch (FasteningTypeNotExistException ex){
            fasteningType = new FasteningType();
        }
        fasteningType.setName(name);
        fasteningType.setNamePl(namePl);
        fasteningTypeService.save(fasteningType);
        return "redirect:/panels/data/fasteningTypes";
    }
}
