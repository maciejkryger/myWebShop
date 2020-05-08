package pl.javarun.mywebshop.controller.panels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javarun.mywebshop.model.ConfigData;
import pl.javarun.mywebshop.model.Role;
import pl.javarun.mywebshop.service.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 07.05.2020 00:25
 * *
 * @className: ConfigManageController
 * *
 * *
 ******************************************************/
@Controller
@RequestMapping("/panels/data/config")
public class ConfigManageController {

    private ModelAndView modelAndView;
    private final ConfigDataService configDataService;


    public ConfigManageController(ConfigDataService configDataService) {
        this.configDataService = configDataService;
    }

    @GetMapping({"/"})
    public ModelAndView editConfig() {
        modelAndView = new ModelAndView("panels/configManager");

            modelAndView.addObject("configs", configDataService.getAllConfigs());

        return modelAndView;
    }

    @PostMapping("/save")
    public String saveConfig(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name,
                             @RequestParam String value, @RequestParam String description) {
        ConfigData configData = configDataService.getConfigDataById(id);
        configData.setValue(value);
        configData.setDescription(description);
        configDataService.save(configData);
        return "redirect:/panels/data/config";
    }

}
