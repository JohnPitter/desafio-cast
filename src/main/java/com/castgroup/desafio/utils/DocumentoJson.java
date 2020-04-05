package com.castgroup.desafio.utils;

public class DocumentoJson {
	
	private String dados;
	
	public DocumentoJson() {};
	
	public DocumentoJson(String dados) {
		this.dados = dados;
	}
	
	public String getRetornoJson() {
		return dados;
	}
	
	public void setDados(String dados) {
		this.dados = dados;
	}

}
