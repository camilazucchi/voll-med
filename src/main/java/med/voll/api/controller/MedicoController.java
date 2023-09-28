package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.DTO.DadosAtualizacaoMedico;
import med.voll.api.DTO.DadosCadastroMedico;
import med.voll.api.DTO.DadosDetalhamentoMedico;
import med.voll.api.DTO.DadosListagemMedico;
import med.voll.api.entity.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
// Essa anotação indica que essa classe é um controlador REST, ou seja, ele irá receber solicitações HTTP e responder
// com dados no formato JSON ou XML.
@RequestMapping("/medicos")
// Define um mapeamento para a raiz do controlador. Isso significa que todas as solicitações que começam com "/medicos"
// serão manipuladas por este controlador.
public class MedicoController {
    @Autowired
    // A classe "MedicoController" possui uma instância do "MedicoRepository" injetada usando a anotação "@Autowired".
    // Isso permite que o controlador acesse métodos para interagor com o banco de dados relacionado aos médicos.
    private MedicoRepository repository;

    @PostMapping
    // Essa anotação especifica que esse método trata solicitações POST.
    @Transactional
    // Essa anotação indica que o método deve ser executado dentro de uma transação, garantindo que as operações
    // no banco de dados sejam atômicas.
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        // Esse método espera um objeto "DadosCadastroMedico" no corpo da solicitação HTTP. O corpo da solicitação
        // será convertido automaticamente em um objeto "dados" pelo Spring.
        var medico = new Medico(dados);
        repository.save(medico);
        // Aqui, um novo objeto "Medico" é criado com base nos dados recebidos na solicitação e é salvo no banco
        // de dados usando o método "save" do "MedicoRepository".
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        // Esse método trata requisições GET para listar médicos. Ele acessa o banco de dados, busca todos os registros
        // da entidade "Medico", transforma esses registros em objetos "DadosListagemMedico" e retorna uma lista
        // desses objetos como respostas à solicitação HTTP.


        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        // Esse método atualiza um registro no banco de dados.
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        // Esse método torna inativo um registro no banco de dados.
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}
