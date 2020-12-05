package br.com.transactionstatus.controller;

import br.com.transactionstatus.model.Transacao;
import br.com.transactionstatus.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping("/transacao")
    public ResponseEntity<?> create (@RequestBody @Valid Transacao transacao) {
        return new ResponseEntity<>(transacaoService.create(transacao), HttpStatus.CREATED);
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<?> delete() {
        transacaoService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/estatistica")
    public ResponseEntity<?> returnStatistic() {
        return new ResponseEntity<>(transacaoService.returnStatistic(), HttpStatus.OK);
    }

}
