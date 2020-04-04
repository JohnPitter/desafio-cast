package com.castgroup.desafio.utils;

public enum PosicaoDoc {
	ESQUERDA, DIREITA;
	
	@Override
	public String toString() {
		switch(this) {
		case ESQUERDA:
			return "ESQUERDA";
		case DIREITA:
			return "DIREITA";
		}
		throw new Error("Um erro ocorreu no posicionamento do documento!");
	}

}
