package med.voll.api.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// Essa anotação é usada para criar um componente que lida com exceções de maneira global em controladores REST.
public class MedVollExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    // Essa anotação é usada em um método de um controlador para indicar que o método deve ser invocado quando uma
    // exceção do tipo "EntityNotFoundException" é lançada durante o processamento de uma requisição HTTP.
    public void handleResourceNotFoundException() {

    }

}
