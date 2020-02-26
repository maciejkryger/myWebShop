package pl.javarun.mywebshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MywebshopApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MywebshopApplication.class, args);
    }

}
