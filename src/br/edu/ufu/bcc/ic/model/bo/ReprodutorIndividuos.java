package br.edu.ufu.bcc.ic.model.bo;

import java.util.Random;

import br.edu.ufu.bcc.ic.model.vo.Grafo;
import br.edu.ufu.bcc.ic.model.vo.Individuo;

public class ReprodutorIndividuos {
	private Grafo grafo;
	
	public ReprodutorIndividuos(Grafo grafo) {
		this.grafo = grafo;
	}
	
	private int[] montarCromossomo(int[] cromossomoPai1, int[] cromossomoPai2, int pontoCortePai1) {
		int[] cromossomo = new int[cromossomoPai1.length];
		
		int i = 0;
		while( i <= pontoCortePai1 ) {
			cromossomo[i] = cromossomoPai1[i];
			i++;
		}
		
		// Seleciona os proximos genes removendo as repetições
		for (int j = 0; j < cromossomoPai2.length; j++) {
			boolean repetido = false;
			for (int k = 0; k < i; k++) {
				if (cromossomo[k] == cromossomoPai2[j])
					repetido = true;
			}
			
			if (!repetido) {
				cromossomo[i++] = cromossomoPai2[j];
			}
		}
		return cromossomo;
		
	}
	
	private double calcularAptidao(int[] cromossomo) {
		double aptidao = 0.0;
		
		for (int i = 0; i < cromossomo.length-1; i++)
			aptidao+=grafo.get(cromossomo[i], cromossomo[i+1]);
		
		aptidao+=grafo.get(cromossomo[cromossomo.length-1], cromossomo[0]);
		
		return aptidao;
	}
	
	// Crossover OX
	public Individuo[] executar(Individuo pai1, Individuo pai2) {
		Individuo[] filhos = new Individuo[2];
		
		int[] cromossomoPai1 = pai1.getCromossomo();
		int[] cromossomoPai2 = pai2.getCromossomo();
		Random random = new Random();
		
		int pontoCorte;
		
		int[] cromossomo;
		double aptidao;
		
		
		// gera o filho 1
		pontoCorte = random.nextInt(cromossomoPai1.length);
		cromossomo = montarCromossomo(cromossomoPai1, cromossomoPai2, pontoCorte);
		aptidao = calcularAptidao(cromossomo);
		filhos[0] = new Individuo(cromossomo, aptidao);
		
		
		// gera o filho 2
		pontoCorte = random.nextInt(cromossomoPai2.length);
		cromossomo = montarCromossomo(cromossomoPai2, cromossomoPai1, pontoCorte);
		aptidao = calcularAptidao(cromossomo);
		filhos[1] = new Individuo(cromossomo, aptidao);

		return filhos;
	}

}
