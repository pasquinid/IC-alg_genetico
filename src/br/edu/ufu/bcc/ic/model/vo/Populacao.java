package br.edu.ufu.bcc.ic.model.vo;

import java.util.ArrayList;
import java.util.List;

public class Populacao {
	private List<Individuo> individuos = new ArrayList<Individuo>();
	
	public List<Individuo> getIndividuos() {
		return individuos;
	}

	public void setIndividuos(List<Individuo> individuos) {
		this.individuos = individuos;
	}
	
	public boolean contem(Individuo novo) {
		for (Individuo i : individuos)
			if (i.equals(novo))
				return true;
		return false;
	}
	
	public void adicionar(Individuo novo) {
		this.individuos.add(novo);
	}
	
	public int getTamanho() {
		return individuos.size();
	}
	
	public Individuo getIndividuo(int posicao) {
		return this.individuos.get(posicao);
	}
	
	public List<Individuos> getIndividuos(){
		return this.individuos;
	}
}
