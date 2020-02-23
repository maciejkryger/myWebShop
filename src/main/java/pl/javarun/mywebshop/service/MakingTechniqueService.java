package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.model.MakingTechnique;
import pl.javarun.mywebshop.repository.MakingTechniqueRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 19:34
 * *
 * @className: MakingTechniqueService
 * *
 * *
 ******************************************************/
@Service
public class MakingTechniqueService {

    MakingTechniqueRepository makingTechniqueRepository;

    public MakingTechniqueService(MakingTechniqueRepository makingTechniqueRepository) {
        this.makingTechniqueRepository = makingTechniqueRepository;
    }

    public List<MakingTechnique> getAllMakingTechniques(){
        return makingTechniqueRepository.findAll();
    }
}
