package med.vol.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "[0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2}")
        String cpf,

        @Valid
        @NotNull
        DadosEndereco endereco
) {
}
