package pl.coderslab.egrades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@SpringBootApplication
public class EGradesApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EGradesApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EGradesApplication.class, args);
    }

    @Bean
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }

}
