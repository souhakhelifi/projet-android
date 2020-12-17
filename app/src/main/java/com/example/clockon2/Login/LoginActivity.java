package com.example.clockon2.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockon2.DAO;
import com.example.clockon2.MainActivity;
import com.example.clockon2.Prog.ProgramActivity;
import com.example.clockon2.R;

public class LoginActivity extends AppCompatActivity {

    DAO db;
    EditText login;
    EditText pwd;
    Button btnlogin;
    TextView inscrire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db= new DAO();
        db.openDB(this);
        login=(EditText)findViewById(R.id.login);
        pwd=(EditText)findViewById(R.id.mdp);
        btnlogin=(Button)findViewById(R.id.button);
        inscrire=(TextView)findViewById(R.id.textview_register);
        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login1 = login.getText().toString().trim();
                String pwd1 = pwd.getText().toString().trim();
                Boolean res = db.checkUser(login1, pwd1);
                if(res == true)
                {
                    Toast.makeText(LoginActivity.this,"You have connected successfully",Toast.LENGTH_SHORT).show();
                    Intent HomePage = new Intent(LoginActivity.this, MainActivity.class);
                    HomePage.putExtra("login",login1);
                    startActivity(HomePage);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
