package com.example.clockon2.Prog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.clockon2.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProgAdapter extends ArrayAdapter<Programme> {
    Context context;
    int resource;
    Button btnDel ;
    Programme currentpg;

    public ProgAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Programme> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    //converView cest litem a constrire
    //viewgroup cest la listview
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        convertView= LayoutInflater.from(context).inflate(resource,parent,false);
        TextView tvdate= (TextView)convertView.findViewById(R.id.txtdatedeb);
        TextView tvduree= (TextView)convertView.findViewById(R.id.txtduree);
        TextView tvmaladie= (TextView)convertView.findViewById(R.id.txtmaladie);
        TextView tvnumP= (TextView)convertView.findViewById(R.id.txtnump);
        currentpg= getItem(position);
        tvdate.setText("A partir de "+currentpg.getDateDeb());
        tvduree.setText("durant: "+currentpg.getDuree());
        tvmaladie.setText(currentpg.getMaladie());
        tvnumP.setText(""+currentpg.getNum_p());

        return convertView;
    }
}
