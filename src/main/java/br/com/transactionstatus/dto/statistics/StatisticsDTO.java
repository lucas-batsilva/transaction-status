package br.com.transactionstatus.dto.statistics;

import lombok.Data;

@Data
public class StatisticsDTO {

    private Long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;

}
