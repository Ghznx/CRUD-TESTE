package br.com.senai.teste.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ValidacaoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(MethodArgumentNotValidException erro){
        
        Map<String, String> erros = new LinkedHashMap<>();

        erro.getBindingResult().getFieldErrors().forEach(e -> {
            erros.put(e.getField(), e.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(erros);
    }
}
