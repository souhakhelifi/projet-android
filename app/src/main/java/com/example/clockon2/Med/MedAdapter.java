package com.example.clockon2.Med;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.clockon2.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class MedAdapter extends ArrayAdapter<Ligne> {
    Context context;
    int resource;
    public MedAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Ligne> objects) {
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
        TextView tvref= (TextView)convertView.findViewById(R.id.txtRef);
        TextView tvdate= (TextView)convertView.findViewById(R.id.txtdate);
        TextView tvduree= (TextView)convertView.findViewById(R.id.txtduree);
        Ligne currentpg= getItem(position);
        tvref.setText(currentpg.getRef_med());
        tvduree.setText(currentpg.getDuree());
        tvdate.setText(currentpg.getDateDeb());
        return convertView;
    }
}
