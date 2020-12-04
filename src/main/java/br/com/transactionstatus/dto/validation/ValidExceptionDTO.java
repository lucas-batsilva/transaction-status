package br.com.transactionstatus.dto.validation;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidExceptionDTO {
    private List<ErrorValidationDTO> message;
}
