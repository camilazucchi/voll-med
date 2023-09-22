package med.voll.api.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.enums.Especialidade;

public record DadosCadastroMedico(
        @NotBlank(message = "{validation.notblank}")
        // A anotação "@NotBlank" é apenas para campos do tipo String.
        String nome,
        @NotBlank(message = "{validation.notblank}")
        @Email
        String email,
        @NotBlank(message = "{validation.notblank}")
        String telefone,
        @NotBlank(message = "{validation.notblank}")
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull(message = "{validation.notnull}")
        Especialidade especialidade,
        @NotNull(message = "{validation.notnull}")
        @Valid
        DadosEndereco endereco) {
}