package br.com.transactionstatus.repository;

import br.com.transactionstatus.model.Transacao;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TransacaoMockRepository {

    private List<Transacao> transacoesMock = new ArrayList<>();

    public void save(Transacao transacao) {
        this.transacoesMock.add(transacao);
    }

    public void deleteAll() {
        this.transacoesMock.clear();
    }

}
