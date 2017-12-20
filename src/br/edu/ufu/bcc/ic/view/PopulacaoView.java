package br.edu.ufu.bcc.ic.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ufu.bcc.ic.model.vo.Individuo;
import br.edu.ufu.bcc.ic.model.vo.Populacao;

public class PopulacaoView {
	public List<Double> executar(Populacao populacao) {
		List<Double> list = new ArrayList<>();
		try {
			List<Individuo> individuos = populacao.getIndividuos();
			IndividuoView individuoView = new IndividuoView();
			Collections.sort(individuos);
			for (Individuo i : individuos) {
				individuoView.executar(i);
				list.add(i.getAptidao());
			}
		} catch (NullPointerException e) {
			System.out.println("População: null");
		}
		
		return list;
	}
}
