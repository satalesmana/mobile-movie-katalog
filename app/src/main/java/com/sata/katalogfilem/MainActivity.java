package com.sata.katalogfilem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etSubject, etMessages;
    Button btnSendMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_Email);
        etSubject = findViewById(R.id.et_Subject);
        etMessages = findViewById(R.id.et_Messages);
        btnSendMsg = findViewById(R.id.btn_SendMsg);

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogMsg();
            }
        });

        setTitle("Messages");
    }

    private  void showDialogMsg(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(etEmail.getText().toString() +"\n" + etSubject.getText().toString() +"\n"+ btnSendMsg.getText().toString());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
