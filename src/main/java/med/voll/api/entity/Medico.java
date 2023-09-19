package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.DTO.DadosAtualizacaoMedico;
import med.voll.api.DTO.DadosCadastroMedico;
import med.voll.api.enums.Especialidade;

// Anotações JPA que ajudam a mapear essa classe para uma tabela no banco de dados.
@Table(name = "medicos")
// Define o nome da tabela no banco de dados como "medicos".
@Entity(name = "Medico")
// Define essa classe como uma entidade JPA com o nome "Medico".
// Os objetos da classe "Medico" serão armazenados no banco de dados na tabela "medicos".
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // "@Id" marca o campo "id" como a chave primária (primary key) da tabela.
    // "@GeneratedValue" especifica que o valor da chave primária será gerado automaticamente pelo banco de dados,
    // usando uma estratégia de incremento automático, que é comumente usada com banco de dados que suportam isso,
    // como MySQL e PostgreSQL.
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    // Essa anotação é usada para mapear a enumeração "Especialidade" como uma coluna no banco de dados.
    // "EnumType.STRING" indica que os valores dessa enumeração serão armazenados como strings no banco de dados,
    // em vez de valores inteiros.
    private Especialidade especialidade;

    @Embedded
    // Essa anotação é usada para indicar que o campo "endereco" é um objeto incorporado (embedded) no objeto
    // "Medico". Isso significa que os campos do objeto "Endereco" serão armazenados na mesma tabela do médico no
    // banco de dados, em vez de em uma tabela separada.
    private Endereco endereco;

    @Column(name = "ativos", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        this.nome = (dados.nome() != null) ? dados.nome() : this.nome;
        this.telefone = (dados.telefone() != null) ? dados.telefone() : this.telefone;

        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
