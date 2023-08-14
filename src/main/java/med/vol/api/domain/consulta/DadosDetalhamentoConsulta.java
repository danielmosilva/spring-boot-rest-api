package med.vol.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(

        Long id,

        Long idMedico,

        Long idPaciente,

        LocalDateTime data,

        Especialidade especialidade

) {
}
