package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
    @GetMapping({"/{id}","/new"})
    public ModelAndView editFasteningTypeItem(@PathVariable (required = false) Integer id) {
        modelAndView = new ModelAndView("panels/fasteningTypeItemManager");
        if(id==null){
            modelAndView.addObject("fasteningType",new FasteningType());
        } else {
            modelAndView.addObject("fasteningType", makingTechniqueService.getMakingTechniqueById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveFasteningTypeIdItem(@PathVariable (required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        FasteningType fasteningType;
        if(id!=null && id!=0){
            fasteningType = fasteningTypeService.getFasteningTypeById(id);
        } else {
            fasteningType = new FasteningType();
        }
        fasteningType.setName(name);
        fasteningType.setNamePl(namePl);
        fasteningTypeService.save(fasteningType);
        return "redirect:/panels/data/fasteningTypes";
    }
}
