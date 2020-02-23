package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.model.Material;
import pl.javarun.mywebshop.repository.MaterialRepository;

import java.util.List;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 18:38
 * *
 * @className: MaterialService
 * *
 * *
 ******************************************************/
@Service
public class MaterialService {

    MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
}
