package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.AddressNotExistException;
import pl.javarun.mywebshop.exception.StatusNotExistException;
import pl.javarun.mywebshop.model.Address;
import pl.javarun.mywebshop.model.Status;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.repository.AddressRepository;
import pl.javarun.mywebshop.repository.StatusRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 30.05.2020 21:07
 * *
 * @className: StatusService
 * *
 * *
 ******************************************************/
@Service
public class StatusService {

    private StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status getById(int id) {
        return statusRepository.findById(id).orElseThrow(()->new StatusNotExistException("Taki status nie istnieje"));
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }
}
