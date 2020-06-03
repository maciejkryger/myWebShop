package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.TypeNotExistException;
import pl.javarun.mywebshop.model.PaymentMethod;
import pl.javarun.mywebshop.model.Type;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 02.06.2020 22:11
 * *
 * @className: PaymentMethodController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/paymentMethod/")
public class PaymentMethodController {

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
    private final PaymentMethodService paymentMethodService;


    public PaymentMethodController(ProductService productService, TypeService typeService, MaterialService materialService,
                                   MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                                   FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                                   UserService userService, CompanyService companyService, RuleService ruleService,
                                   PaymentMethodService paymentMethodService) {
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
        this.paymentMethodService=paymentMethodService;
    }

    @GetMapping({"/{id}"})
    public ModelAndView editPaymentMethodItem(@PathVariable (required = false) Integer id) {
        modelAndView = new ModelAndView("panels/paymentMethodItemManager");
        modelAndView.addObject("paymentMethod", paymentMethodService.getPaymentMethodById(id));
        return modelAndView;
    }

    @PostMapping("/save")
    public String savePaymentMethodItem(@RequestParam (required = false) Integer id, @RequestParam String name, @RequestParam String namePl) {
        PaymentMethod paymentMethod;
        try{
            paymentMethod = paymentMethodService.getPaymentMethodById(id);
        } catch (TypeNotExistException ex){
            paymentMethod = new PaymentMethod();
        }
        paymentMethod.setName(name);
        paymentMethod.setNamePl(namePl);

        paymentMethodService.save(paymentMethod);
        return "redirect:/panels/data/paymentMethods";
    }
}

