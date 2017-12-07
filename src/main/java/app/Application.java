package app;


import app.model.ToDo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    public static void main(String args[]) {
        SpringApplication.run(LocalConfig.class, args);
    }

    @Configuration
    @Profile("local")
    @ComponentScan(lazyInit = true)
    static class LocalConfig {
    }
}



