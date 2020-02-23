package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.model.Company;
import pl.javarun.mywebshop.repository.CompanyRepository;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 22.02.2020 14:39
 * *
 * @className: CompanyService
 * *
 * *
 ******************************************************/
@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getCompanyData(){
        return companyRepository.getOne(1);
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
}
