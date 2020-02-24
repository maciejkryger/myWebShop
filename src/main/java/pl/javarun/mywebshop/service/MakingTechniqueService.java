package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.MakingTechniqueNotExistException;
import pl.javarun.mywebshop.exception.TypeNotExistException;
import pl.javarun.mywebshop.model.MakingTechnique;
import pl.javarun.mywebshop.repository.MakingTechniqueRepository;

import java.util.List;
import java.util.Optional;

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

    public MakingTechnique getMakingTechniqueByNamePl(String makingTechnique) {
        return makingTechniqueRepository.findByNamePl(makingTechnique).orElseThrow(()->new MakingTechniqueNotExistException("MakingTechnique "+makingTechnique));
    }

    public MakingTechnique getMakingTechniqueById(Integer id) {
        return makingTechniqueRepository.findById(id).orElseThrow(()->new MakingTechniqueNotExistException("MakingTechnique "+id));
    }

    public void save(MakingTechnique makingTechnique) {
        makingTechniqueRepository.save(makingTechnique);
    }
}
