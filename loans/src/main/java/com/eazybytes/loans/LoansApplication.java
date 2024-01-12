package com.eazybytes.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Loans microservice REST API Documentation",
                description = "EazyBank Loans microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Andrii",
                        email = "qq@gmail.com",
                        url = "qq.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "qqq.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EazyBank Loans microservice REST API Documentation",
                url = "qqqq.com"
        )
)
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}
