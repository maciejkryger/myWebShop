package pl.javarun.mywebshop.search;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 07.04.2020 17:22
 * *
 * @className: SearchColorPerMaterialModelOption
 * *
 * *
 ******************************************************/
public enum SearchProductModelOption {

    BY_PRODUCT_NAME("po nazwie produktu"),
    BY_MAIN_PRODUCT("po głównym produkcie"),
    BY_TYPE("po typie"),
    BY_MAKING_TECHNIQUE("po technice wykonania"),
    BY_MATERIAL("po materiale"),
    BY_MATERIAL_COLOR("po kolorze"),
    BY_FASTENING_TYPE("po zapięciu"),
//    BY_FASTENING_COLOR("po kolorze zapięcia"),
    BY_LENGTH("po długości"),
    BY_WIGHT("po szerokości"),
    BY_PRICE("po cenie"),
    BY_PRODUCT_DESCRIPTION("po opisie");


    private String description;

    SearchProductModelOption(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }
}
