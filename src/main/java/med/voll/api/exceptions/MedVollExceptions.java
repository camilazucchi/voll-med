package med.voll.api.exceptions;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.DTO.ValidationErrorData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
// Essa anotação é usada para criar um componente que lida com exceções de maneira global em controladores REST.
public class MedVollExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    // Essa anotação é usada em um método de um controlador para indicar que o método deve ser invocado quando uma
    // exceção do tipo "EntityNotFoundException" é lançada durante o processamento de uma requisição HTTP.
    public ResponseEntity<String> handleResourceNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Essa anotação é usada para tratar exceções do tipo "MethodNotAllowedException".
    // É lançada quando ocorre uma validação de argumento em um método de controle e essa validação falha devido
    // a dados de entrada inválidos.
    public ResponseEntity<List<ValidationErrorData>> handleBadRequestException(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorData::new).toList());
    }
}
