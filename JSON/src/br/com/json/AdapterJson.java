package br.com.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterJson extends BaseAdapter{

    private int resource;
    private LayoutInflater inflater;
    private Context context;
    List<Pessoa> lista;

    public AdapterJson(Context ctx, int resourceId,
                                    List<Pessoa> lista) {
      //  super(ctx, resourceId, objects);
        this.resource = resourceId;
        this.inflater = LayoutInflater.from(ctx);
        this.context = ctx;
        this.lista = lista;

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Pessoa getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = (RelativeLayout) inflater.inflate(resource, null);
        Pessoa Legend = getItem(position);
        TextView legendNome = (TextView) convertView
                .findViewById(R.id.txtendereco);
        legendNome.setText(Legend.getEndereco());

        TextView legendAtividade = (TextView) convertView
                .findViewById(R.id.txtnome);
        legendAtividade.setText(Legend.getNome());



        TextView tipoTempo = (TextView) convertView
                .findViewById(R.id.txtid);
        tipoTempo.setText(Legend.getCpf());
        
        return  convertView;

    }

}
