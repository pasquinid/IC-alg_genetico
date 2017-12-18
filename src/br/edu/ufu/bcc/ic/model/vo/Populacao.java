package br.edu.ufu.bcc.ic.model.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Populacao {
	private List<Individuo> individuos = new ArrayList<Individuo>();
	
	public List<Individuo> getIndividuos() {
		return individuos;
	}
        
        public void BalanceadorPopulacao(){
            List<Individuo> ordenaTodos = new ArrayList<>();
            ordenaTodos = this.getIndividuos();
            Collections.sort(ordenaTodos);
            if(this.individuos.size() == 352)
                this.remover(ordenaTodos.get(351));
            if(this.individuos.size() == 351)
                this.remover(ordenaTodos.get(350));
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
        
        public void remover(Individuo individuo){
            this.individuos.remove(individuo);
        }
	
	public int getTamanho() {
		return individuos.size();
	}
	
	public Individuo getIndividuo(int posicao) {
		return this.individuos.get(posicao);
	}
}
