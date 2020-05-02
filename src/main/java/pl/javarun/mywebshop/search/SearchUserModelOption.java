package pl.javarun.mywebshop.search;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 02.05.2020 22:34
 * *
 * @className: SearchUserModelOption
 * *
 * *
 ******************************************************/
public enum SearchUserModelOption {

    BY_USERNAME("po użytkowniku"), BY_FIRSTNAME("po imieniu"), BY_LASTNAME("po nazwisku"),
    BY_EMAIL("po mailu"), BY_ROLE("po uprawnieniach"), BY_ACTIVE("po aktywności"),
    BY_CREATIONDATE("po dacie rejestracji");

    private String description;

    SearchUserModelOption(String description) {
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
