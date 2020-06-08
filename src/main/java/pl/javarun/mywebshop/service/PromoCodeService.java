package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.PromoCodeNotExistException;
import pl.javarun.mywebshop.model.PromoCode;
import pl.javarun.mywebshop.repository.PromoCodeRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 06.06.2020 21:45
 * *
 * @className: PromoCodeService
 * *
 * *
 ******************************************************/
@Service
public class PromoCodeService {

    private PromoCodeRepository promoCodeRepository;

    public PromoCodeService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public PromoCode getPromoCodeByPromoCode(String discountCode) {
        return promoCodeRepository.findByCode(discountCode).orElseThrow(()->new PromoCodeNotExistException("brak szukanego promo"));
    }

    public PromoCode getPromoCodeById(Integer id) {
        return promoCodeRepository.findById(id).orElseThrow(()->new PromoCodeNotExistException("brak wybranego kodu"));
    }

    public void save(PromoCode promoCode) {
        promoCodeRepository.save(promoCode);
    }

    public List<PromoCode> getAllPromoCodes() {
        return promoCodeRepository.findAll();
    }
}
