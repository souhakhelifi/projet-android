package com.example.clockon2.Med;

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
import android.widget.Toast;

import com.example.clockon2.DAO;
import com.example.clockon2.Med.MedAdapter;
import com.example.clockon2.MedicamentActivity;
import com.example.clockon2.Prise.PriseActivity;
import com.example.clockon2.Prog.ProgAdapter;
import com.example.clockon2.Prog.ProgPop;
import com.example.clockon2.Prog.ProgramActivity;
import com.example.clockon2.R;

import java.util.ArrayList;

public class MedActivity extends AppCompatActivity {
    ListView lp;
    DAO dao=new DAO();
    Button btn;
    MedPop p ;
    String num_p;
    ArrayList<Medicament> values;
    ArrayList<Ligne> valuesRef;
    ArrayList<Medicament> valuesMed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);
        Intent in=getIntent();
        num_p=in.getStringExtra("num_p");
        Toast.makeText(this,""+num_p,Toast.LENGTH_LONG).show();
        lp=(ListView)findViewById(R.id.list_med);
        btn=(Button)findViewById(R.id.btnajouter);
        valuesRef=new ArrayList<>();
        //valuesMed=new ArrayList<>();
        dao.openDB(this);
        valuesRef=dao.getLigne(this,Integer.parseInt(num_p));
       /* Medicament m;
        int i ;
        for(i=0;i<valuesRef.size();i++){
            m=dao.getMed(valuesRef.get(i));
            valuesMed.add(m);
        }*/
        MedAdapter adapter=new MedAdapter(this,R.layout.itemmed,valuesRef);
        lp.setAdapter(adapter);

        lp.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView adapter, View view, int position, long l) {

                String refMed = ((TextView) view.findViewById(R.id.txtRef)).getText().toString();
                Toast.makeText(MedActivity.this,refMed,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MedActivity.this, PriseActivity.class);
                intent.putExtra("ref_med",refMed);
                startActivity(intent);
            }


        });
        lp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String numM = ((TextView) view.findViewById(R.id.txtRef)).getText().toString();
                new AlertDialog.Builder(MedActivity.this).setIcon(android.R.drawable.ic_delete)
                        .setTitle("Vous etes sur ?")
                        .setMessage("Vous voulez supprimer ce médicament ? ")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long rowId=dao.deleteLigne(Integer.parseInt(num_p),numM);
                                if (rowId == -1) {
                                    Toast.makeText(MedActivity.this, "Erreur lors du suppression", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MedActivity.this, "Médicament est supprimé avec succès!", Toast.LENGTH_LONG).show();
                                    valuesRef=dao.getLigne(MedActivity.this,Integer.parseInt(num_p));
                                    /*Medicament m;

                                    for(i=0;i<valuesRef.size();i++){
                                        m=dao.getMed(valuesRef.get(i));
                                        valuesMed.add(m);
                                    }*/
                                    MedAdapter adapter=new MedAdapter(MedActivity.this,R.layout.itemmed,valuesRef);
                                    lp.setAdapter(adapter);
                                }
                            }
                        }).setNegativeButton("Non",null)
                        .show();
                return true;
            }
        });
//Boutton ajouter
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=new MedPop(MedActivity.this);
                p.getTxtajout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in=new Intent(MedActivity.this,MedicamentActivity.class);
                        startActivity(in);
                    }
                });
                p.getBtnajout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ContentValues ct = new ContentValues();
                        ct.put("num_p", num_p);
                        ct.put("ref_med", String.valueOf(p.getTxtRef().getSelectedItem()));
                        ct.put("dateDeb",p.getTxtdate().getText().toString());
                        ct.put("duree",p.getTxtduree().getText().toString()+" "+p.getSpduree().getSelectedItem().toString());
                        long rowId=dao.insertLigne(ct);
                        if (rowId == -1) {
                            Toast.makeText(MedActivity.this, "Ajout échoué !",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MedActivity.this, "La ligne médicament est ajoutée avec succès!", Toast.LENGTH_LONG).show();
                            valuesRef=dao.getLigne(MedActivity.this,Integer.parseInt(num_p));
                            /*Medicament m;
                            valuesMed.clear();
                            int i ;
                            for(i=0;i<valuesRef.size();i++){
                                m=dao.getMed(valuesRef.get(i));
                                valuesMed.add(m);
                            }*/
                            MedAdapter adapter=new MedAdapter(MedActivity.this,R.layout.itemmed,valuesRef);
                            lp.setAdapter(adapter);

                            p.dismiss();
                        } }
                });
                p.build();
            }        }
        );

    }
}
