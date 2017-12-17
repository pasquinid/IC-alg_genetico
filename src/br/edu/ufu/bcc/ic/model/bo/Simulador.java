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
	
        public void PopulacaoMelhorAptidão(Populacao populacao){
        List<Individuo> ordenaTodos = new ArrayList<>();
        ordenaTodos = populacao.getIndividuos();
        Collections.sort(ordenaTodos);
        /*int count = 1;
        for (Individuo cromossomo: ordenaTodos){
            if(cromossomo.getAptidao() > 0.0)
               System.out.println(count + ": "+ cromossomo.toString());
               count++;
        }*/
            populacao.remover(ordenaTodos.get(101));
            populacao.remover(ordenaTodos.get(100));
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
		
		int i = 0;
		while( i < numeroGeracoes ) {
			
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
                        
                        this.PopulacaoMelhorAptidão(populacao);
                        populacaoView.executar(populacao);
		}
	}
}
