package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.AddressNotExistException;
import pl.javarun.mywebshop.model.Address;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.repository.AddressRepository;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.05.2020 22:24
 * *
 * @className: AddressService
 * *
 * *
 ******************************************************/
@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getByUser(User user) {
        return addressRepository.findByUser(user).orElseThrow(()->new AddressNotExistException("adress dla tego użytkownika nie istnieje"));
    }

    public void save(Address address) {
        addressRepository.save(address);
    }
}
