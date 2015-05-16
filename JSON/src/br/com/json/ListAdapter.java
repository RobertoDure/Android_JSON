package br.com.json;

/**
 * Created by Insideweb2 on 04/05/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Pessoa> {

    private int resource;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapter(Context ctx, int resourceId,
                                 List<Pessoa> objects) {
        super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(ctx);
        context = ctx;

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
        
        return convertView;
    }

}