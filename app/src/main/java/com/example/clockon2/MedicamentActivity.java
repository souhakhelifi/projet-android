package com.example.clockon2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockon2.Med.MedActivity;
import com.example.clockon2.Med.Medicament;

import java.util.ArrayList;

public class MedicamentActivity extends AppCompatActivity {
    DAO dao=new DAO();
    Button btnAjout ;
    TextView txtref;
    ContentValues values ;
    ListView lp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicament);

        btnAjout=(Button)findViewById(R.id.btnajout);
        txtref=(TextView)findViewById(R.id.txtRef);
        lp=(ListView)findViewById(R.id.lstM);
        dao.openDB(this);
        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values=new ContentValues();
                values.put("ref_med",txtref.getText().toString());

                long rowId=dao.insertMed(values);
                if (rowId == -1) {
                    Toast.makeText(MedicamentActivity.this, "Ajout échoué !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MedicamentActivity.this, "Le médicament est ajouté avec succès!", Toast.LENGTH_LONG).show();
                    ArrayList<Medicament> a=new ArrayList<>();
                    ArrayList<String> str=new ArrayList<>();
                    a=dao.getMed(MedicamentActivity.this);
                    for(int i=0;i<a.size();i++){
                        str.add(a.get(i).getRef_med());
                    }
                    ArrayAdapter adapter=new ArrayAdapter(MedicamentActivity.this,android.R.layout.simple_list_item_1,str);
                    lp.setAdapter(adapter);
                }
            }
        });
        ArrayList<Medicament> a=new ArrayList<>();
        ArrayList<String> str=new ArrayList<>();
        a=dao.getMed(this);
        for(int i=0;i<a.size();i++){
            str.add(a.get(i).getRef_med());
        }
        ArrayAdapter adapter=new ArrayAdapter(MedicamentActivity.this,android.R.layout.simple_list_item_1,str);
        lp.setAdapter(adapter);



    }
}
