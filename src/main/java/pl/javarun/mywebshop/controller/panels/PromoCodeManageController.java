package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.MaterialNotExistException;
import pl.javarun.mywebshop.exception.ProductNotExistException;
import pl.javarun.mywebshop.model.Material;
import pl.javarun.mywebshop.model.PromoCode;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 08.06.2020 20:27
 * *
 * @className: PromoCodeManageController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/promoCode")
public class PromoCodeManageController {

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
    private final PromoCodeService promoCodeService;


    public PromoCodeManageController(ProductService productService, TypeService typeService, MaterialService materialService,
                                     MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                                     FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                                     UserService userService, CompanyService companyService, RuleService ruleService,
                                     PromoCodeService promoCodeService) {
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
        this.promoCodeService = promoCodeService;
    }

    @GetMapping({"/{id}", "/new"})
    public ModelAndView editPromoCodeItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/promoCodeItemManager");
        if (id != null) {
            modelAndView.addObject("promoCode", promoCodeService.getPromoCodeById(id));
        } else {
            modelAndView.addObject("promoCode", new PromoCode());
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public String savePromoCodeItem(@RequestParam(required = false) Integer id, @RequestParam(required = false) String code,
                                    @RequestParam(required = false) Integer discount, @RequestParam boolean active) {
        PromoCode promoCode;
        if(id!=0 && id!=null){
            promoCode = promoCodeService.getPromoCodeById(id);
        } else  {
            promoCode = new PromoCode();
        }
        if (code != null && discount != null) {
            promoCode.setCode(code);
            promoCode.setDiscount(discount);
        }
        promoCode.setActive(active);
        promoCodeService.save(promoCode);
        return "redirect:/panels/data/promoCodes";
    }
}
