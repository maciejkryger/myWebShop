package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.PromoCodeNotExistException;
import pl.javarun.mywebshop.model.PromoCode;
import pl.javarun.mywebshop.repository.PromoCodeRepository;

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
}
