package ru.clevertec.ecl.clevertecfinaltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClevertecFinalTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClevertecFinalTaskApplication.class, args);
    }

}
