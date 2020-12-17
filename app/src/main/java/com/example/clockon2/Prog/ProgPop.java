package com.example.clockon2.Prog;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.clockon2.R;

public class ProgPop extends Dialog {

    private Button btnajout;
    private TextView txtmaladie,txtduree,txtdate;
    private Spinner spDuree;

    //constructeur
    public ProgPop(Activity activity){
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        setContentView(R.layout.pop_prog);
        btnajout=(Button)findViewById(R.id.btnajout);
        txtdate=(TextView)findViewById(R.id.txtdate);
        txtmaladie=(TextView)findViewById(R.id.txtmaladie);
        txtduree=(TextView)findViewById(R.id.txtduree);
        spDuree=(Spinner)findViewById(R.id.spduree);
    }

    public Spinner getSpDuree() {
        return spDuree;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public TextView getTxtmaladie() {
        return txtmaladie;
    }

    public TextView getTxtduree() {
        return txtduree;
    }

    public TextView getTxtdate() {
        return txtdate;
    }

    public void build(){
        show();
    }
}
