package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.MakingTechniqueNotExistException;
import pl.javarun.mywebshop.exception.MaterialColorNotExistException;
import pl.javarun.mywebshop.model.MaterialColor;
import pl.javarun.mywebshop.repository.MaterialColorRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 22.02.2020 19:36
 * *
 * @className: MaterialColorService
 * *
 * *
 ******************************************************/
@Service
public class MaterialColorService {

    private MaterialColorRepository materialColorRepository;

    public MaterialColorService(MaterialColorRepository materialColorRepository) {
        this.materialColorRepository = materialColorRepository;
    }

    public List<MaterialColor> getMaterialColorsByMaterialId(int id){
        return materialColorRepository.findMaterialColorsByMaterialId(id);
    }

    public List<MaterialColor> getAllMaterialColors() {
        return materialColorRepository.findAll();
    }

    public MaterialColor getMaterialColorsById(Integer id) {
        return materialColorRepository.findById(id).orElseThrow(()->new MaterialColorNotExistException("MaterialColor "+id));
    }

    public void save(MaterialColor materialColor) {
        materialColorRepository.save(materialColor);
    }
}
