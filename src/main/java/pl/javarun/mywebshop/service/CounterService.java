package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.TypeCounterNotExistException;
import pl.javarun.mywebshop.model.Counter;
import pl.javarun.mywebshop.repository.CounterRepository;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.06.2020 22:24
 * *
 * @className: TypeCounterService
 * *
 * *
 ******************************************************/
@Service
public class CounterService {

    private CounterRepository counterRepository;


    public Counter getByTypeName(int type) {
        return counterRepository.findByType(type).orElseThrow(()->new TypeCounterNotExistException("brak  licznika typu asortymenta"));
    }

    public void save(Counter counter) {
        counterRepository.save(counter);
    }
}
