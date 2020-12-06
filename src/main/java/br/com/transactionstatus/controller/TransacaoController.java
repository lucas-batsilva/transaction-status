package br.com.transactionstatus.controller;

import br.com.transactionstatus.model.Transacao;
import br.com.transactionstatus.service.TransacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    private Logger logger = LoggerFactory.getLogger(TransacaoController.class);

    @ApiOperation(value = "Recebe a transação")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "A transação foi aceita"),
        @ApiResponse(code = 422, message = "A transação não foi aceita pois não cumpriu os critérios de aceite"),
        @ApiResponse(code = 400, message = "A API não compreendeu a requisição")
    })
    @PostMapping("/transacao")
    public ResponseEntity<?> create (@RequestBody @Valid Transacao transacao) {
        logger.info("A requisição foi recebida e está sendo processada");
        return new ResponseEntity<>(transacaoService.create(transacao), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Apaga todos os dados de transações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Todas as informações foram apagadas com sucesso"),
            @ApiResponse(code = 400, message = "A API não compreendeu a requisição")
    })
    @DeleteMapping("/transacao")
    public ResponseEntity<?> delete() {
        logger.info("A requisição foi recebida e está sendo processada");
        transacaoService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna calculos estatísticos sobre as transações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornou os dados estatísticos"),
            @ApiResponse(code = 400, message = "A API não compreendeu a requisição")
    })
    @GetMapping("/estatistica")
    public ResponseEntity<?> returnStatistic(@RequestParam(required = false) Long quantidadeSegundos) {
        logger.info("A requisição foi recebida e está sendo processada");
        return new ResponseEntity<>(transacaoService.returnStatistic(quantidadeSegundos != null ? quantidadeSegundos : 60),
                HttpStatus.OK);
    }

}
