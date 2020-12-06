package br.com.transactionstatus;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.transactionstatus.model.Transacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void tentaCriarTransacaoAceita() throws Exception {

        Transacao transacao = new Transacao();
        transacao.setDataHora(OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00"));
        transacao.setValor(BigDecimal.valueOf(123.45));
        mockMvc.perform(post("/api/transacao")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isCreated());
    }

    @Test
    public void tentaCriarTransacaoNoFuturo() throws Exception {

        Transacao transacaoFuturo = new Transacao();
        transacaoFuturo.setDataHora(OffsetDateTime.parse("2021-08-07T12:34:56.789-03:00"));
        transacaoFuturo.setValor(BigDecimal.valueOf(123.45));
        mockMvc.perform(post("/api/transacao")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transacaoFuturo)))
                .andExpect(status().isCreated());
    }

    @Test
    public void tentaCriarTransacaoComValorNegativo() throws Exception {

        Transacao transacaoValorNegativo = new Transacao();
        transacaoValorNegativo.setDataHora(OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00"));
        transacaoValorNegativo.setValor(BigDecimal.valueOf(-123.45));
        mockMvc.perform(post("/api/transacao")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transacaoValorNegativo)))
                .andExpect(status().isCreated());
    }


    @Test
    public void tentaCriarTransacaoRequisicaoInvalida() throws Exception {

        Transacao transacaoRequisicaoInvalida = null;
        mockMvc.perform(post("/api/transacao")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transacaoRequisicaoInvalida)))
                .andExpect(status().isCreated());
    }

}
