package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.model.ConfigData;
import pl.javarun.mywebshop.repository.ConfigDataRepository;


import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 01.05.2020 22:11
 * *
 * @className: ConfigurationService
 * *
 * *
 ******************************************************/
@Service
public class ConfigDataService {

    private ConfigDataRepository configDataRepository;

    public ConfigDataService(ConfigDataRepository configDataRepository) {
        this.configDataRepository = configDataRepository;
    }

    public List<ConfigData> getAllConfigs(){
        return configDataRepository.findAll();
    }

    public ConfigData getConfigDataById(int id){
        return configDataRepository.getOne(id);
    }

    public ConfigData getConfigDataByName(String name){
        return configDataRepository.findByName(name);
    }
}
