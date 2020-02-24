package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.TypeNotExistException;
import pl.javarun.mywebshop.model.Type;
import pl.javarun.mywebshop.repository.TypeRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 21.02.2020 18:10
 * *
 * @className: TypeService
 * *
 * *
 ******************************************************/
@Service
public class TypeService {

    private TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type getTypeById(int id){
        return typeRepository.findById(id).orElseThrow(()->new TypeNotExistException("type "+id));
    }

    public Type getTypeByName(String name){
        return typeRepository.findByName(name).orElseThrow(()->new TypeNotExistException("type "+name));
    }

    public List getAllTypes() {
        return typeRepository.findAll();
    }

    public Type getTypeByNamePl(String namePl) {
        return typeRepository.findByNamePl(namePl).orElseThrow(()->new TypeNotExistException("type "+namePl));
    }

    public void save(Type type) {
        typeRepository.save(type);
    }
}
