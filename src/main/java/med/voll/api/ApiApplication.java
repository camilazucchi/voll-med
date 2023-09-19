package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
    /*Essa é a classe principal do aplicativo. Ela é responsável por iniciar o aplicativo Spring Boot e é
    onde a execução do programa começa.*/
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}