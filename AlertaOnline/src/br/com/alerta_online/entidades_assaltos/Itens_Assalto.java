package br.com.alerta_online.entidades_assaltos;


public class Itens_Assalto 

{
	private int id_itens, id_assalto;
	private String descricao;
	
	
	public Itens_Assalto ()
	
	{
		
	}
	
	public Itens_Assalto(int id_itens, int id_assalto, String descricao) {
		super();
		this.id_itens = id_itens;
		this.id_assalto = id_assalto;
		this.descricao = descricao;
	}
	
	public int getId_itens() {
		return id_itens;
	}
	public void setId_itens(int id_itens) {
		this.id_itens = id_itens;
	}
	public int getId_assalto() {
		return id_assalto;
	}
	public void setId_assalto(int id_assalto) {
		this.id_assalto = id_assalto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Itens_Assalto [id_itens=" + id_itens + ", id_assalto="
				+ id_assalto + ", descricao=" + descricao + "]";
	}
	
}
