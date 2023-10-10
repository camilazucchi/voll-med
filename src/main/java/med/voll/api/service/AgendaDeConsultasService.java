package med.voll.api.service;

import med.voll.api.DTO.DadosAgendamentoConsulta;
import med.voll.api.DTO.DadosCancelamentoConsulta;
import med.voll.api.entity.Consulta;
import med.voll.api.entity.Medico;
import med.voll.api.exceptions.NaoEncontradoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// A classe 'Service' executa as regras de negócio e validações da aplicação.
public class AgendaDeConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendarConsulta(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new NaoEncontradoException("ID do paciente informado não existe.");
        }

        // Verifica primeiro se o ID do médico não é nulo, caso não seja nulo, verifica se o ID do médico existe
        // no Banco de Dados.
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new NaoEncontradoException("ID do médico informado não existe.");
        }


        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        // Checa o retorno do ID do médico, caso seja existente, retorna o ID do médico do Banco de Dados.
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        // Caso não tenha um ID do médico:
        if (dados.especialidade() == null) {
            throw new NaoEncontradoException("A especialidade do médico é obrigatória quando médico não for escolhido.");
        }

        return medicoRepository.selectRandomDoctorOnDate(dados.especialidade(), dados.data());
    }

    public void cancelarConsulta(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new NaoEncontradoException("ID informado da consulta não existe.");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelarConsulta(dados.motivoCancelamentoConsulta());
    }

}
