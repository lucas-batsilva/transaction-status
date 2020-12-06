package br.com.transactionstatus.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class Transacao {

    @ApiModelProperty(value = "Valor monetário da transação")
    @PositiveOrZero
    @NotNull
    private BigDecimal valor;

    @ApiModelProperty(value = "Data e hora da transação")
    @Past
    @NotNull
    private OffsetDateTime dataHora;

}
