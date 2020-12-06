package br.com.transactionstatus.controller;

import br.com.transactionstatus.model.Transacao;
import br.com.transactionstatus.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    private Logger logger = LoggerFactory.getLogger(TransacaoController.class);

    @PostMapping("/transacao")
    public ResponseEntity<?> create (@RequestBody @Valid Transacao transacao) {
        logger.info("A requisição foi recebida e está sendo processada");
        return new ResponseEntity<>(transacaoService.create(transacao), HttpStatus.CREATED);
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<?> delete() {
        logger.info("A requisição foi recebida e está sendo processada");
        transacaoService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/estatistica")
    public ResponseEntity<?> returnStatistic() {
        logger.info("A requisição foi recebida e está sendo processada");
        return new ResponseEntity<>(transacaoService.returnStatistic(), HttpStatus.OK);
    }

}
