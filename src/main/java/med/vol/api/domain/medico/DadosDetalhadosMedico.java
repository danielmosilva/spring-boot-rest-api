package med.vol.api.domain.medico;

import med.vol.api.domain.endereco.DadosDetalhadosEndereco;

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
