package com.example.clockon2.Contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clockon2.Contact.Contact;
import com.example.clockon2.Contact.ContactAdapter;
import com.example.clockon2.R;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    ListView lp;
    Button btnappel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        lp=(ListView)findViewById(R.id.lstv);
        ArrayList<Contact> lstC=new ArrayList<>();
        lstC.add(new Contact("SAMU","190"));
        lstC.add(new Contact("Garde nationale","193"));
        lstC.add(new Contact("Protection civile","198"));
        lstC.add(new Contact("Police de secours","197"));
        ContactAdapter adapter=new ContactAdapter(this,R.layout.itemcontact,lstC);
        lp.setAdapter(adapter);

        lp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final TextView txtnum=view.findViewById(R.id.txtNuméro);
                btnappel=view.findViewById(R.id.btnappel);
                btnappel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+txtnum.getText().toString()));
                        startActivity(appel);
                    }
                });

            }
        });


    }
}
