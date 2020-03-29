package pl.javarun.mywebshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.FasteningColor;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 19:03
 * *
 * @className: FasteningColorController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/fasteningColor")
public class FasteningColorController {

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


    public FasteningColorController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    public ModelAndView editFasteningColorItem(@PathVariable (required = false) Integer id) {
        modelAndView = new ModelAndView("panels/fasteningColorItemManager");
        if(id==null){
            modelAndView.addObject("fasteningColor", new FasteningColor());
        } else {
            modelAndView.addObject("fasteningColor", fasteningColorService.getFasteningColorById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveFasteningColorIdItem(@RequestParam (required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        FasteningColor fasteningColor;
        if (id==null){
          fasteningColor = new FasteningColor();
          id = fasteningColorService.getAllFasteningColors().size()+1;
          fasteningColor.setId(id);
        } else {
          fasteningColor = fasteningColorService.getFasteningColorById(id);
        }
        fasteningColor.setName(name);
        fasteningColor.setNamePl(namePl);
        fasteningColorService.save(fasteningColor);
        return "redirect:/panels/data/fasteningColors";
    }

}