package com.example.clockon2.Med;

import android.app.Activity;
import android.app.Dialog;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.clockon2.DAO;
import com.example.clockon2.R;

import java.util.ArrayList;
import java.util.List;

public class MedPop extends Dialog {

    private Button btnajout;
    private TextView txtajout,txtdate,txtduree;
    Spinner txtRef,spduree;


    //constructeur
    public MedPop(Activity activity){
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        setContentView(R.layout.pop_med);
        btnajout=(Button)findViewById(R.id.btnajout);
        txtRef=(Spinner)findViewById(R.id.spinner);
        txtajout=(TextView)findViewById(R.id.txtajout);
        txtdate=(TextView)findViewById(R.id.txtdate);
        txtduree=(TextView)findViewById(R.id.txtduree);
        spduree=(Spinner)findViewById(R.id.spduree);
    }

    public TextView getTxtdate() {
        return txtdate;
    }

    public TextView getTxtduree() {
        return txtduree;
    }

    public Spinner getSpduree() {
        return spduree;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public Spinner getTxtRef() {
        return txtRef;
    }

    public TextView getTxtajout() {
        return txtajout;
    }

    public void build(){
        DAO dao=new DAO();
        dao.openDB(getContext());
        ArrayList<Medicament> med = dao.getMed(getContext());
        List<String> list = new ArrayList<String>();
        for(int i=0;i<med.size();i++){
          list.add(med.get(i).getRef_med());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        txtRef.setAdapter(dataAdapter);
        show();
    }
}
