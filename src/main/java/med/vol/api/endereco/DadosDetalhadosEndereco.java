package med.vol.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosDetalhadosEndereco(

       String logradouro,

        String bairro,

         String cep,

       String cidade,

        String uf,

        String numero,

        String complemento

) {

    public DadosDetalhadosEndereco(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(), endereco.getNumero(), endereco.getComplemento());
    }
}
