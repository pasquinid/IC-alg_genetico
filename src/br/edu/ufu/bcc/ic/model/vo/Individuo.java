package br.edu.ufu.bcc.ic.model.vo;

import java.util.Arrays;

public class Individuo implements Comparable<Individuo> {
	private int[] cromossomo;
	private double aptidao;
	
	public Individuo(int[] cromossomo, double aptidao) {
		this.cromossomo = cromossomo;
		this.aptidao = aptidao;
	}

	public int[] getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(int[] cromossomo) {
		this.cromossomo = cromossomo;
	}

	public double getAptidao() {
		return aptidao;
	}

	public void setAptidao(double aptidao) {
		this.aptidao = aptidao;
	}
	
	public Individuo[] crossover(Individuo individuo) {
		return null;
	}
	
	public void set(int locus, int alelo) {
		if ( locus < 0 || locus >= cromossomo.length )
			throw new IllegalArgumentException("Locus fora dos limites");
		this.cromossomo[locus] = alelo;
	}

	public int compareTo(Individuo outro) {
		if ( this.aptidao  < outro.aptidao )
			return -1;
		if ( this.aptidao == outro.aptidao )
			return 0;
		return 1;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Rota   : " + Arrays.toString(this.getCromossomo()) + "\n");
		stringBuilder.append("Aptidao: " + this.getAptidao());
		return stringBuilder.toString();
	}
	
	public boolean equals(Individuo outro) {
		if (this.cromossomo.length != outro.cromossomo.length)
			return false;
		
		for (int i = 0; i < this.cromossomo.length; i++)
			if (this.cromossomo[i] != outro.cromossomo[i])
				return false;
		
		return true;
	}
}
