package com.api.lgr.controlers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.api.lgr.dtos.UsuarioDto;
import com.api.lgr.models.UsuarioModel;
import com.api.lgr.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Object> salvarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
		if(usuarioService.existsByEmail(usuarioDto)){
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse E-mail já está em uso!");
		}
		UsuarioModel usuarioModel = new UsuarioModel();
		BeanUtils.copyProperties(usuarioDto, usuarioModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> LocalizarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> pegarUsuario(@PathVariable(value = "id") Long id) {
		Optional<UsuarioModel> usuarioOptional = usuarioService.findById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não foi localizado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}	
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<UsuarioModel>> PegarPaginas(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll(pageable));
	}

	@GetMapping(value = "/pegar-salario")
	public ResponseEntity<Page<UsuarioModel>> pegarSalario(@RequestParam (defaultValue = "0") Double minSalario, 
														   @RequestParam (defaultValue = "10000000000") Double maxSalario, Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.pegarSalario(minSalario, maxSalario, pageable));
	}

	@GetMapping(value = "/pegar-nome")
	public ResponseEntity<Page<UsuarioModel>> pegarNome(@RequestParam (defaultValue = "") String nome, Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.pegarNome(nome, pageable));
	}	
}