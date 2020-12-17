package com.example.clockon2.Login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockon2.DAO;
import com.example.clockon2.MainActivity;
import com.example.clockon2.Prog.ProgramActivity;
import com.example.clockon2.R;

public class RegisterActivity extends AppCompatActivity {
    DAO db;
    EditText login;
    EditText pwd;
    EditText pwd2;
    EditText age;
    EditText nom;
    Button btnreg;
    TextView connecter;
    Spinner spprofil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db= new DAO();
        db.openDB(this);
        login=(EditText)findViewById(R.id.loginr);
        pwd=(EditText)findViewById(R.id.mdpr);
        pwd2=(EditText)findViewById(R.id.mdpr2);
        age=(EditText)findViewById(R.id.age);
        nom=(EditText) findViewById(R.id.nom);
        btnreg=(Button)findViewById(R.id.buttonreg);
        connecter=(TextView)findViewById(R.id.tv_login);
        spprofil=(Spinner)findViewById(R.id.spprofil);
        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login1 = login.getText().toString().trim();
                String pwd1 = pwd.getText().toString().trim();
                String nom1 = nom.getText().toString().trim();
                int age1 = Integer.parseInt(age.getText().toString().trim());
                String pwd3 = pwd2.getText().toString().trim();

                // Insert a new record
                ContentValues ct = new ContentValues();
                if(pwd1.equals(pwd3)){
                    Boolean res = db.checkUser(login1, pwd1);
                    if(res == false)
                    {
                        // Assign the values for each column.
                        ct.put("login", login1);
                        ct.put("mdp",pwd1 );
                        ct.put("nom", nom1);
                        ct.put("age",age1);
                        ct.put("profil",spprofil.getSelectedItem().toString());
                        long val = db.insertUser(ct);

                        if(val ==-1){
                            Toast.makeText(RegisterActivity.this,"Registeration Error",Toast.LENGTH_SHORT).show();
                        }
                        else{

                            //Toast.makeText(RegisterActivity.this,"You have registered",Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                            moveToLogin.putExtra("login",login1);
                            startActivity(moveToLogin);
                        }

                    }
                    else{ Toast.makeText(RegisterActivity.this,"User already exist",Toast.LENGTH_SHORT).show();}
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
