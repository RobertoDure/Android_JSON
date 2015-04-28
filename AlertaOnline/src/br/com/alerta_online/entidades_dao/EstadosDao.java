package br.com.alerta_online.entidades_dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.alerta_online.entidades_estados_cidades.Estados;
import br.com.alerta_online.util.Conexao;

public class EstadosDao

{
	private static final String URL = ":8080/Alerta_Online/services/EstadosDao?wsdl";
	private static final String NAMESPACE = "http://estados_cidades.alerta_online.com.br";
	private static final String BUSCAR_TODOS = "buscarTodosEstados";
	
	Conexao conectar = new Conexao();

	public ArrayList <Estados> buscarTodosEstados()

	{
		ArrayList<Estados> lista = new ArrayList<Estados>();

		SoapObject buscarEstados = new SoapObject(NAMESPACE, BUSCAR_TODOS);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

		envelope.setOutputSoapObject(buscarEstados);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(conectar.getIp() + URL);

		try 

		{
			http.call("urn :" + BUSCAR_TODOS, envelope);

			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

			for (SoapObject soapObject : resposta)

			{
				Estados usr =new Estados();
				usr.setId_estado(Integer.parseInt(soapObject.getProperty("id_estado").toString()));
				usr.setNome_estado(soapObject.getPropertyAsString("nome_estado").toString());
				usr.setUf(soapObject.getPropertyAsString("uf").toString());
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


}
