package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.FasteningColor;
import pl.javarun.mywebshop.model.MaterialColor;
import pl.javarun.mywebshop.model.Rule;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 19:53
 * *
 * @className: RuleController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/rule/")
public class RuleManageController {

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


    public RuleManageController(ProductService productService, TypeService typeService, MaterialService materialService,
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
    public ModelAndView editRuleItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/ruleItemManager");
        if (id == null) {
            modelAndView.addObject("rule", new Rule());
        } else {
            modelAndView.addObject("rule", ruleService.getRuleById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveRuleItem(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) String namePl,
                               @RequestParam(required = false) String description, @RequestParam(required = false) String descriptionPl) {
        Rule rule;
        try{
            rule = ruleService.getRuleById(id);
        } catch (Exception ex){
            rule = new Rule();
        }
        rule.setName(name);
        rule.setNamePl(namePl);
        rule.setDescription(description);
        rule.setDescriptionPl(descriptionPl);
        ruleService.save(rule);
        return "redirect:/panels/data/rules";
    }

}
