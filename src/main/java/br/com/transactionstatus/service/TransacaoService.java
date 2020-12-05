package br.com.transactionstatus.service;

import br.com.transactionstatus.dto.statistics.StatisticsDTO;
import br.com.transactionstatus.model.Transacao;
import br.com.transactionstatus.repository.TransacaoMockRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    TransacaoMockRepository transacaoRepository = new TransacaoMockRepository();

    public Transacao create(Transacao transacao) {
        transacaoRepository.save(transacao);
        return transacao;
    }

    public void deleteAll() {
        transacaoRepository.deleteAll();
    }

    public List<Transacao> list() {
        return transacaoRepository.list();
    }

    public StatisticsDTO returnStatistic() {

        OffsetDateTime dataHoraUltimoMinuto = OffsetDateTime.now().minus(60, ChronoUnit.SECONDS);

        List<Transacao> transacoesUltimoMinuto = list().stream()
                .filter(t -> t.getDataHora().isAfter(dataHoraUltimoMinuto))
                .collect(Collectors.toList());

        DoubleSummaryStatistics doubleSummaryStatistics = transacoesUltimoMinuto.stream()
                .mapToDouble(t -> t.getValor().doubleValue()).summaryStatistics();

        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setCount(doubleSummaryStatistics.getCount());
        statisticsDTO.setSum(doubleSummaryStatistics.getSum());
        statisticsDTO.setAvg(doubleSummaryStatistics.getAverage());
        if(statisticsDTO.getCount() == 0) {
            statisticsDTO.setMin(0d);
            statisticsDTO.setMax(0d);
        } else {
            statisticsDTO.setMin(doubleSummaryStatistics.getMin());
            statisticsDTO.setMax(doubleSummaryStatistics.getMax());
        }
        
        return statisticsDTO;

    }

}
