package com.castgroup.desafio.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Documento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	private String esquerda;
	private String direita;

	public Documento() {};
	
	public Documento(long id, String esquerda, String direita) {
		this.id = id;
		this.esquerda = esquerda;
		this.direita = direita;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(String esquerda) {
		this.esquerda = esquerda;
	}

	public String getDireita() {
		return direita;
	}

	public void setDireita(String direita) {
		this.direita = direita;
	}
}
