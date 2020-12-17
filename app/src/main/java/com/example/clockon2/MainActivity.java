package com.example.clockon2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clockon2.Contact.ContactActivity;
import com.example.clockon2.Prog.ProgramActivity;

public class MainActivity extends AppCompatActivity {


    Button btnCns,btnTemp,btnCon,btnAjout,btnMed,btnAjMedc;
    String login;
    DAO dao;
    AjoutPop p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCns=(Button)findViewById(R.id.btncns);
        btnTemp=(Button)findViewById(R.id.btnTemp);
        btnAjout=(Button)findViewById(R.id.btnAjout);
        btnCon=(Button)findViewById(R.id.btnCon);
        btnMed=(Button)findViewById(R.id.btnMed) ;
        btnAjMedc=(Button)findViewById(R.id.btnAjMed);
        Intent in=getIntent();
        login =in.getStringExtra("login");
        dao=new DAO();
        dao.openDB(this);
        String profil=dao.getProfilUser(login);
        if(profil.equals("Patient")){btnAjout.setVisibility(View.INVISIBLE);}
        btnCns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, ProgramActivity.class);
                i.putExtra("login",login);
                startActivity(i);
            }
        });
        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, TempActivity.class);
                i.putExtra("login",login);
                startActivity(i);
            }
        });
        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, ContactActivity.class);
                i.putExtra("login",login);
                startActivity(i);
            }
        });
        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=new AjoutPop(MainActivity.this);
                p.getBtnOK().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String login=p.getSpLogin().getSelectedItem().toString();
                        Intent i=new Intent(MainActivity.this, ProgramActivity.class);
                        i.putExtra("login",login);
                        startActivity(i);
                    }
                });
                p.build();
            }
        });
        btnMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, MapsActivity2.class);
                i.putExtra("login",login);
                startActivity(i);
            }
        });
        btnAjMedc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, MedicamentActivity.class);
                startActivity(i);
            }
        });
    }

}
