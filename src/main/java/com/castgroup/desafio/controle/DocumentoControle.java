package com.castgroup.desafio.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.desafio.modelo.Documento;
import com.castgroup.desafio.serviço.DocumentoServico;
import com.castgroup.desafio.utils.DocumentoPosicao;
import com.castgroup.desafio.utils.Mensagens;
import com.castgroup.desafio.utils.DocumentoUtil;
import com.castgroup.desafio.utils.DocumentoJson;

@RestController
@RequestMapping("/v1/diff/{id}")
public class DocumentoControle {

	@Autowired
	private DocumentoServico servico;
	private Documento resultadoDocumento;

	@RequestMapping(value = "/left", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String documentoEsquerdo(@PathVariable long id, @RequestBody DocumentoJson dados) {

		resultadoDocumento = servico.salvar(id, dados.getRetornoJson(), DocumentoPosicao.ESQUERDA.toString());
		
		return (DocumentoUtil.validarDocumentoEsquerdo(resultadoDocumento)) ? DocumentoUtil.respostaJson(Mensagens.DOC_ESQUERDO_SALVO_SUCESSO)
				: DocumentoUtil.respostaJson(Mensagens.DOC_ESQUERDO_NAO_SALVO);
	}

	@RequestMapping(value = "/right", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String documentoDireito(@PathVariable long id, @RequestBody DocumentoJson dados) {

		resultadoDocumento = servico.salvar(id, dados.getRetornoJson(), DocumentoPosicao.DIREITA.toString());
		
		return (DocumentoUtil.validarDocumentoDireito(resultadoDocumento)) ? DocumentoUtil.respostaJson(Mensagens.DOC_DIREITO_SALVO_SUCESSO)
				: DocumentoUtil.respostaJson(Mensagens.DOC_DIREITO_NAO_SALVO);
	}

	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String documentoDiferenca(@PathVariable long id) {
		return DocumentoUtil.respostaJson(servico.validarDocumento(id));
	}

	
}
