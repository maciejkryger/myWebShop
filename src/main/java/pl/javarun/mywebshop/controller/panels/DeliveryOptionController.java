package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.exception.TypeNotExistException;
import pl.javarun.mywebshop.model.DeliveryOption;
import pl.javarun.mywebshop.model.PaymentMethod;
import pl.javarun.mywebshop.service.*;

import javax.sound.midi.Soundbank;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 02.06.2020 22:11
 * *
 * @className: DeliveryOptionController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/deliveryOption/")
public class DeliveryOptionController {

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
    private final DeliveryOptionService deliveryOptionService;
    private final PaymentTypeService paymentTypeService;


    public DeliveryOptionController(ProductService productService, TypeService typeService, MaterialService materialService,
                                    MaterialColorService materialColorService, FasteningTypeService fasteningTypeService,
                                    FasteningColorService fasteningColorService, MakingTechniqueService makingTechniqueService,
                                    UserService userService, CompanyService companyService, RuleService ruleService,
                                    DeliveryOptionService deliveryOptionService, PaymentTypeService paymentTypeService) {
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
        this.deliveryOptionService = deliveryOptionService;
        this.paymentTypeService = paymentTypeService;
    }

    @GetMapping({"/{id}"})
    public ModelAndView editDeliveryOptionItem(@PathVariable(required = false) Integer id) {
        modelAndView = new ModelAndView("panels/deliveryOptionItemManager");
        modelAndView.addObject("deliveryOption", deliveryOptionService.getDeliveryOptionById(id));
        modelAndView.addObject("paymentTypes", paymentTypeService.getAllPaymentTypes());
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveDeliveryOptionItem(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name,
                                         @RequestParam(required = false) String namePl, @RequestParam(required = false) boolean active,
                                         @RequestParam(required = false) Integer price, @RequestParam(required = false) boolean checked,
                                         @RequestParam(required = false) Integer paymentTypeId) {
        DeliveryOption deliveryOption;
        try {
            deliveryOption = deliveryOptionService.getDeliveryOptionById(id);
        } catch (TypeNotExistException ex) {
            deliveryOption = new DeliveryOption();
        }
        if (price != null) deliveryOption.setPrice(price);
        if (name != null) deliveryOption.setName(name);
        if (namePl != null) deliveryOption.setNamePl(namePl);
        if (price != null) deliveryOption.setPrice(price);
        if(active!=deliveryOption.isActive()) deliveryOption.setActive(active);
        if (paymentTypeId != null) {
            deliveryOption.setPaymentType(paymentTypeService.getById(paymentTypeId));
        }
        if (checked != deliveryOption.isChecked()) {
            for (DeliveryOption item : deliveryOptionService.getAllDeliveryOptions()){
                item.setChecked(false);
            }
            deliveryOption.setChecked(checked);
        }


        deliveryOptionService.save(deliveryOption);
        return "redirect:/panels/data/deliveryOptions";
    }
}

