package com.example.clockon2.Prog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import android.widget.Toast;

import com.example.clockon2.DAO;
import com.example.clockon2.Med.MedActivity;
import com.example.clockon2.R;

public class ProgramActivity extends AppCompatActivity {
    ListView lp;
    DAO dao=new DAO();
    Button btn ;
    ProgPop p;
    String login ;
    ArrayList<Programme> values;
    Button btnDel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        Intent in=getIntent();
        login =in.getStringExtra("login");

        lp = (ListView) findViewById(R.id.list_prog);
        btn = (Button) findViewById(R.id.btnajouter);
        //AFFICHAGE LISTE
        dao.openDB(this);
        values = dao.getPrograms(this, login);
        ProgAdapter adapter = new ProgAdapter(this, R.layout.itemprog, values);
        lp.setAdapter(adapter);

        lp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapter, View view, int position, long l) {

                String numP = ((TextView) view.findViewById(R.id.txtnump)).getText().toString();
                Intent intent = new Intent(ProgramActivity.this, MedActivity.class);
                intent.putExtra("num_p",numP);
                startActivity(intent);

            }
        });
        lp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

               final String numP = ((TextView) view.findViewById(R.id.txtnump)).getText().toString();
                new AlertDialog.Builder(ProgramActivity.this).setIcon(android.R.drawable.ic_delete)
                        .setTitle("Vous etes sur ?")
                        .setMessage("Vous voulez supprimer ce programme ? ")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long rowId=dao.deleteProg(Integer.parseInt(numP));
                                if (rowId == -1) {
                                    Toast.makeText(ProgramActivity.this, "Erreur lors du suppression", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ProgramActivity.this, "Programme est supprimé avec succès!", Toast.LENGTH_LONG).show();
                                    values = dao.getPrograms(ProgramActivity.this, login);
                                    ProgAdapter adapterr = new ProgAdapter(ProgramActivity.this, R.layout.itemprog, values);
                                    lp.setAdapter(adapterr);
                                }
                            }
                        }).setNegativeButton("Non",null)
                        .show();
                return true;
            }
        });
//BOUTON AJOUTER
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=new ProgPop(ProgramActivity.this);
                p.getBtnajout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ContentValues ct = new ContentValues();
                        ct.put("dateDeb", p.getTxtdate().getText().toString());
                        ct.put("duree", p.getTxtduree().getText().toString()+" "+p.getSpDuree().getSelectedItem().toString());
                        ct.put("maladie", p.getTxtmaladie().getText().toString());
                        ct.put("login",login);
                        long rowId=dao.insertProg(ct);
                        if (rowId == -1) {
                            Toast.makeText(ProgramActivity.this, "Ajout échoué !",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ProgramActivity.this, "Le programme est ajouté avec succès!", Toast.LENGTH_LONG).show();

                            values=dao.getPrograms(ProgramActivity.this,login);
                            ProgAdapter adapter=new ProgAdapter(ProgramActivity.this,R.layout.itemprog,values);
                            lp.setAdapter(adapter);
                            p.dismiss();
                        } }
                });
                p.build();
            }        }
        );}
    }



