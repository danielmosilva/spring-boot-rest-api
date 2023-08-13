package med.vol.api.paciente;

import med.vol.api.endereco.DadosDetalhadosEndereco;


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
