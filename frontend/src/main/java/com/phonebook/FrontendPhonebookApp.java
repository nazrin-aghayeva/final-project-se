package com.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FrontendPhonebookApp {

    public static void main(String[] args) {
        SpringApplication.run(FrontendPhonebookApp.class, args);
    }

}
