package br.edu.ufu.bcc.ic.model.bo;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.edu.ufu.bcc.ic.model.vo.Individuo;
import br.edu.ufu.bcc.ic.model.vo.Populacao;

public class SelecionadorPais {
	private Populacao populacao;
	
	public SelecionadorPais(Populacao populacao) {
		this.populacao = populacao;
	}
	
	private double calcularSomaAptidoes() {
		List<Individuo> individuos = populacao.getIndividuos();
		double soma = 0.0;
		for (Individuo i : individuos)
			soma+=i.getAptidao();
		return soma;
	}
	
	// Metodo da roleta
	public Individuo roletaRussa() {
		List<Individuo> individuos = populacao.getIndividuos();
		double total = calcularSomaAptidoes();
		Random random = new Random();
		double rand = random.nextDouble() * total;
		double totalParcial;
		int i;
		
		Collections.sort(individuos);
		Collections.reverse(individuos);
		
		totalParcial = 0.0;
		i = 0;
		while (totalParcial < rand) {
			totalParcial = totalParcial + individuos.get(i).getAptidao();
			i++;
		}
		
		return individuos.get(i);
		
	}
	
	public Individuo[] executar() {
		Individuo[] pais = new Individuo[2];
		pais[0] = roletaRussa();
		pais[1] = roletaRussa();
		return pais;	
	}
}
