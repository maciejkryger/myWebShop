package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.RoleNotExistException;
import pl.javarun.mywebshop.model.Role;
import pl.javarun.mywebshop.model.Rule;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 29.03.2020 21:04
 * *
 * @className: RuleController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/role")
public class RoleManageController {

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
    private final RoleService roleService;


    public RoleManageController(ProductService productService, TypeService typeService, MaterialService materialService,
                                MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                                FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                                UserService userService, CompanyService companyService, RuleService ruleService, RoleService roleService) {
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
        this.roleService = roleService;
    }

    @GetMapping({"/{id}", "/new"})
    public ModelAndView editRoleItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/roleItemManager");
        if (id == null) {
            modelAndView.addObject("role", new Role());
        } else {
            modelAndView.addObject("role", roleService.getRoleById(id));
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveRoleItem(@RequestParam(required = false) Integer id, @RequestParam String authority) {
        Role role;
        try {
            role = roleService.getRoleById(id);
        } catch (RoleNotExistException ex){
            role = new Role();
        }
        role.setAuthority(authority);
        roleService.save(role);
        return "redirect:/panels/data/roles";
    }

}
