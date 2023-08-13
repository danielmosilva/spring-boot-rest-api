package med.vol.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank(message = "{medico.nome.obrigatorio}")
        String nome,

        @NotBlank(message = "{medico.email.obrigatorio}")
        @Email(message = "{medico.email.invalido}")
        String email,

        @NotBlank(message = "{medico.telefone.obrigatorio}")
        String telefone,

        @NotBlank(message = "{medico.crm.obrigatorio}")
        @Pattern(regexp= "\\d{4,6}", message = "{medico.crm.invalido}")
        String crm,

        @NotNull(message = "{medico.especialidade.obrigatoria}")
        Especialidade especialidade,

        @NotNull(message = "{medico.endereco.obrigatorio}")
        @Valid
        DadosEndereco endereco
) {

}
