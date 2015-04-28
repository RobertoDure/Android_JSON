package br.com.alerta_online.entidades_usuarios;

import java.io.Serializable;


public class Usuario implements Serializable

{
	private int id;
	private String email;

	public Usuario ()

	{

	}

	public Usuario(int id, String email)

	{
		this.id = id;
		this.email = email;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email	+ "]";
	}
}
