package med.vol.api.domain.paciente;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.domain.endereco.Endereco;
import jakarta.persistence.*;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private boolean ativo;


    public Paciente(DadosCadastroPaciente endereco) {
        this.ativo = true;
        this.nome = endereco.nome();
        this.email = endereco.email();
        this.telefone = endereco.telefone();
        this.cpf = endereco.cpf();
        this.endereco = new Endereco(endereco.endereco());
    }

    public void atualizar(DadosAtualizacaoPaciente dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.telefone = dados.telefone() != null ? dados.telefone() : this.telefone;

        if(dados.endereco() != null)
            this.endereco.atualizar(dados.endereco());
    }

    public void excluir() {
        this.ativo = false;
    }
}
