package br.edu.ufu.bcc.ic.model.dao;

public interface ConfiguracaoDAO {
	public String get(String chave);
	public void atualiza(String chave, String valor);
	public void remover(String chave);
	public void inserir(String chave, String valor);

}
