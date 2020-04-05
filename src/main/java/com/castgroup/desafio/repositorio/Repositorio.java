package com.castgroup.desafio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castgroup.desafio.modelo.*;

@Repository
public interface Repositorio extends JpaRepository<Documento, Long> {
	Documento findById(long id);
}
