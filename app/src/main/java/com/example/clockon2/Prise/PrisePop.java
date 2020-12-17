package com.example.clockon2.Prise;

import android.app.Activity;
import android.app.Dialog;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.clockon2.DAO;
import com.example.clockon2.Med.Medicament;
import com.example.clockon2.R;

import java.util.ArrayList;
import java.util.List;

public class PrisePop extends Dialog {
    private Button btnajout;
    private TextView txtdescr,txtheure,txtqte;
    private TextView txtdate;
    TextView txtref;
    Spinner sptype;

    //constructeur
    public PrisePop(Activity activity,String ref_med){
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        setContentView(R.layout.pop_prise);
        btnajout=(Button)findViewById(R.id.btnajout);
        txtdate=(TextView) findViewById(R.id.datePicker1);
        txtdescr=(TextView)findViewById(R.id.txtdescr);
        txtheure=(TextView)findViewById(R.id.txtheure);
        txtqte=(TextView)findViewById(R.id.txtqte);
        txtref=(TextView) findViewById(R.id.spinner);
        sptype=(Spinner)findViewById(R.id.sptype);
        txtref.setText(ref_med);
    }

    public Spinner getSptype() {
        return sptype;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public TextView getTxtdescr() {
        return txtdescr;
    }

    public TextView getTxtheure() {
        return txtheure;
    }

    public TextView getTxtqte() {
        return txtqte;
    }

    public TextView getTxtref() {
        return txtref;
    }

    public TextView getTxtdate() {
        return txtdate;
    }

    public void build(){
        show();
    }
}
