package com.castgroup.desafio.serviço;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

import org.h2.util.StringUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.castgroup.desafio.modelo.Documento;
import com.castgroup.desafio.repositorio.Repositorio;
import com.castgroup.desafio.utils.Mensagens;
import com.castgroup.desafio.utils.DocumentoPosicao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentoServicoTest {

	@InjectMocks
	private DocumentoServico docServico;

	@Mock
	public Repositorio repositorio;

	private static String DOCUMENTO = "YWJjZGY=";
	private static String DOCUMENTO_DIFERENTE = "YWJjZGU=";
	private static String DOCUMENTO_TAM_DIFERENTE = "YWJjZGVmZw==";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Salvar documento esquerdo sucesso")
	@Test
	public void salvarDocumentoEsquerdoSucessoTest() {
		Mockito.doReturn(null).when(repositorio).findById(Mockito.eq(1l));
		Mockito.doAnswer(returnsFirstArg()).when(repositorio).save(Mockito.any(Documento.class));
		Documento documentoEsquerdo = docServico.salvar(1l, DOCUMENTO, DocumentoPosicao.ESQUERDA.toString());

		Assert.assertThat(documentoEsquerdo.getId(), Matchers.is(1l));
		Assert.assertThat(documentoEsquerdo.getEsquerda(), Matchers.is(DOCUMENTO));
		Assert.assertTrue(StringUtils.isNullOrEmpty(documentoEsquerdo.getDireita()));
	}

	@DisplayName("Salvar documento esquerdo falha")
	@Test
	public void salvarDocumentoEsquerdoFalhaTest() {
		Mockito.doReturn(null).when(repositorio).findById(Mockito.eq(1l));
		Mockito.doAnswer(returnsFirstArg()).when(repositorio).save(Mockito.any(Documento.class));
		Documento documentoEsquerdo = docServico.salvar(1l, "", DocumentoPosicao.ESQUERDA.toString());

		Assert.assertNull(documentoEsquerdo);
	}

	@DisplayName("Salvar documento direito sucesso")
	@Test
	public void salvardocumentoDireitoSucessoTest() {
		Mockito.doReturn(null).when(repositorio).findById(Mockito.eq(1l));
		Mockito.doAnswer(returnsFirstArg()).when(repositorio).save(Mockito.any(Documento.class));
		Documento documentoDireito = docServico.salvar(1l, "YWJjZGY=", DocumentoPosicao.DIREITA.toString());

		Assert.assertThat(documentoDireito.getId(), Matchers.is(1l));
		Assert.assertThat(documentoDireito.getDireita(), Matchers.is("YWJjZGY="));
		Assert.assertTrue(StringUtils.isNullOrEmpty(documentoDireito.getEsquerda()));
	}

	@DisplayName("Salvar documento direito falha")
	@Test
	public void salvarDocumentoDireitoFalhaTest() {
		Mockito.doReturn(null).when(repositorio).findById(Mockito.eq(1l));
		Mockito.doAnswer(returnsFirstArg()).when(repositorio).save(Mockito.any(Documento.class));
		Documento documentoDireito = docServico.salvar(1l, "", DocumentoPosicao.DIREITA.toString());

		Assert.assertNull(documentoDireito);
	}

	@DisplayName("Salvar documento sem posição")
	@Test
	public void salvarDocLiberalTest() {
		Mockito.doReturn(null).when(repositorio).findById(Mockito.eq(1l));
		Mockito.doAnswer(returnsFirstArg()).when(repositorio).save(Mockito.any(Documento.class));
		Documento documentoSemPosicao = docServico.salvar(1l, DOCUMENTO, null);

		Assert.assertThat(documentoSemPosicao.getId(), Matchers.is(1l));
		Assert.assertTrue(StringUtils.isNullOrEmpty(documentoSemPosicao.getDireita()));
		Assert.assertTrue(StringUtils.isNullOrEmpty(documentoSemPosicao.getEsquerda()));
	}
	
	@DisplayName("Salvar documento vazio")
    @Test
    public void salvarDocumentoVazioTest() {
        Mockito.doReturn(null).when(repositorio).findById(Mockito.eq(1l));
        Mockito.doAnswer(returnsFirstArg()).when(repositorio).save(Mockito.any(Documento.class));
        Documento documentoDireito = docServico.salvar(0, "", "");
        Assert.assertNull(documentoDireito);
    }

	@DisplayName("Valida documentos iguais")
	@Test
	public void validarDocumentoIgualTest() {
		Documento documentoIgual = new Documento(1l, DOCUMENTO, DOCUMENTO);
		Mockito.doReturn(documentoIgual).when(repositorio).findById(Mockito.eq(1l));
		String resultado = docServico.validarDocumento(1l);

		Assert.assertThat(resultado, Matchers.is("Documentos " + 1l + " idênticos"));
	}

	@DisplayName("Valida documentos diferentes")
	@Test
	public void validarDocumentoDiferenteTest() {
		Documento documentoDiferente = new Documento(1l, DOCUMENTO, DOCUMENTO_DIFERENTE);
		Mockito.doReturn(documentoDiferente).when(repositorio).findById(Mockito.eq(1l));
		String resultado = docServico.validarDocumento(1l);

		Assert.assertThat(resultado, Matchers.is(Mensagens.DOC_TAM_DIVERGENTE + " 4"));
	}

	@DisplayName("Valida documentos com tamanhos diferentes")
	@Test
	public void validarDocumentoTamDiferenteTest() {
		Documento documentoTamDiferente = new Documento(1l, DOCUMENTO, DOCUMENTO_TAM_DIFERENTE);
		Mockito.doReturn(documentoTamDiferente).when(repositorio).findById(Mockito.eq(1l));
		String resultado = docServico.validarDocumento(1l);

		Assert.assertThat(resultado, Matchers.is("Documentos " + 1l + " com tamanhos diferentes"));
	}

}
