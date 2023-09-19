package med.voll.api.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.DTO.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
