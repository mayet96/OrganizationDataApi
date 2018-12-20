package ru.id61890868.OrganizationDataApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class OrganizationDataApi {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationDataApi.class, args);
    }
}
