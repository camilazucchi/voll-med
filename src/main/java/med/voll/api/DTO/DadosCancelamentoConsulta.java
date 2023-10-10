package med.voll.api.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.enums.MotivoCancelamentoConsulta;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamentoConsulta motivoCancelamentoConsulta
) {
}
