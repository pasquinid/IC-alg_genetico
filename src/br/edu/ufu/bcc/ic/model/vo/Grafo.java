package br.edu.ufu.bcc.ic.model.vo;

public class Grafo {
	private double[][] matriz;
	
	public Grafo(int tamanho) {
		if (tamanho <= 0)
			tamanho = 1;
		this.matriz = new double[tamanho][];
		for (int i = 0 ; i < matriz.length; i++)
			this.matriz[i] = new double[tamanho];
	}
	
	private void verificar(int dimensao) {
		if (dimensao < 0 || dimensao >= matriz.length )
			throw new IllegalArgumentException("Dimensão fora dos limites do grafo");
	}
	
	public double get(int linha, int coluna) {
		verificar(linha);
		verificar(coluna);
		return this.matriz[linha][coluna];
	}
	
	public void set(int linha, int coluna, double valor) {
		verificar(linha);
		verificar(coluna);
		this.matriz[linha][coluna] = valor;
	}
	
	public int getTamanho() {
		return this.matriz.length;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < matriz.length; i++) {
			int j = 0;
			while ( j < matriz[i].length - 1) {
				stringBuilder.append(matriz[i][j] + " ");
				j++;
			}
			stringBuilder.append(matriz[i][j] + "\n");
		}
		return stringBuilder.toString();
	}
}
