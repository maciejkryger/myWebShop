package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.RuleNotExistException;
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

    public Rule getRuleById(int id) { return ruleRepository.findById(id).orElseThrow(()->new RuleNotExistException("rule "+id)); }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    public void save(Rule rule) { ruleRepository.save(rule);
    }
}
