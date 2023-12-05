package com.dac.api.app.exception;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dac.api.app.dto.ErrorMessageDTO;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleUsernameNotFoundException(
            UsernameNotFoundException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage(), "email");
        dto.add(error);

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleAuthenticationException(
            AuthenticationException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage(), "password");
        dto.add(error);

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleUserFoundException(
            UserFoundException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage(), e.getLocalizedMessage());
        dto.add(error);

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EditionNotFoundException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleEditionNotFoundException(
            EditionNotFoundException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage(), "");
        dto.add(error);

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleEventNotFoundException(
            EventNotFoundException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage(), "");
        dto.add(error);

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

    }

}
