package med.vol.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.endereco.DadosDetalhadosEndereco;
import med.vol.api.endereco.DadosEndereco;

public record DadosDetalhadosMedico(

        Long id,

        String nome,

        String email,

        String telefone,

        String crm,

        Especialidade especialidade,

        DadosDetalhadosEndereco endereco
) {

    public DadosDetalhadosMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), new DadosDetalhadosEndereco(medico.getEndereco()));
    }
}
