package br.edu.ufu.bcc.ic.model.dao;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public class ArquivoConfiguracaoDAO implements ConfiguracaoDAO {
	private String caminho = "conf/ag.conf";
	private Properties propriedades;
	
	
	public ArquivoConfiguracaoDAO() {
		this.propriedades = new Properties();
		try {
			FileInputStream arquivo = new FileInputStream(caminho);
			this.propriedades.load(arquivo);
		} catch (IOException e) {
			this.propriedades.setProperty("distancia.maxima.percorrida", "250");
			this.propriedades.setProperty("numero.vertices", "42");
			this.propriedades.setProperty("tamanho.populacao.inicial", "100");
			this.propriedades.setProperty("numero.geracoes", "1000");
			this.propriedades.setProperty("porcentagem.mutacao", "0.5");
			salvar();
		}
	}
	
	private void salvar(){
		try {
			FileOutputStream arquivo = new FileOutputStream(caminho);
			this.propriedades.store(arquivo,"");
		} catch (IOException e){
			System.out.println ("Arquivo e/ou diretório de configuração não encontrado");
		}
	}
	@Override
	public String get(String chave) {
		return this.propriedades.getProperty(chave);
	}

	@Override
	public void atualiza(String chave, String valor) {
		this.propriedades.setProperty(chave, valor);
		salvar();
	}

	@Override
	public void remover(String chave) {
		this.propriedades.remove(chave);
		salvar();
	}

	@Override
	public void inserir(String chave, String valor) {
		this.propriedades.setProperty(chave, valor);
		salvar();
	}

}
