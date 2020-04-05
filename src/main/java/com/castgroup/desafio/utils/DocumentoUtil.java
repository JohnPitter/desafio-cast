package com.castgroup.desafio.utils;

import com.castgroup.desafio.modelo.Documento;

public class DocumentoUtil {

	public static boolean validarDocumentoEsquerdo(Documento documento) {
		if (documento != null) {
			if (documento.getId() != 0 || !nuloOuBranco(documento.getEsquerda())) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean validarDocumentoDireito(Documento documento) {
		if (documento != null) {
			if (documento.getId() != 0 || !nuloOuBranco(documento.getDireita())) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean nuloOuBranco(String valor) {
		return valor == null || valor.trim().length() == 0;
	}

	public static String respostaJson(String retorno) {
		return "{\"" + "Resultado" + "\":" + "\"" + retorno + "\"" + "}";
	}
}
