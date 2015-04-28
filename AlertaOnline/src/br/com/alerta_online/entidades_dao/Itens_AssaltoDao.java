package br.com.alerta_online.entidades_dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.alerta_online.entidades_assaltos.Itens_Assalto;
import br.com.alerta_online.util.Conexao;

public class Itens_AssaltoDao

{
	
	private static final String URL = ":8080/Alerta_Online/services/Itens_AssaltoDao?wsdl";
	private static final String NAMESPACE = "http://itens_assalto.alerta_online.com.br";
	private static final String INSERIR = "inserirItensAssalto";
	
	Conexao conectar = new Conexao();
	
	
	public boolean inserirItensAssalto(Itens_Assalto itens_Assalto)

	{
		
		
		SoapObject inserirItensAssalto = new SoapObject(NAMESPACE, INSERIR);
		SoapObject user = new SoapObject(NAMESPACE, "itensAssalto");
		user.addProperty("id_itens", itens_Assalto.getId_itens());
		user.addProperty("id_assalto", itens_Assalto.getId_assalto());
		user.addProperty("descricao", itens_Assalto.getDescricao());
		
		inserirItensAssalto.addSoapObject(user);


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirItensAssalto);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(conectar.getIp() + URL);
		
		try
		
		{
			http.call("urn :" + INSERIR, envelope);

			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();

			return Boolean.parseBoolean(resposta.toString());
		}
		
		catch (Exception e)
		
		{
			e.printStackTrace();
			return false;
		}


	}
}
