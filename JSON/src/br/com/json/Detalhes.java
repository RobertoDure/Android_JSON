package br.com.json;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Detalhes extends Activity  {

    private Context context;
    private ListView listViewInfoAtividades;
    private ArrayAdapter adapterAtividades;
    HttpJson JsonAtividades = new HttpJson();
    ArrayList<String> listaAtividadesEstadoAtual = new ArrayList<String>();
    List<Pessoa> legendList = new ArrayList<Pessoa>();
    String cliente;
    private String atividadeSelecionada;
    String cpf;
    String endereco;
    String id;
    String nome;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent intent = getIntent();
        cpf = intent.getStringExtra("cpf");
        endereco = intent.getStringExtra("endereco");
        id = intent.getStringExtra("id");
        nome = intent.getStringExtra("nome");


        TextView textCpf = (TextView) findViewById(R.id.textcpf);
        textCpf.setText("CPF : " + id);

        TextView textNome = (TextView) findViewById(R.id.txtnome);
        textNome.setText("Nome: " + endereco);

        TextView textId = (TextView) findViewById(R.id.textid);
        textId.setText("ID : " + cpf);

        TextView textEndereco = (TextView) findViewById(R.id.textendereco);
        textEndereco.setText("Endereço: " + nome);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
