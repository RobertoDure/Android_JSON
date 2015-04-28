package br.com.alerta_online.entidades_usuarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.alerta_online.entidades_dao.UsuarioDAO;
import br.com.alerta_online.principal.Principal_Assaltos_Activity;
import br.com.alerta_online.util.Validador;
import br.com.alertaonline.R;



public class Principal_Usuarios_Activity extends Activity

{
	@Override
	protected void onCreate(Bundle savedInstanceState) 

	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal_usuarios);

		if(android.os.Build.VERSION.SDK_INT > 9)

		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		Button btn_inserirNovoUsuario = (Button) findViewById(R.id.btn_CadastrarNovoUsuario);
		final EditText email_usuario = (EditText) findViewById(R.id.editText_EmailCadastro);


		btn_inserirNovoUsuario.setOnClickListener(new OnClickListener()

		{
			@Override
			public void onClick(View v)

			{
				if(Validador.isNotNull(email_usuario.getText().toString()))
				{
					if (Validador.validate(email_usuario.getText().toString()))

					{

						UsuarioDAO dao = new UsuarioDAO();

						Usuario resultado = dao.buscarUsuarioPorEmail(email_usuario.getText().toString());

						if (resultado != null)

						{
							Intent it =  new Intent(Principal_Usuarios_Activity.this, Principal_Assaltos_Activity.class);

							Bundle enviar_dados = new Bundle();
							enviar_dados.putSerializable("usuario", resultado); 
							it.putExtras(enviar_dados);
							startActivity(it);
							finish();
						}


						else 

						{
							int valor = dao.inserirUsuario(new Usuario(0, email_usuario.getText().toString()));
							Intent it =  new Intent(Principal_Usuarios_Activity.this, Principal_Assaltos_Activity.class);
							Bundle enviar_dados = new Bundle();
							enviar_dados.putString("email", email_usuario.getText().toString());
							enviar_dados.putInt("valor", valor); 
							it.putExtras(enviar_dados);
							startActivity(it);
							finish();
						}

					}

					else 

					{
						Toast.makeText(getApplicationContext(), "Email inválido", Toast.LENGTH_LONG).show();
					}
				}

				else 

				{
					Toast.makeText(getApplicationContext(), "Email não pode ficar vazio", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}