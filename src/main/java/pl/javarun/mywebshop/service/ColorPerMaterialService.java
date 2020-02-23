package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.model.ColorPerMaterial;
import pl.javarun.mywebshop.model.MaterialColor;
import pl.javarun.mywebshop.repository.ColorPerMaterialRepository;

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
        return colorPerMaterialRepository.findColorPerMaterialsById(id);}

}
