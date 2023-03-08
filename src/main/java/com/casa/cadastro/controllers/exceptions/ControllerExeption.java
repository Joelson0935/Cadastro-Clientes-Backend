package com.casa.cadastro.controllers.exceptions;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.casa.cadastro.services.exception.ObjetoNaoEncontrado;

@RestControllerAdvice
public class ControllerExeption {

	@ExceptionHandler(ObjetoNaoEncontrado.class)
	public ResponseEntity<Error> objectNotFound(ObjetoNaoEncontrado e) {
		Error error = new Error("Objeto Não Encontrado", HttpStatus.NOT_FOUND.value(), LocalDate.now());
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class) // HttpServletRequest
	public ResponseEntity<ErrorsList> argumentoInvalido(MethodArgumentNotValidException e, HttpServletRequest request) {
		ErrorsList errorsList = new ErrorsList("Argumento Irregular", HttpStatus.BAD_REQUEST.value(), LocalDate.now());
		for (FieldError field : e.getBindingResult().getFieldErrors()) {
			errorsList.setCampos(field.getField(), field.getDefaultMessage());
		}
		return new ResponseEntity<ErrorsList>(errorsList, HttpStatus.BAD_REQUEST);
	}

}
