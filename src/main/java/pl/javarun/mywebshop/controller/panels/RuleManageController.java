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
    public String saveRuleItem(@RequestParam(required = false) Integer id, @RequestParam String name, @RequestParam String namePl,
                               @RequestParam String description, @RequestParam String descriptionPl) {
        Rule rule;
        if (id != null & id != 0) {
            rule = ruleService.getRuleById(id);
        } else {
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