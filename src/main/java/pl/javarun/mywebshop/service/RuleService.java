package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.model.Rule;
import pl.javarun.mywebshop.repository.RuleRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 22.02.2020 20:37
 * *
 * @className: RuleService
 * *
 * *
 ******************************************************/
@Service
public class RuleService {

    private RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public Rule getRuleByName(String name){
        return ruleRepository.findByName(name);
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }
}
