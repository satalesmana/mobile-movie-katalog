package com.sata.katalogfilem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_pasword);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        getSupportActionBar().hide();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProses();
            }
        });
    }
    protected  void  onRegister(){
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    protected void loginProses(){
        String defaultUsername = "Admin";
        String defaultPassword = "Admin123";

//        ProgressDialog pd = new ProgressDialog(this);
//        pd.setIndeterminate(true);
//        pd.setCancelable(false);
//        pd.setInverseBackgroundForced(false);
//        pd.setCanceledOnTouchOutside(false);
//        pd.setMessage("Login");
//        pd.show();

        if(etUsername.getText().toString().equals(defaultUsername)
                && etPassword.getText().toString().equals(defaultPassword) ){
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();

        }else{

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Username dan Password tidak sesuai");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }


}
