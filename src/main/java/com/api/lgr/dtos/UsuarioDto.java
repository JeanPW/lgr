package com.api.lgr.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioDto {
	
	@NotBlank
	private	String nome;
	@NotBlank
	private String email;
	@NotNull
	private Double salario;

}
