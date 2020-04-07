package pl.javarun.mywebshop.search;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 06.04.2020 19:22
 * *
 * @className: SearchColorPerMaterialModelOption
 * *
 * *
 ******************************************************/
public enum SearchColorPerMaterialModelOption {

    BY_MATERIAL_NAME("po nazwie materiału"), BY_MATERIAL_COLOR("po kolorze");

    private String description;

    SearchColorPerMaterialModelOption(String description) {
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
