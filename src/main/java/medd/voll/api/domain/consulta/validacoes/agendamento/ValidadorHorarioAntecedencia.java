package medd.voll.api.domain.consulta.validacoes.agendamento;

import medd.voll.api.domain.ValicacaoException;
import medd.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia {

    public void  validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora,dataConsulta).toMinutes();
        if (diferencaEmMinutos < 30){
            throw  new ValicacaoException("Consulta deve ser agendada com antecedencia minima de 30 minutos");
        }
    }

}
