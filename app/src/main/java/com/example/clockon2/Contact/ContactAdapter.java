package com.example.clockon2.Contact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.clockon2.Contact.Contact;
import com.example.clockon2.R;

public class ContactAdapter extends ArrayAdapter<Contact>{
        Context context;
        int resource;
        Button btnok;
public ContactAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contact> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;

        }

    public Button getBtnok() {
        return btnok;
    }

    @NonNull
@Override
//converView cest litem a constrire
//viewgroup cest la listview
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(resource,parent,false);
        TextView tvnom= (TextView)convertView.findViewById(R.id.txtNom);
        final TextView tvnum= (TextView)convertView.findViewById(R.id.txtNuméro);
        btnok=(Button)convertView.findViewById(R.id.btnappel);
        Contact currentpg= getItem(position);
        tvnom.setText(currentpg.getNom());
        tvnum.setText(currentpg.getNum());
        return convertView;
        }
}
