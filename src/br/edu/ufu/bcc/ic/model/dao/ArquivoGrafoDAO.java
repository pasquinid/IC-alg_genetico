package br.edu.ufu.bcc.ic.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import br.edu.ufu.bcc.ic.model.vo.Grafo;

public class ArquivoGrafoDAO implements GrafoDAO {
	private ConfiguracaoDAO configuracaoDAO = new ArquivoConfiguracaoDAO();
	private static String caminho = "dados/dados.txt";
	private Scanner scanner;
	
	
	@SuppressWarnings("finally")
	@Override
	public Grafo get() {
		int numeroVertices = Integer.parseInt(configuracaoDAO.get("numero.vertices"));
		Grafo grafo = new Grafo(numeroVertices);
		try {
			FileReader arquivo = new FileReader(caminho);
			scanner = new Scanner(arquivo);
			scanner.useDelimiter(",|\\n");
			for (int i = 0; scanner.hasNext(); i++)
				for (int j = 0; j < numeroVertices; j++)
					grafo.set(i, j, Double.parseDouble(scanner.next()));
		} catch (IOException e) {
			System.out.println("Impossível ler o arquivo de dados");
		} finally {
			return grafo;
		}	
	}

}
