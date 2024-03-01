package io.bootify.libreri.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;



@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    public OpenAPI api(){
        return  new OpenAPI();
    }
}