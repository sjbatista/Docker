package com.gvendas.gestaovendas.excecao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		gerarListaDeErros(ex.getBindingResult());
		
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	private List<Erro> gerarListaDeErros(BindingResult bindingResult) {
		// TODO Auto-generated method stub
		List<Erro> erros = new ArrayList<Erro>();
		bindingResult.getFieldErrors().forEach(fieldError -> {
			String msgUsuario = fieldError.getDefaultMessage();
			String msgDesenvolvedor = fieldError.toString();
			erros.add(new Erro(msgUsuario,msgDesenvolvedor));
		});
		
		return erros;
	}
}
