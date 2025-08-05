package org.springframework.boot.config.swaggerconfig;

import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi openApi(@Value("2.1.0") String appVersion) {
        log.info("SWAGGER - GroupedOpenApi bean is created");
        log.info("Swagger API docs available at: /swagger-ui.html");
        String[] paths = {"/api/**"};
        String[] packagedToMatch = {"org.springframework.boot.api.controller.spec"};
        return GroupedOpenApi.builder()
                .group("test-auto")
                .addOpenApiCustomizer(openApi -> {
                    openApi.info(new Info().title("test-auto").version(appVersion));
                    openApi.addSecurityItem(new SecurityRequirement().addList("Authorization"));
                })
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch)  // Controller'larınızı içeren paketi doğru yazın
                .build();
    }
}

