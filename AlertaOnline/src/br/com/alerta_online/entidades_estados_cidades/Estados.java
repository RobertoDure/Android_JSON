package br.com.alerta_online.entidades_estados_cidades;

public class Estados

{
	private int id_estado;
	private String nome_estado, uf;
	
		
	public Estados ()
	
	{
		
	}
	
	
	public Estados(int id_estado, String nome_estado, String uf)
	
	{
		super();
		this.id_estado = id_estado;
		this.nome_estado = nome_estado;
		this.uf = uf;
	}


	public int getId_estado() {
		return id_estado;
	}


	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}


	public String getNome_estado() {
		return nome_estado;
	}


	public void setNome_estado(String nome_estado) {
		this.nome_estado = nome_estado;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}


	@Override
	public String toString() {
		return nome_estado;
	}
	
}

