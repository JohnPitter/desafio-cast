package com.castgroup.desafio.serviço;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castgroup.desafio.modelo.Documento;
import com.castgroup.desafio.repositorio.Repositorio;
import com.castgroup.desafio.utils.Mensagens;
import com.castgroup.desafio.utils.DocumentoUtil;
import com.castgroup.desafio.utils.DocumentoPosicao;

@Service
public class DocumentoServico {
	
	@Autowired
	public Repositorio repositorio;
	
	public Documento salvar(long id, String dados, String posicao){
		Documento documento = null;
	
		try {
			
			if(!DocumentoUtil.nuloOuBranco(dados)) {			
				documento = repositorio.findById(id);
				
				if(documento == null) {
					documento = new Documento();
					documento.setId(id);
				}
				
				if(DocumentoPosicao.ESQUERDA.toString().equals(posicao)) {
					documento.setEsquerda(dados);
				} else if(DocumentoPosicao.DIREITA.toString().equals(posicao)) {
					documento.setDireita(dados);
				} else {
					return documento;
				}
				
				documento = repositorio.save(documento);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return documento;
	}
	
	public String validarDocumento(long id) {
		Documento documento = repositorio.findById(id);
		
		if(documento == null) {
			return Mensagens.DOC_NAO_ENCONTRADO;
		}
		
		if(DocumentoUtil.nuloOuBranco(documento.getEsquerda()) || DocumentoUtil.nuloOuBranco(documento.getDireita())) {
			return Mensagens.DOC_INCOMPLETO;
		}
		
		byte[] bytesEsquerda = interprete(Mensagens.UNCODE, documento.getEsquerda()).getBytes();
		byte[] bytesDireita = interprete(Mensagens.UNCODE, documento.getDireita()).getBytes();
		
		boolean resultado = Arrays.equals(bytesEsquerda, bytesDireita);
		
		String posicao = "";
		
		if(resultado) {
			return "Documentos " + id + " idênticos";
		} else if(bytesEsquerda.length != bytesDireita.length) {
			return "Documentos " + id + " com tamanhos diferentes";
		} else {
			byte diferente = 0;
			for(int i = 0; i < bytesEsquerda.length; i++) {
				diferente = (byte) (bytesEsquerda[i] ^ bytesDireita[i]);
				
				if(diferente != 0) {
					posicao += " " + i;
				}
			}
		}
		return Mensagens.DOC_TAM_DIVERGENTE.concat(posicao);
		
	}
	
	private String interprete(String comando, String dados) {
		Base64 b64 = new Base64();		
		/*if(comando.equals(Mensagens.CODE)) {
			return b64.encodeAsString(dados.getBytes());
		} else */
		if (comando.equals(Mensagens.UNCODE)) 
			return new String(b64.decode(dados), Charset.forName(Mensagens.ENCODING));
		 else 
			return Mensagens.COMANDO_INVALIDO;
	}
}
