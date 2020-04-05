package com.castgroup.desafio.controle;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.castgroup.desafio.repositorio.Repositorio;
import com.castgroup.desafio.utils.Mensagens;
import com.castgroup.desafio.utils.DocumentoUtil;
import com.castgroup.desafio.utils.DocumentoJson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentoControleTest {

	@Autowired
	public Repositorio repositorio;

	@Autowired
	public DocumentoControle docControle;

	private DocumentoJson json;

	private static String DOCUMENTO = "YWJjZGY=";

	@Before
	public void init() {
		this.repositorio.deleteAll();
	}

	@DisplayName("POST documento esquerdo sucesso")
	@Test
	public void documentoEsquerdoSucessoTest() {
		json = new DocumentoJson(DOCUMENTO);
		String resultado = docControle.documentoEsquerdo(1l, json);
		Assert.assertThat(resultado, Matchers.is(DocumentoUtil.respostaJson(Mensagens.DOC_ESQUERDO_SALVO_SUCESSO)));
	}

	@DisplayName("POST documento esquerdo falha")
	@Test
	public void documentoEsquerdoFalhaTest() {
		json = new DocumentoJson("");
		String resultado = docControle.documentoEsquerdo(1l, json);
		Assert.assertThat(resultado, Matchers.is(DocumentoUtil.respostaJson(Mensagens.DOC_ESQUERDO_NAO_SALVO)));
	}

	@DisplayName("POST documento direito sucesso")
	@Test
	public void documentoDireitoSucessoTest() {
		json = new DocumentoJson(DOCUMENTO);
		String resultado = docControle.documentoDireito(1l, json);
		Assert.assertThat(resultado, Matchers.is(DocumentoUtil.respostaJson(Mensagens.DOC_DIREITO_SALVO_SUCESSO)));
	}

	@DisplayName("POST documento direito falha")
	@Test
	public void documentoDireitoFalhaTest() {
		json = new DocumentoJson("");
		String resultado = docControle.documentoDireito(1l, json);
		Assert.assertThat(resultado, Matchers.is(DocumentoUtil.respostaJson(Mensagens.DOC_DIREITO_NAO_SALVO)));
	}

	@DisplayName("GET documentos")
	@Test
	public void documentoDiferencaNaoEncontradoTest() {
		String resultado = docControle.documentoDiferenca(1l);
		Assert.assertThat(resultado, Matchers.is(DocumentoUtil.respostaJson(Mensagens.DOC_NAO_ENCONTRADO)));
	}

}
