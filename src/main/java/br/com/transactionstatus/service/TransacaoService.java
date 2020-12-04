package br.com.transactionstatus.service;

import br.com.transactionstatus.model.Transacao;
import br.com.transactionstatus.repository.TransacaoMockRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    TransacaoMockRepository transacaoRepository = new TransacaoMockRepository();

    public Transacao create(Transacao transacao) {
        transacaoRepository.save(transacao);
        return transacao;
    }

}
