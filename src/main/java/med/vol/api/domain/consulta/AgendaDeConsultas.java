package med.vol.api.domain.consulta;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsultas;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.MedicoRepository;
import med.vol.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
            throw new ValidacaoException("Não foi possivel efetuar o agendamento pois o id do médico não existe");

        if(!pacienteRepository.existsById(dados.idPaciente()))
            throw new ValidacaoException("Não foi possivel efetuar o agendamento pois o id do paciente não existe");


        validadores.forEach( v -> v.validar(dados));

        var medico = escolherMedico(dados);

        if (medico == null)
            throw new ValidacaoException("Nao existe medico disponvel nessa data");

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(),null);

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if (dados.idMedico() != null)
            return medicoRepository.getReferenceById(dados.idMedico());

        if (dados.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatoria quando medico nao e selecionado");

        return medicoRepository.escolherMedicoLivreNaData(dados.especialidade(), dados.data());

    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }


}
