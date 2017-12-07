package br.edu.ufu.bcc.ic.model.bo;

import br.edu.ufu.bcc.ic.model.dao.ConfiguracaoDAO;

import java.util.Random;

import br.edu.ufu.bcc.ic.model.dao.ArquivoConfiguracaoDAO;

import br.edu.ufu.bcc.ic.model.vo.Grafo;
import br.edu.ufu.bcc.ic.model.vo.Individuo;
import br.edu.ufu.bcc.ic.model.vo.Populacao;

public class GeradorPopulacaoInicial {
	private ConfiguracaoDAO configuracaoDAO = new ArquivoConfiguracaoDAO();
	private Grafo grafo;
	
	public GeradorPopulacaoInicial(Grafo grafo) {
		this.grafo = grafo;
	}

	public double calcularAptidao(int[] cromossomo) {
		double aptidao = 0.0;
		
		for (int i = 0; i < cromossomo.length-1; i++)
			aptidao+=grafo.get(cromossomo[i], cromossomo[i+1]);
		
		aptidao+=grafo.get(cromossomo[cromossomo.length-1], cromossomo[0]);
		
		return aptidao;
	}
	
	private boolean todosVerticesVisitados(boolean[] verticesVisitados) {
		for (boolean vertice : verticesVisitados)
			if (vertice == false)
				return false;
		return true;
	}
	
	public int[] gerarCromossomo() {
		int[] cromossomo = new int[grafo.getTamanho()];
		boolean[] verticesVisitados = new boolean[grafo.getTamanho()];
		Random random = new Random();
		
		int i = 0;
		while(!todosVerticesVisitados(verticesVisitados)) {
			int vertice = random.nextInt(grafo.getTamanho());
			if (verticesVisitados[vertice] ==  false) {
				verticesVisitados[vertice] = true;
				cromossomo[i++] = vertice;
			}
		}
	
		return cromossomo;
	}
	
	public Populacao executar() {
		int tamanhoPoupulacaoInicial = Integer.parseInt(configuracaoDAO.get("tamanho.populacao.inicial"));
		Populacao populacaoInicial = new Populacao();
		
		while (populacaoInicial.getTamanho() < tamanhoPoupulacaoInicial ) {
			
			// Gera o novo individuo
			int[] cromossomo = gerarCromossomo();
			double aptidao = calcularAptidao(cromossomo);
			Individuo individuo = new Individuo(cromossomo, aptidao);
			
			// Verifica se ja existe na populacao
			// Se não adiciona
			if (!populacaoInicial.contem(individuo))
				populacaoInicial.adicionar(individuo);
		}
		
		return populacaoInicial;
	}
}
