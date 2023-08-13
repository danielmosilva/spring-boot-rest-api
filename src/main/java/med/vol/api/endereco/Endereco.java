package med.vol.api.endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro  = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }

    public void atualizar(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro() != null ? endereco.logradouro() : this.logradouro;
        this.bairro  = endereco.bairro() != null ? endereco.bairro() : this.bairro;
        this.cep = endereco.cep() != null ? endereco.cep() : this.cep;
        this.numero = endereco.numero() != null ? endereco.numero() : this.numero;
        this.complemento = endereco.complemento() != null ? endereco.complemento() : this.complemento;
        this.cidade = endereco.cidade() != null ? endereco.cidade() : this.cidade;;
        this.uf = endereco.uf() != null ? endereco.uf() : this.uf;
    }
}
