package com.castgroup.desafio.utils;

import com.castgroup.desafio.modelo.Documento;

public class DocumentoUtil {

	public static boolean verficarDocEsquerda(Documento doc) {

		if (doc != null) {

			if (doc.getId() != 0 || !nuloOuBranco(doc.getEsquerda())) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean verficarDocDireita(Documento doc) {

		if (doc != null) {

			if (doc.getId() != 0 || !nuloOuBranco(doc.getDireita())) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean nuloOuBranco(String parametro) {
		return parametro == null || parametro.trim().length() == 0;
	}

}
