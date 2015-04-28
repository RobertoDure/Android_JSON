package br.com.alerta_online.entidades_estados_cidades;


public class Cidades 

{
	private int id_cidade, estado_cidade;
	private String nome_cidade;


	public Cidades ()

	{

	}


	public Cidades(int id_cidade, String nome_cidade, int estado_cidade)

	{
		super();
		this.id_cidade = id_cidade;
		this.nome_cidade = nome_cidade;
		this.estado_cidade = estado_cidade;
	}


	public int getId_cidade() {
		return id_cidade;
	}


	public void setId_cidade(int id_cidade) {
		this.id_cidade = id_cidade;
	}


	public String getNome_cidade() {
		return nome_cidade;
	}


	public void setNome_cidade(String nome_cidade) {
		this.nome_cidade = nome_cidade;
	}


	public int getEstado_cidade() {
		return estado_cidade;
	}


	public void setEstado_cidade(int estado_cidade) {
		this.estado_cidade = estado_cidade;
	}


	@Override
	public String toString() {
		return nome_cidade;
	}


}
