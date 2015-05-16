package br.com.json;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {

    private Context context;
    private ListView listViewInfoAtividades;
    private ArrayAdapter adapterAtividades;
    HttpJson JsonAtividades = new HttpJson();
    List<Pessoa> legendList = new ArrayList<Pessoa>();

    Pessoa listaInformacoes = new Pessoa(null, null, null,null);
    Pessoa listaLegend;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        context = getApplicationContext();


        listViewInfoAtividades = (ListView) findViewById(R.id.Atividades_Lista);
        //adapterAtividades = new ArrayAdapter<String>(context, R.layout.legend_row_item ,listaAtividadesEstadoAtual);

        new HttpAsyncTask().execute("http://192.168.0.103:8080/Web-Service-Restful/cliente/listarTodos");

        listViewInfoAtividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(),
                        Detalhes.class);

                intent.putExtra("cpf", legendList.get(position).getCpf());
                intent.putExtra("endereco", legendList.get(position).getEndereco());
                intent.putExtra("id", legendList.get(position).getId());
                intent.putExtra("nome", legendList.get(position).getNome());


                startActivity(intent);
            }
        });

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


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return JsonAtividades.GET(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {



            try {
                JSONObject json = new JSONObject(result);
                JSONArray articles = json.getJSONArray("cliente");
                int tamanhoLista=json.getJSONArray("cliente").length();



                for(int i=0;i<tamanhoLista;i++) {

                    listaInformacoes.setCpf(articles.getJSONObject(i).getString("cpf"));
                    listaInformacoes.setEndereco(articles.getJSONObject(i).getString("endereco"));
                    listaInformacoes.setId(articles.getJSONObject(i).getString("id"));
                    listaInformacoes.setNome(articles.getJSONObject(i).getString("nome"));
                 

                    legendList.add(new Pessoa(articles.getJSONObject(i).getString("cpf"), articles.getJSONObject(i).getString("endereco"),articles.getJSONObject(i).getString("id"), articles.getJSONObject(i).getString("nome")));
                }

               listViewInfoAtividades.setAdapter(new ListAdapter(context, R.layout.legend_row_item, legendList));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }
}
