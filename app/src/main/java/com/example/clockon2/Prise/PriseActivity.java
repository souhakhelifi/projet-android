package com.example.clockon2.Prise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.clockon2.AlertReceiver;
import com.example.clockon2.DAO;
import com.example.clockon2.Med.MedActivity;
import com.example.clockon2.Med.MedPop;
import com.example.clockon2.Med.Medicament;
import com.example.clockon2.MedicamentActivity;
import com.example.clockon2.R;
import com.example.clockon2.TimePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class PriseActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    ListView lp;
    DAO dao=new DAO();
    Button btn;
    PrisePop p ;
    String ref_med;
    ArrayList<Prise> values;
    DatePickerDialog picker;
    DialogFragment timePicker;
    TextView txttime,txtdate;
    Calendar copie ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prise);

        Intent in=getIntent();
        ref_med=in.getStringExtra("ref_med");
        Toast.makeText(this,""+ref_med,Toast.LENGTH_LONG).show();
        lp=(ListView)findViewById(R.id.list_prise);
        btn=(Button)findViewById(R.id.btnajouter);

//Remplir liste view
        values=new ArrayList<>();
        dao.openDB(this);
        values=dao.getPrises(this,ref_med);
        PriseAdapter adapter=new PriseAdapter(this,R.layout.itemprise,values);
        lp.setAdapter(adapter);
        //Preparer le pop up d'ajout
        p=new PrisePop(PriseActivity.this,ref_med);

        lp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String numP = ((TextView) view.findViewById(R.id.txtnumPr)).getText().toString();
                new AlertDialog.Builder(PriseActivity.this).setIcon(android.R.drawable.ic_delete)
                        .setTitle("Vous etes sur ?")
                        .setMessage("Vous voulez supprimer cette prise de médicament ? ")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                long rowId=dao.deletePrise(Integer.parseInt(numP));
                                if (rowId == -1) {
                                    Toast.makeText(PriseActivity.this, "Erreur lors du suppression", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(PriseActivity.this, "Prise est supprimée avec succès!", Toast.LENGTH_LONG).show();
                                    values = dao.getPrises(PriseActivity.this,ref_med);
                                    PriseAdapter adapterr = new PriseAdapter(PriseActivity.this, R.layout.itemprise, values);
                                    lp.setAdapter(adapterr);
                                }
                            }
                        }).setNegativeButton("Non",null)
                        .show();
                return true;
            }
        });
        //Bouton ajouter
        p.getBtnajout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues ct = new ContentValues();
                ct.put("descr", p.getTxtdescr().getText().toString());
                ct.put("date", picker.getDatePicker().getDayOfMonth()+"/"+ (picker.getDatePicker().getMonth()+1)+"/"+picker.getDatePicker().getYear());
                ct.put("heure",copie.get(Calendar.HOUR_OF_DAY)+"h"+copie.get(Calendar.MINUTE));

                ct.put("qte", p.getTxtqte().getText().toString()+" "+p.getSptype().getSelectedItem().toString());
                Toast.makeText(PriseActivity.this,ct.get("qte").toString(),Toast.LENGTH_LONG).show();

                ct.put("ref_med", p.getTxtref().getText().toString());
                //Toast.makeText(MedActivity.this,"nump="+num_p+"ref="+String.valueOf(p.getTxtRef().getSelectedItem()),Toast.LENGTH_LONG).show();
                long rowId=dao.insertPrise(ct);
                if (rowId == -1) {
                    Toast.makeText(PriseActivity.this, "Ajout échoué !",Toast.LENGTH_LONG).show();

                } else {
                   // Toast.makeText(PriseActivity.this, "La prise de médicament est ajoutée avec succès!", Toast.LENGTH_LONG).show();
                    startAlarm(copie);
                    values=dao.getPrises(PriseActivity.this,ref_med);
                    PriseAdapter adapter=new PriseAdapter(PriseActivity.this,R.layout.itemprise,values);
                    lp.setAdapter(adapter);
                    p.dismiss();
                } }
        });
        p.getTxtdate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(PriseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    p.getTxtdate().setText(dayOfMonth+"/"+ monthOfYear+"/"+year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        p.getTxtheure().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        //Bouton ajouter
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.build();
            }        }
        );


    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        p.getTxtheure().setText(hourOfDay+"H"+minute);
        copie=c ;
        //startAlarm(c);
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DAY_OF_MONTH, picker.getDatePicker().getDayOfMonth());
            c.add(Calendar.MONTH,picker.getDatePicker().getMonth()+1);
            c.add(Calendar.YEAR,picker.getDatePicker().getYear());


        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),24 * 60 * 60 * 1000,pendingIntent);
        }
    }
}
