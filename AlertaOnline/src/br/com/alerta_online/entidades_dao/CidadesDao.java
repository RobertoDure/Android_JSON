package br.com.alerta_online.entidades_dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.alerta_online.entidades_estados_cidades.Cidades;
import br.com.alerta_online.util.Conexao;

public class CidadesDao 

{
	private static final String URL = ":8080/Alerta_Online/services/CidadesDao?wsdl";
	private static final String NAMESPACE = "http://estados_cidades.alerta_online.com.br";
	private static final String BUSCAR_TODAS = "buscarTodasCidades";


	public ArrayList <Cidades> buscarTodasCidades(int id_city)

	{
		Conexao conectar = new Conexao();
		
		//Cidades city = null;
		
		ArrayList<Cidades> lista = new ArrayList<Cidades>();

		SoapObject buscarCidades = new SoapObject(NAMESPACE, BUSCAR_TODAS);
		
		buscarCidades.addProperty("estado_cidade", id_city);
		
		//SoapObject user = new SoapObject(NAMESPACE, "usuario");

		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

		envelope.setOutputSoapObject(buscarCidades);
		
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(conectar.getIp() + URL);

		try 

		{
			http.call("urn :" + BUSCAR_TODAS, envelope);

			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

			for (SoapObject soapObject : resposta)

			{
				Cidades usr = new Cidades();
				usr.setId_cidade(Integer.parseInt(soapObject.getProperty("id_cidade").toString()));
				usr.setNome_cidade(soapObject.getPropertyAsString("nome_cidade").toString());
				usr.setEstado_cidade(Integer.parseInt(soapObject.getPropertyAsString("estado_cidade").toString()));
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
