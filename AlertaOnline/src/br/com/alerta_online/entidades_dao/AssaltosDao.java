package br.com.alerta_online.entidades_dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.alerta_online.entidades_assaltos.Assalto;
import br.com.alerta_online.util.Conexao;

public class AssaltosDao 

{
	
	private static final String URL = ":8080/Alerta_Online/services/AssaltoDao?wsdl";
	private static final String NAMESPACE = "http://assalto.alerta_online.com.br";
	private static final String INSERIR = "inserirAssalto";
	
	Conexao conectar = new Conexao();
	
	
	public int inserirAssalto(Assalto assalto)

	{		
		SoapObject inserirAssalto = new SoapObject(NAMESPACE, INSERIR);
		SoapObject user = new SoapObject(NAMESPACE, "assalto");
		user.addProperty("id_assalto", assalto.getId_assalto());
		user.addProperty("id_email", assalto.getId_email());
		user.addProperty("id_local_assalto", assalto.getId_local_assalto());
		user.addProperty("sexo_vitima", assalto.getSexo_vitima());
		user.addProperty("tipo_assalto", assalto.getTipo_assalto());
		user.addProperty("hora_assalto", assalto.getHora_assalto());
		user.addProperty("data_assalto", assalto.getData_assalto());

		inserirAssalto.addSoapObject(user);


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirAssalto);
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
}
