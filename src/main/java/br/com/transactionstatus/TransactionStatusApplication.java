package br.com.transactionstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class TransactionStatusApplication {
    private static final Logger logger = LoggerFactory.getLogger(TransactionStatusApplication.class);
    public static void main(String[] args) {
        logger.info("Iniciando a aplicação");
        SpringApplication.run(TransactionStatusApplication.class, args);
        logger.info("A aplicação foi iniciada e já está apta a receber requisições");
    }

}
