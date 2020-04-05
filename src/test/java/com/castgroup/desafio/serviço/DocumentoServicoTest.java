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
import com.castgroup.desafio.utils.PosicaoDoc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentoServicoTest {

	@InjectMocks
	private DocumentoServico docServico;

	@Mock
	public Repositorio repositorio;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Salvar Documento Esquerda")
	@Test
	public void salvarDocEsquerdaTest() {
		Mockito.doReturn(null).when(repositorio).findById(Mockito.eq(1));
		Mockito.doAnswer(returnsFirstArg()).when(repositorio).save(Mockito.any(Documento.class));
		Documento documentoEsquerda = docServico.salvar(1, "YWJjZGY=", PosicaoDoc.ESQUERDA.toString());

		Assert.assertThat(documentoEsquerda.getId(), Matchers.is(1));
		Assert.assertThat(documentoEsquerda.getEsquerda(), Matchers.is("abcdf"));
		Assert.assertTrue(StringUtils.isNullOrEmpty(documentoEsquerda.getDireita()));
	}

	@DisplayName("Salvar Doc Direita")
	@Test
	public void salvardocDireitaTest() {
		Mockito.doReturn(null).when(repositorio).findById(Mockito.eq(1));
		Mockito.doAnswer(returnsFirstArg()).when(repositorio).save(Mockito.any(Documento.class));
		Documento documentoDireita = docServico.salvar(1, "YWJjZGY=", PosicaoDoc.DIREITA.toString());

		Assert.assertThat(documentoDireita.getId(), Matchers.is(1));
		Assert.assertThat(documentoDireita.getDireita(), Matchers.is("abcdf"));
		Assert.assertTrue(StringUtils.isNullOrEmpty(documentoDireita.getEsquerda()));
	}

	@DisplayName("Salvar Doc Sem Posição")
	@Test
	public void salvarDocLiberalTest() {
		Mockito.doReturn(null).when(repositorio).findById(Mockito.eq(1));
		Mockito.doAnswer(returnsFirstArg()).when(repositorio).save(Mockito.any(Documento.class));
		Documento documentoSemPosicao = docServico.salvar(1, "YWJjZGY=", null);

		Assert.assertThat(documentoSemPosicao.getId(), Matchers.is(1));
		Assert.assertTrue(StringUtils.isNullOrEmpty(documentoSemPosicao.getDireita()));
		Assert.assertTrue(StringUtils.isNullOrEmpty(documentoSemPosicao.getEsquerda()));
	}

	@DisplayName("Valida Documento Igual")
	@Test
	public void validarDocumentoIgualTest() {
		Documento documentoIgual = new Documento(1, "YWJjZGY=", "YWJjZGY=");
		Mockito.doReturn(documentoIgual).when(repositorio).findById(Mockito.eq(1));

		String resultado = docServico.validaDocumento(1);

		Assert.assertThat(resultado, Matchers.is("Os documentos são iguais!"));
	}

	@DisplayName("Valida Documento Diferente")
	@Test
	public void validarDocumentoDiferenteTest() {
		Documento documentoDiferente = new Documento(1, "YWJjZGY=", "YWJjZGU=");
		Mockito.doReturn(documentoDiferente).when(repositorio).findById(Mockito.eq(1));

		String resultado = docServico.validaDocumento(1);

		Assert.assertThat(resultado,
				Matchers.is("Os documentos têm o mesmo tamanho, porém divergem a partir da posição: 6"));
	}

	@DisplayName("Valida Documento Tamanho Diferente")
	@Test
	public void validarDocumentoTamDiferenteTest() {
		Documento documentoTamDiferente = new Documento(1, "YWJjZGY=", "YWJjZGVmZw==");
		Mockito.doReturn(documentoTamDiferente).when(repositorio).findById(Mockito.eq(1));

		String resultado = docServico.validaDocumento(1);

		Assert.assertThat(resultado, Matchers.is("Os documentos não tem o mesmo tamanho!"));
	}

}
