package com.example.clockon2.Prise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockon2.Prog.Programme;
import com.example.clockon2.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PriseAdapter extends ArrayAdapter<Prise> {
    Context context;
    int resource;
    Button btnDel ;
    Prise currentpg;

    public PriseAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Prise> objects) {
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
        TextView tvheure= (TextView)convertView.findViewById(R.id.txtheure);
        TextView tvdescr= (TextView)convertView.findViewById(R.id.txtdescr);
        TextView tvqte= (TextView)convertView.findViewById(R.id.txtqte);
        TextView tvref= (TextView)convertView.findViewById(R.id.txtref);
        TextView tvnumPr= (TextView)convertView.findViewById(R.id.txtnumPr);
        currentpg= getItem(position);
        tvdate.setText("Le  "+currentpg.getDate());
        tvheure.setText("A : "+currentpg.getHeure());
        tvdescr.setText(currentpg.getDescr());
        tvnumPr.setText(""+currentpg.getNum_prise());
        //Toast.makeText(getContext(),currentpg.getQte(),Toast.LENGTH_LONG).show;
        tvqte.setText(""+currentpg.getQte());
        tvref.setText(""+currentpg.getRef_med());

        return convertView;
    }
}
