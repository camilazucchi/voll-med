package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.DTO.DadosAgendamentoConsulta;
import med.voll.api.DTO.DadosCancelamentoConsulta;
import med.voll.api.DTO.DadosDetalhamentoConsulta;
import med.voll.api.service.AgendaDeConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    // Importante: a classe controller não deve conter regras de negócio!
    @Autowired
    private AgendaDeConsultasService agendaDeConsultasService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        agendaDeConsultasService.agendarConsulta(dadosAgendamentoConsulta);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<DadosCancelamentoConsulta> cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendaDeConsultasService.cancelarConsulta(dados);
        return ResponseEntity.noContent().build();
    }

}
