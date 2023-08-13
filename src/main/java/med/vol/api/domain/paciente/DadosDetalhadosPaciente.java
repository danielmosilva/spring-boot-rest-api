package med.vol.api.domain.paciente;

import med.vol.api.domain.endereco.DadosDetalhadosEndereco;


public record DadosDetalhadosPaciente(


        Long id,

        String nome,

        String email,

        String telefone,

        String cpf,

        DadosDetalhadosEndereco endereco
) {

    public DadosDetalhadosPaciente(Paciente paciente)
    {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), new DadosDetalhadosEndereco(paciente.getEndereco()));
    }

}
