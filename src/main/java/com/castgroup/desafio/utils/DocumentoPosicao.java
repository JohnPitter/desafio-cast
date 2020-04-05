package com.castgroup.desafio.utils;

public enum DocumentoPosicao {
	ESQUERDA, DIREITA;
	
	@Override
	public String toString() {
		switch(this) {
		case ESQUERDA:
			return "ESQUERDA";
		case DIREITA:
			return "DIREITA";
		}
		return "Um erro ocorreu no posicionamento do documento!";
	}

}
