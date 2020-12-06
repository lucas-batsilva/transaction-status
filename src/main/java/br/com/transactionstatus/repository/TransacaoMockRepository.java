package br.com.transactionstatus.repository;

import br.com.transactionstatus.model.Transacao;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Data
public class TransacaoMockRepository {

    private List<Transacao> transacoesMock = new ArrayList<>();

    private Logger logger = LoggerFactory.getLogger(TransacaoMockRepository.class);

    public void save(Transacao transacao) {
        this.transacoesMock.add(transacao);
        logger.info("A transação recebida foi incluida com sucesso");
    }

    public void deleteAll() {
        this.transacoesMock.clear();
        logger.info("Todas as transações foram excluídas com sucesso");
    }

    public List<Transacao> list() {
        return this.transacoesMock;
    }

}
