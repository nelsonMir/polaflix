package es.unican.nelson.polaflix_nelson.controladorRest;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") 
                .allowedOrigins("http://127.0.0.1:3001", "http://127.0.0.1:8080", "http://localhost:4200") 
                .allowedMethods("*") 
                .allowedHeaders("*") 
                .allowCredentials(true); 
    }
}