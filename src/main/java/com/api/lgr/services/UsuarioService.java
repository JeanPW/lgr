package com.api.lgr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.lgr.dtos.UsuarioDto;
import com.api.lgr.models.UsuarioModel;
import com.api.lgr.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioModel save(UsuarioModel usuarioModel) {
		return usuarioRepository.save(usuarioModel);
	}
	
	public boolean existsByEmail(UsuarioDto usuarioDto) {
		return usuarioRepository.existsByEmail(usuarioDto.getEmail());
	}	
	
	public List<UsuarioModel> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Optional<UsuarioModel> findById(Long id){
		return usuarioRepository.findById(id);
	}

	public Page<UsuarioModel> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	public Page <UsuarioModel> pegarSalario (Double minSalario, Double maxSalario, Pageable pageable) {
		return usuarioRepository.findBySalarioBetween(minSalario, maxSalario, pageable);
	}

	public Page <UsuarioModel> pegarNome (String nome, Pageable pageable) {
		return usuarioRepository.findByNomeContainingIgnoreCase(nome, pageable);
	}

}
