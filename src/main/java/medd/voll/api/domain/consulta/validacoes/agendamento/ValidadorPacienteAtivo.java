package medd.voll.api.domain.consulta.validacoes.agendamento;

import medd.voll.api.domain.ValicacaoException;
import medd.voll.api.domain.consulta.DadosAgendamentoConsulta;
import medd.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValicacaoException("Consulta não poder ser agendada com paciente excluido");
        }
    }

}
