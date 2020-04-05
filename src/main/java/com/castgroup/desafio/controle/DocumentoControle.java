package com.castgroup.desafio.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.desafio.modelo.Documento;
import com.castgroup.desafio.serviço.DocumentoServico;
import com.castgroup.desafio.utils.PosicaoDoc;
import com.castgroup.desafio.utils.DocumentoUtil;
import com.castgroup.desafio.utils.JSON2Doc;

@RestController
@RequestMapping("/v1/diff/{id}")
public class DocumentoControle {

	@Autowired
	private DocumentoServico servico;
	private Documento resultadoDocumento;

	@RequestMapping(value = "/left", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String esquerda(@PathVariable long id, @RequestBody JSON2Doc dados) {

		resultadoDocumento = servico.salvar(id, dados.getDados(), PosicaoDoc.ESQUERDA.toString());
		
		return (DocumentoUtil.verficarDocEsquerda(resultadoDocumento)) ? respostaJson("O documento esquerdo foi salvo com sucesso!")
				: respostaJson("O documento esquerdo não foi salvo!");
	}

	@RequestMapping(value = "/right", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String direita(@PathVariable long id, @RequestBody JSON2Doc dados) {

		resultadoDocumento = servico.salvar(id, dados.getDados(), PosicaoDoc.DIREITA.toString());
		
		return (DocumentoUtil.verficarDocDireita(resultadoDocumento)) ? respostaJson("O documento direito foi salvo com sucesso!")
				: respostaJson("O documento não foi salvo!");
	}

	@RequestMapping(method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public String diferenca(@PathVariable long id) {
		return respostaJson(servico.validarDocumento(id));
	}

	private String respostaJson(String mensagem) {
		return "{\"" + "Resultado" + "\":" + "\"" + mensagem + "\"" + "}";
	}
}
