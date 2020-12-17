package com.example.clockon2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockon2.Contact.ContactActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TempActivity extends AppCompatActivity {

    private TextView textView;
    private SeekBar seekBar;
    Button btnAjout;
    ContentValues values;
    String login ;
    ListView lp;
DAO dao ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        textView=(TextView)findViewById(R.id.textV);
        seekBar=(SeekBar)findViewById(R.id.seekBar2);
        btnAjout=(Button)findViewById(R.id.btnajout) ;
        dao=new DAO();
        lp=(ListView)findViewById(R.id.lstV);
        Intent in=getIntent();
        login=in.getStringExtra("login");
        //charger seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        textView.setText(""+i+" °C");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
});

        //bouton ajouter
        dao.openDB(this);
        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values=new ContentValues();
                int degree=seekBar.getProgress();
                values.put("degree",degree);
                values.put("login",login);
                values.put("date",new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                long rowId=dao.insertTemp(values);
                if (rowId == -1) {
                    Toast.makeText(TempActivity.this, "Ajout échoué !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TempActivity.this, "La température est ajouté avec succès!", Toast.LENGTH_LONG).show();
                    ArrayList<Temperature> values=dao.getTemp(TempActivity.this,login);
                    if(values.size()!=0){
                        TempAdapter adapter=new TempAdapter(TempActivity.this,R.layout.itemtemp,values);
                        lp.setAdapter(adapter);}
                    if(degree>39){
                        Toast.makeText(TempActivity.this, "Votre température est élevée !", Toast.LENGTH_LONG).show();
                        new AlertDialog.Builder(TempActivity.this).setIcon(android.R.drawable.alert_dark_frame)
                                .setTitle("Votre température est élevée !")
                                .setMessage("Vous voulez voir ? ")
                                .setPositiveButton("Médecin", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent in=new Intent(TempActivity.this,MapsActivity2.class);
                                        startActivity(in);
                                    }
                                }).setNegativeButton("Urgence", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent in=new Intent(TempActivity.this, ContactActivity.class);
                                startActivity(in);
                            }
                        })
                                .show();
                    }
                }
            }
        });

        //charger listView
        ArrayList<Temperature> values=dao.getTemp(TempActivity.this,login);
        if(values.size()!=0){
        TempAdapter adapter=new TempAdapter(this,R.layout.itemtemp,values);
        lp.setAdapter(adapter);}
    }
}
