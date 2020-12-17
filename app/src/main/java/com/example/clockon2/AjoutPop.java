package com.example.clockon2;

import android.app.Activity;
import android.app.Dialog;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.clockon2.Med.Medicament;

import java.util.ArrayList;
import java.util.List;

public class AjoutPop extends Dialog {

    private Spinner spLogin;
    private Button btnOK;

    public AjoutPop(Activity activity){
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        setContentView(R.layout.pop_ajout);

        spLogin=(Spinner)findViewById(R.id.spLogin);
        btnOK=(Button)findViewById(R.id.btnOK);


    }

    public Spinner getSpLogin() {
        return spLogin;
    }

    public Button getBtnOK() {
        return btnOK;
    }

    public void build(){
        DAO dao=new DAO();
        dao.openDB(getContext());
        ArrayList<User> med = dao.getUsers(getContext());
        List<String> list = new ArrayList<String>();
        for(int i=0;i<med.size();i++){
            list.add(med.get(i).getLogin());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spLogin.setAdapter(dataAdapter);
        show();
    }
}
