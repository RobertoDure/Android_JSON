package br.com.alerta_online.principal;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.alerta_online.entidades_assaltos.Assalto;
import br.com.alerta_online.entidades_assaltos.Itens_Assalto;
import br.com.alerta_online.entidades_dao.AssaltosDao;
import br.com.alerta_online.entidades_dao.CidadesDao;
import br.com.alerta_online.entidades_dao.EstadosDao;
import br.com.alerta_online.entidades_dao.Itens_AssaltoDao;
import br.com.alerta_online.entidades_estados_cidades.Cidades;
import br.com.alerta_online.entidades_estados_cidades.Estados;
import br.com.alerta_online.entidades_usuarios.Usuario;
import br.com.alertaonline.MainActivity;
import br.com.alertaonline.R;

public class Principal_Assaltos_Activity extends Activity {
	 String latitude;
	 String longitude;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal_assalto);
		 Intent itn = getIntent();
		  latitude = itn.getStringExtra("latitude");
		  longitude = itn.getStringExtra("longitude");
		   Log.d("latitude tela assalto", latitude+"");
		   Log.d("longitude tela assalto", longitude+"");
		final Spinner spinner_estado;

		spinner_estado = (Spinner) findViewById(R.id.spinner_estado);

		spinner_estado.setOnTouchListener(new OnTouchListener()

		{

			@Override
			public boolean onTouch(View v, MotionEvent event) 

			{
				EstadosDao dao = new EstadosDao();

				ArrayList<Estados> lista = dao.buscarTodosEstados();

				ArrayAdapter<Estados> adpusr = new ArrayAdapter<Estados> (Principal_Assaltos_Activity.this,android.R.layout.simple_list_item_1, lista);

				spinner_estado.setAdapter(adpusr);

				return false;


			}

			@Override
			protected void finalize() throws Throwable

			{
				// TODO Auto-generated method stub
				super.finalize();
			}
		});

		spinner_estado.setOnItemSelectedListener(new OnItemSelectedListener() 

		{

			@Override
			public void onItemSelected(AdapterView<?> av, View v,  int position, long id)

			{

				CidadesDao dao2 = new CidadesDao();

				ArrayList<Cidades> lista2 = dao2.buscarTodasCidades(position + 1);

				ArrayAdapter<Cidades> adpusra = new ArrayAdapter<Cidades> (Principal_Assaltos_Activity.this,android.R.layout.simple_list_item_1, lista2);

				Spinner spinner_cidade = (Spinner) findViewById(R.id.spinner_cidade);

				spinner_cidade.setVisibility(1);

				spinner_cidade.setAdapter(adpusra);


			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)

			{
				// TODO Auto-generated method stub
			}

		});

		Button btn_registrar = (Button) findViewById(R.id.btn_registrar);

		btn_registrar.setOnClickListener(new OnClickListener()

		{

			@Override
			public void onClick(View v) 

			{
				final Spinner spinner_TipoCrime = (Spinner) findViewById(R.id.spinner_tipo);
				EditText editText_DataCrime = (EditText) findViewById(R.id.editText_DataCrime);
				EditText editText_HoraCrime = (EditText) findViewById(R.id.editText_HoraCrime);
				EditText editText_ItensRoubados = (EditText) findViewById(R.id.editText_ItensRoubados);
				EditText editText_SexoVitima = (EditText) findViewById(R.id.editText_SexoVitima);
				String tipo = spinner_TipoCrime.getSelectedItem().toString();
				
				String data = editText_DataCrime.getText().toString();
				String hora = editText_HoraCrime.getText().toString();
				String itens = editText_ItensRoubados.getText().toString();
				String sexo = editText_SexoVitima.getText().toString();
			   
				//String comentarios = editText_Comentarios.getText().toString();
		
			//	if (Validador.isNotNull(tipo) && Validador.isNotNull(data) && Validador.isNotNull(hora) && Validador.isNotNull(itens) && Validador.isNotNull(sexo))

			//	{

					AssaltosDao dao2 = new AssaltosDao();

					Itens_AssaltoDao dao4 = new Itens_AssaltoDao();

					int id = 0;
					String email = null;

					Intent intent_dados_recebidos = getIntent();

					Usuario teste = (Usuario) intent_dados_recebidos.getSerializableExtra("usuario");

					Bundle params = intent_dados_recebidos.getExtras();
					int idcadastro = params.getInt("valor");
					String emailcadastro = params.getString("email");

					if (intent_dados_recebidos != null)
					{

						if (teste != null)

						{
							id = teste.getId();
							email = teste.getEmail();		
						}

						else

						{
							id = idcadastro;
							email = emailcadastro;	
						}
					}

					int resultado = dao2.inserirAssalto(new Assalto(0, id, (int)(Math.random()*100),sexo, tipo, hora, data));

					if (resultado != 0)

					{
						boolean resultado2 = dao4.inserirItensAssalto(new Itens_Assalto(0, resultado, itens));

						if (resultado2)
						{
							Toast.makeText(Principal_Assaltos_Activity.this, "Informações inseridas com sucesso" ,Toast.LENGTH_LONG).show();
						}

						else
						{
							Toast.makeText(Principal_Assaltos_Activity.this, "Erro no cadastro, tente novamente" ,Toast.LENGTH_LONG).show();
						}
					}

					else
					{
						while (resultado == 0)

						{
							resultado = dao2.inserirAssalto(new Assalto(0, id, (int)(Math.random()*100), sexo, tipo, hora, data));
							dao4.inserirItensAssalto(new Itens_Assalto(0, resultado, itens));						
						}

						Toast.makeText(Principal_Assaltos_Activity.this, "Informações inseridas com sucesso !" ,Toast.LENGTH_LONG).show();
						Intent it = new Intent(Principal_Assaltos_Activity.this,MainActivity.class);
						startActivity(it);
					}
		    	/*else
					{
						
						Toast.makeText(Principal_Assaltos_Activity.this, "Algum dado necessário está ausente, confira todas as informações" ,Toast.LENGTH_LONG).show();
					}*/
				
			
				}
				
			
		});

	}

	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//		// Inflate the menu; this adds items to the action bar if it is present.
	//		getMenuInflater().inflate(R.menu.principal_assaltos, menu);
	//		return true;
	//	}
	//
	//	@Override
	//	public boolean onOptionsItemSelected(MenuItem item) {
	//		// Handle action bar item clicks here. The action bar will
	//		// automatically handle clicks on the Home/Up button, so long
	//		// as you specify a parent activity in AndroidManifest.xml.
	//		int id = item.getItemId();
	//		if (id == R.id.action_settings) {
	//			return true;
	//		}
	//		return super.onOptionsItemSelected(item);
	//	}
}
