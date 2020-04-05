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
import com.castgroup.desafio.utils.JSON2Doc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentoControleTest {

	@Autowired
	public Repositorio repositorio;

	@Autowired
	public DocumentoControle docControle;

	private JSON2Doc json;

	@Before
	public void init() {
		
		this.repositorio.deleteAll();
		json = new JSON2Doc("YWJjZGY=");
	}

	@DisplayName("POST Documento Esquerda")
	@Test
	public void esquerdaTest() {

		String resultado = docControle.esquerda(1l, json);
		Assert.assertThat(resultado, Matchers.is("{\"Resultado\":\"O documento esquerdo foi salvo com sucesso!\"}"));
	}

	@DisplayName("POST Documento Esquerda")
	@Test
	public void direitaTest() {
		
		String resultado = docControle.direita(1l, json);
		Assert.assertThat(resultado, Matchers.is("{\"Resultado\":\"O documento direito foi salvo com sucesso!\"}"));
	}

	@DisplayName("GET Documentos")
	@Test
	public void diferenca() {

		String resultado = docControle.diferenca(1l);
		Assert.assertThat(resultado, Matchers.is("{\"Resultado\":\"Nenhum dado encontrado!\"}"));
	}

}
