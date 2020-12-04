package br.com.transactionstatus.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class Transacao {

    @PositiveOrZero
    @NotNull
    private BigDecimal valor;

    @Past
    @NotNull
    private OffsetDateTime dataHora;

}
