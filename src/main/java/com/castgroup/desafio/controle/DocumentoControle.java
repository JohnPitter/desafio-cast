package com.castgroup.desafio.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.desafio.serviço.DocumentoServico;
import com.castgroup.desafio.utils.PosicaoDoc;
import com.castgroup.desafio.utils.JSON2Doc;

@RestController
@RequestMapping("/v1/diff/{id}")
public class DocumentoControle {

	@Autowired
	private DocumentoServico servico;

	@RequestMapping(value = "/left", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String esquerda(@PathVariable int id, @RequestBody JSON2Doc dados) {

		boolean resultado = servico.salvar(id, dados.getDados(), PosicaoDoc.ESQUERDA.toString());
		
		return (resultado) ? respostaJson("O documento esquerdo foi salvo com sucesso!")
				: respostaJson("O documento não foi salvo!");
	}

	@RequestMapping(value = "/right", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String direita(@PathVariable int id, @RequestBody JSON2Doc dados) {

		boolean resultado = servico.salvar(id, dados.getDados(), PosicaoDoc.DIREITA.toString());
		
		return (resultado) ? respostaJson("O documento direito foi salvo com sucesso!")
				: respostaJson("O documento não foi salvo!");
	}

	@RequestMapping(method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public String diferenca(@PathVariable int id) {
		return respostaJson(servico.validaDocumento(id));
	}

	public String respostaJson(String mensagem) {
		return "{\"" + "Resultado" + "\":" + "\"" + mensagem + "\"" + "}";
	}
}
