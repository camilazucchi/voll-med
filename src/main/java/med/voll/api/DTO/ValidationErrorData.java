package med.voll.api.DTO;

import org.springframework.validation.FieldError;

public record ValidationErrorData(String campo, String mensagem) {
    public ValidationErrorData(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
