package com.example.clockon2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockon2.Med.Medicament;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TempAdapter extends ArrayAdapter<Temperature> {
    Context context;
    int resource;
    public TempAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Temperature> objects) {
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
        TextView tvdegree= (TextView)convertView.findViewById(R.id.txtdegree);
        TextView tvdate= (TextView)convertView.findViewById(R.id.txtdate);
        Temperature currentpg= getItem(position);
        Toast.makeText(getContext(),""+currentpg.getDegree(),Toast.LENGTH_LONG).show();
        tvdegree.setText(String.valueOf(currentpg.getDegree())+" °C");
        tvdate.setText(currentpg.getDate());
        return convertView;
    }
}
