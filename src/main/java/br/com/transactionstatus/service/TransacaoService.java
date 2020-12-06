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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransacaoService {

    TransacaoMockRepository transacaoRepository = new TransacaoMockRepository();

    private Logger logger = LoggerFactory.getLogger(TransacaoService.class);

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

    public StatisticsDTO returnStatistic(Long quantidadeSegundos) {

        Long tempoInicial = System.currentTimeMillis();

        OffsetDateTime dataHoraMenosQuantidadeSegundos = OffsetDateTime.now().minus(quantidadeSegundos, ChronoUnit.SECONDS);

        List<Transacao> transacoesUltimosSegundos = list().stream()
                .filter(t -> t.getDataHora().isAfter(dataHoraMenosQuantidadeSegundos))
                .collect(Collectors.toList());

        DoubleSummaryStatistics doubleSummaryStatistics = transacoesUltimosSegundos.stream()
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
        logger.info("Calculos estatísticos concluídos");
        logger.info("Tempo gasto para a realização dos calculos: " +
                ((System.currentTimeMillis() - tempoInicial) / 1000d) + " segundos");
        return statisticsDTO;

    }

}
