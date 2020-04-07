package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.ColorPerMaterialNotExistException;
import pl.javarun.mywebshop.model.ColorPerMaterial;
import pl.javarun.mywebshop.search.SearchColorPerMaterialModelOption;
import pl.javarun.mywebshop.repository.ColorPerMaterialRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 10:28
 * *
 * @className: ColorPerMaterialService
 * *
 * *
 ******************************************************/
@Service
public class ColorPerMaterialService {

    private ColorPerMaterialRepository colorPerMaterialRepository;

    public ColorPerMaterialService(ColorPerMaterialRepository colorPerMaterialRepository) {
        this.colorPerMaterialRepository = colorPerMaterialRepository;
    }

    public List<ColorPerMaterial> getMaterialColorsByMaterialId(int id){
        return colorPerMaterialRepository.findColorsPerMaterialsByIdAndActive(id);}

    public ColorPerMaterial getColorPerMaterialById(int id){
        return colorPerMaterialRepository.findById(id).orElseThrow(()->new ColorPerMaterialNotExistException("ColorPerMaterial "+id));
    }

    public void save(ColorPerMaterial colorPerMaterial) {
        colorPerMaterialRepository.save(colorPerMaterial);
    }

    public List<ColorPerMaterial> getAllColorPerMaterial() {
        return colorPerMaterialRepository.findAll();
    }

    public List<ColorPerMaterial> searchColorPerMaterial(String searchWhat, SearchColorPerMaterialModelOption findBy) {
        switch (findBy) {
            case BY_MATERIAL_NAME:
                return colorPerMaterialRepository.findByMaterialNamePlContainsIgnoreCase(searchWhat);
            case BY_MATERIAL_COLOR:
                return colorPerMaterialRepository.findByMaterialColorNamePlContainsIgnoreCase(searchWhat);
            default:
                return Collections.emptyList();
        }
    }
}
