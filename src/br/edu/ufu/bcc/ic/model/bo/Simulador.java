package br.edu.ufu.bcc.ic.model.bo;

import java.util.Random;

import br.edu.ufu.bcc.ic.model.dao.ArquivoConfiguracaoDAO;
import br.edu.ufu.bcc.ic.model.dao.ArquivoGrafoDAO;
import br.edu.ufu.bcc.ic.model.dao.ConfiguracaoDAO;
import br.edu.ufu.bcc.ic.model.dao.GrafoDAO;
import br.edu.ufu.bcc.ic.model.vo.Grafo;
import br.edu.ufu.bcc.ic.model.vo.Individuo;
import br.edu.ufu.bcc.ic.model.vo.Populacao;
import br.edu.ufu.bcc.ic.view.PopulacaoView;
import br.edu.ufu.bcc.ic.view.IndividuoView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulador {
	private ConfiguracaoDAO configuracaoDAO = new ArquivoConfiguracaoDAO();
	private GrafoDAO grafoDAO = new ArquivoGrafoDAO();
	
	
	private Grafo grafo;
	private GeradorPopulacaoInicial geradorPopulacaoInicial;
	private MutadorIndividuo mutadorIndividuo;
	private ReprodutorIndividuos reprodutorIndividuos;
	private SelecionadorPais selecionadorPais;
	private PopulacaoView populacaoView = new PopulacaoView();
        private IndividuoView individuoView = new IndividuoView();
        
        public Individuo melhorIndividuo(Populacao populacao){
            List<Individuo> ordenaTodos = new ArrayList<>();
            ordenaTodos = populacao.getIndividuos();
            Collections.sort(ordenaTodos);
            return ordenaTodos.get(0);     
        }
        
	public Simulador() {
		this.grafo = grafoDAO.get();
		this.geradorPopulacaoInicial = new GeradorPopulacaoInicial(grafo);
		this.mutadorIndividuo = new MutadorIndividuo(grafo);
		this.reprodutorIndividuos = new ReprodutorIndividuos(grafo);	
	}
	
	public void executar() {
		Populacao populacao = geradorPopulacaoInicial.executar();
		int numeroGeracoes = Integer.parseInt(configuracaoDAO.get("numero.geracoes"));
		double porcentagemMutacao = Double.parseDouble(configuracaoDAO.get("porcentagem.mutacao"));
                this.selecionadorPais = new SelecionadorPais(populacao);
		Random random = new Random();
		
		int geracao = 0;
		while( geracao < numeroGeracoes ) {
			
			Individuo[] pais = this.selecionadorPais.executar();
			Individuo[] filhos = this.reprodutorIndividuos.executar(pais[0], pais[1]);
			double probabilidadeMutacao = random.nextDouble();
			
			if ( probabilidadeMutacao >= porcentagemMutacao ) {
				this.mutadorIndividuo.executar(filhos[0]);
				this.mutadorIndividuo.executar(filhos[1]);
			}
                        
			if(!populacao.contem(filhos[0]))
                            populacao.adicionar(filhos[0]);
                        if(!populacao.contem(filhos[1]))
                            populacao.adicionar(filhos[1]);
                        
                        populacao.BalanceadorPopulacao();
                        geracao++;
		}
                System.out.println("============Populacao Final========================");
                populacaoView.executar(populacao);
                System.out.println("\n=============Melhor Cromossomo===================");
                Individuo melhorRota = this.melhorIndividuo(populacao);
                individuoView.executar(melhorRota);
	}
}
