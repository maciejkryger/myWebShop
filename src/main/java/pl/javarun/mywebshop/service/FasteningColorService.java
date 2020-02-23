package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.model.FasteningColor;
import pl.javarun.mywebshop.repository.FasteningColorRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 19:10
 * *
 * @className: FasteningColorService
 * *
 * *
 ******************************************************/
@Service
public class FasteningColorService {

    FasteningColorRepository fasteningColorRepository;

    public FasteningColorService(FasteningColorRepository fasteningColorRepository) {
        this.fasteningColorRepository = fasteningColorRepository;
    }

    public List<FasteningColor> getAllFasteningColors(){
        return fasteningColorRepository.findAll();
    }
}
