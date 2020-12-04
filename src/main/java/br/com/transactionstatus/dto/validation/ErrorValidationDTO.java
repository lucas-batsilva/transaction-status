package br.com.transactionstatus.dto.validation;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorValidationDTO {

    private String campo;
    private String erro;

}
