package br.com.transactionstatus.configuration.validation;

import java.util.ArrayList;
import java.util.List;

import br.com.transactionstatus.dto.validation.ErrorValidationDTO;
import br.com.transactionstatus.dto.validation.ValidExceptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestControllerAdvice
public class ErrorValidationHandler {

    @Autowired
    private MessageSource messageSource;

    private Logger logger = LoggerFactory.getLogger(ErrorValidationHandler.class);

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidExceptionDTO handle(MethodArgumentNotValidException exception){
        List<ErrorValidationDTO> dto = new ArrayList<>();
        ValidExceptionDTO exdto = new ValidExceptionDTO();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message= messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorValidationDTO erro = new ErrorValidationDTO(e.getField(), message);
            logger.error("Ocorreu o seguinte erro durante a inserção da transação: " + e.getField() + " " + message);
            dto.add(erro);
        });
        exdto.setMessage(dto);
        return exdto;
    }
}
