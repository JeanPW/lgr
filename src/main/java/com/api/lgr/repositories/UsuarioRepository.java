package com.api.lgr.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.lgr.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

	boolean existsByEmail(String email);

	@Query("SELECT obj FROM UsuarioModel obj WHERE obj.salario >= :minSalario AND obj.salario <= :maxSalario")
	Page <UsuarioModel> pegarSalario (Double minSalario, Double maxSalario, Pageable pageable);
	
	//no SQL WHERE LOWER(name LIKE LOWER'%name%')
	
	@Query("SELECT obj FROM UsuarioModel obj WHERE LOWER(obj.nome) LIKE LOWER(CONCAT('%',:nome,'%'))")
	Page<UsuarioModel> pegarNome(String nome, Pageable pageable);

	Page<UsuarioModel> findBySalarioBetween(Double minSalario, Double maxSalario, Pageable pageable);
	
	Page<UsuarioModel> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
