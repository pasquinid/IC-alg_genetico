package br.edu.ufu.bcc.ic.view;

import java.util.Arrays;

import br.edu.ufu.bcc.ic.model.vo.Individuo;

public class IndividuoView {
	public void executar(Individuo individuo) {
		try {
			System.out.println("Cromossomo: " + Arrays.toString(individuo.getCromossomo()));
			System.out.println("Aptidão   : " + individuo.getAptidao() + "\n");
		} catch (NullPointerException e) {
			System.out.println("Cromossomo: null");
			System.out.println("Aptidão   : null");
		}
	}
}
