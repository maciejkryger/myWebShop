package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.TypeNotExistException;
import pl.javarun.mywebshop.model.Type;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 19:11
 * *
 * @className: TypeController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/type/")
public class TypeManageController {

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


    public TypeManageController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    public ModelAndView editTypeItem(@PathVariable (required = false) Integer id) {
        modelAndView = new ModelAndView("panels/typeItemManager");
        if (id==null){
          modelAndView.addObject("type", new Type());
        } else {
        modelAndView.addObject("type", typeService.getTypeById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveTypeItem(@RequestParam (required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        Type type;
        try{
            type = typeService.getTypeById(id);
        } catch (TypeNotExistException ex){
            type = new Type();
        }
        type.setName(name);
        type.setNamePl(namePl);
        typeService.save(type);
        return "redirect:/panels/data/types";
    }
}

