package br.com.alerta_online.entidades_dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.alerta_online.entidades_usuarios.Usuario;
import br.com.alerta_online.util.Conexao;

public class UsuarioDAO 

{

	private static final String URL = ":8080/Alerta_Online/services/UsuarioDao?wsdl";
	private static final String NAMESPACE = "http://usuario.alerta_online.com.br";
	private static final String INSERIR = "inserirUsuario";	
	private static final String BUSCAR_TODOS = "buscarTodosUsuarios";
	private static final String BUSCAR_POR_EMAIL = "buscarUsuarioPorEmail";

	Conexao conectar = new Conexao();

	public int inserirUsuario(Usuario usuario)

	{
		SoapObject inserirUsuario = new SoapObject(NAMESPACE, INSERIR);
		SoapObject user = new SoapObject(NAMESPACE, "usuario");
		user.addProperty("id", usuario.getId());
		user.addProperty("email", usuario.getEmail());

		inserirUsuario.addSoapObject(user);


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirUsuario);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(conectar.getIp() + URL);

		try

		{
			http.call("urn :" + INSERIR, envelope);

			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();

			return Integer.parseInt(resposta.toString());
		}

		catch (Exception e)

		{
			e.printStackTrace();
			return 0;
		}


	}


	public ArrayList <Usuario> buscarTodosUsuarios()

	{
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		SoapObject buscarUsuario = new SoapObject(NAMESPACE, BUSCAR_TODOS);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

		envelope.setOutputSoapObject(buscarUsuario);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(conectar.getIp() + URL);

		try 

		{
			http.call("urn :" + BUSCAR_TODOS, envelope);

			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

			for (SoapObject soapObject : resposta)

			{
				Usuario usr =new Usuario();
				usr.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
				usr.setEmail(soapObject.getPropertyAsString("email").toString());
				lista.add(usr);
			}

		}

		catch (Exception e)

		{
			e.printStackTrace();
			return null;
		}

		return lista;

	}


	public Usuario buscarUsuarioPorEmail (String email)

	{
		Usuario usr = null;

		SoapObject buscarUsuariosPorEmail = new SoapObject(NAMESPACE, BUSCAR_POR_EMAIL);

		buscarUsuariosPorEmail.addProperty("email", email);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

		envelope.setOutputSoapObject(buscarUsuariosPorEmail);

		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(conectar.getIp() + URL);

		try 

		{
			http.call("urn :" + BUSCAR_POR_EMAIL, envelope);

			SoapObject resposta = (SoapObject) envelope.getResponse();


			usr = new Usuario();

			usr.setId(Integer.parseInt(resposta.getProperty("id").toString()));

			usr.setEmail(resposta.getPropertyAsString("email").toString());

		}

		catch (Exception e)

		{
			e.printStackTrace();
			return null;
		}

		return usr;

	}

}
