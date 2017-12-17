package br.edu.ufu.bcc.ic.view;

import br.edu.ufu.bcc.ic.model.vo.Grafo;

public class GrafoView {
	public void executar(Grafo grafo) {
		try {
			int tamanho = grafo.getTamanho();
			for (int i = 0; i < tamanho; i++) {
				int j;
				for (j = 0; j < tamanho-1; j++)
					System.out.print(grafo.get(i, j) + ", ");
				System.out.println(grafo.get(i, j));
			}
		} catch (NullPointerException e) {
			System.out.println("Grafo Vazio");
		}
	}
}
