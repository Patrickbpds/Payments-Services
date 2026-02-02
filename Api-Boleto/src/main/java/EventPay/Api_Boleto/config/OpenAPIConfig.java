package EventPay.Api_Boleto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API boleto")
                .description("Boleto Payment API")
                .contact(new Contact().name("Patrick Batista").email("patrickbpds@outlook.com"))
                .version("1.0.0")
        );
    }

}
