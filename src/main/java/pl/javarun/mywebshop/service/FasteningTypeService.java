package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.FasteningTypeNotExistException;
import pl.javarun.mywebshop.exception.MakingTechniqueNotExistException;
import pl.javarun.mywebshop.model.FasteningType;
import pl.javarun.mywebshop.repository.FasteningTypeRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 19:10
 * *
 * @className: FasteningTypeService
 * *
 * *
 ******************************************************/
@Service
public class FasteningTypeService {

    FasteningTypeRepository fasteningTypeRepository;

    public FasteningTypeService(FasteningTypeRepository fasteningTypeRepository) {
        this.fasteningTypeRepository = fasteningTypeRepository;
    }

    public List<FasteningType> getAllFasteningTypes(){
        return fasteningTypeRepository.findAll();
    }

    public FasteningType getFasteningTypeById(Integer id) {
        return fasteningTypeRepository.findById(id).orElseThrow(()->new FasteningTypeNotExistException("FasteningType "+id));
    }

    public void save(FasteningType fasteningType) {
        fasteningTypeRepository.save(fasteningType);
    }
}
