package br.edu.ufu.bcc.ic.model.bo;

import java.util.Random;

import br.edu.ufu.bcc.ic.model.vo.Grafo;
import br.edu.ufu.bcc.ic.model.vo.Individuo;

public class MutadorIndividuo {
	private Grafo grafo;
	
	public MutadorIndividuo(Grafo grafo) {
		this.grafo = grafo;
	}
	
	private double calcularAptidao(int[] cromossomo) {
		double aptidao = 0.0;
		
		for (int i = 0; i < cromossomo.length-1; i++)
			aptidao+=grafo.get(cromossomo[i], cromossomo[i+1]);
		
		aptidao+=grafo.get(cromossomo[cromossomo.length-1], cromossomo[0]);
		
		return aptidao;
	}
	
	// Troca dois genes de posicao
	
	public void executarTrocadoisGenes(Individuo individuo) {
		int[] cromossomo = individuo.getCromossomo();
		Random random = new Random();
		
		// Geracao de posicoes aleatorias para troca de genes
		int posicao1 = random.nextInt(cromossomo.length);
		int posicao2 = random.nextInt(cromossomo.length);
		
		// Troca de dos genes
		int aux;
		aux = cromossomo[posicao1];
		cromossomo[posicao1] = cromossomo[posicao2];
		cromossomo[posicao2] = aux;
		double aptidao = calcularAptidao(cromossomo);

		individuo.setCromossomo(cromossomo);
		individuo.setAptidao(aptidao);
	}
	
	// Inveresão
	public void executarInversaodeGenes(Individuo individuo) {
		int[] cromossomo = individuo.getCromossomo();
		Random random = new Random();
		
		// Geracao de posicoes aleatorias para troca de genes
		int posicao1 = random.nextInt(cromossomo.length);
		int posicao2 = random.nextInt(cromossomo.length);
	
		
		int tamanhoInversao = 0;
		int inicio;
		int fim;

		if(posicao1 < posicao2) {
			tamanhoInversao = posicao2 - posicao1;
			inicio = posicao1;
			fim = posicao2;
		} else {
			tamanhoInversao = posicao1 - posicao2;
			inicio = posicao2;
			fim = posicao1;
		}
		
		if(tamanhoInversao > 0) {
			// Inversão dos genes
			int[] cromossomoMutante = new int[cromossomo.length];
			
			int j = fim;			
			for(int i = 0; i < cromossomo.length; i++) {
				cromossomoMutante[i] = cromossomo[i];
				if(i >= inicio && i <= fim) {
					cromossomoMutante[i] = cromossomo[j];
					j--;
				}
			}
			
			double aptidao = calcularAptidao(cromossomoMutante);
	
			individuo.setCromossomo(cromossomoMutante);
			individuo.setAptidao(aptidao);
		}
	}
}
