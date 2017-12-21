package br.edu.ufu.bcc.ic.model.vo;

import br.edu.ufu.bcc.ic.model.dao.ArquivoConfiguracaoDAO;
import br.edu.ufu.bcc.ic.model.dao.ConfiguracaoDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Populacao {
	private List<Individuo> individuos = new ArrayList<Individuo>();
        private ConfiguracaoDAO configuracaoDAO = new ArquivoConfiguracaoDAO();
	
	public List<Individuo> getIndividuos() {
		return individuos;
	}
        
        public void BalanceadorPopulacao(){
            int tam = Integer.parseInt(configuracaoDAO.get("tamanho.populacao.inicial"));
            List<Individuo> ordenaTodos = new ArrayList<>();
            ordenaTodos = this.getIndividuos();
            int tamAtual = ordenaTodos.size();
            Collections.sort(ordenaTodos);
            while(tamAtual != tam){
                this.remover(ordenaTodos.get(tamAtual - 1));
                tamAtual = ordenaTodos.size();
            }
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
