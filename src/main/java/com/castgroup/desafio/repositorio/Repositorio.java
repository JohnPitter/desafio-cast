package com.castgroup.desafio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castgroup.desafio.modelo.*;

public interface Repositorio extends JpaRepository<Documento, Long> {
	
	Documento findById(long id);
}
