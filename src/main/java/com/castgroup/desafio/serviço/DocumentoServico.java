package com.castgroup.desafio.serviço;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.castgroup.desafio.modelo.Documento;
import com.castgroup.desafio.repositorio.Repositorio;
import com.castgroup.desafio.utils.PosicaoDoc;

@Service
public class DocumentoServico {
	
	@Autowired
	public Repositorio repositorio;
	
	public boolean resultado = false;
	
	public Documento salvar(int id, String dados, String posicao){
		Documento doc = null;
		
		try {
			
			if(validacao(dados)) {			
				doc = repositorio.findById(id);
				
				if(doc == null) {
					doc = new Documento();
					doc.setId(id);
				}
				
				if(PosicaoDoc.ESQUERDA.toString().equals(posicao)) {
					doc.setEsquerda(decodificarB64(dados));
				} else if(PosicaoDoc.DIREITA.toString().equals(posicao)) {
					doc.setDireita(decodificarB64(dados));
				} else {
					return doc;
				}
				
				doc = repositorio.save(doc);
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return doc;
	}
	
	private boolean validacao(String dados) {
		return (StringUtils.isEmpty(dados)) ? false : true;
	}
	
	public String validaDocumento(int id) {
		Documento doc = repositorio.findById(id);
		
		if(doc == null) {
			return "Nenhum dado encontrado!";
		}
		
		if(StringUtils.isEmpty(doc.getEsquerda()) || StringUtils.isEmpty(doc.getDireita())) {
			return "Dados incompletos!";
		}
		
		byte[] bytesEsquerda = doc.getEsquerda().getBytes();
		byte[] bytesDireita = doc.getDireita().getBytes();
		
		boolean resultado = Arrays.equals(bytesEsquerda, bytesDireita);
		
		String posicao = "";
		
		if(resultado) {
			return "Os documentos são iguais!";
		} else if(bytesEsquerda.length != bytesDireita.length) {
			return "Os documentos não tem o mesmo tamanho!";
		} else {
			byte diferente = 0;
			
			for(int i = 0; i < bytesEsquerda.length; i++) {
				diferente = (byte) (bytesEsquerda[i] ^ bytesDireita[i]);
				
				if(diferente != 0) {
					posicao += " " + i;
				}
			}
		}
		
		return "Os documentos têm o mesmo tamanho, porém divergem a partir da posição:".concat(posicao);
		
	}
	
	/*
	private String codificarB64(String dado) {
		return Base64.getEncoder().encodeToString(dado.getBytes());
	}*/


	private String decodificarB64(String dado) {
	    byte[] bytesDecodificados = Base64.getDecoder().decode(dado);
	    return new String(bytesDecodificados, Charset.forName("UTF-8"));
	}

}
