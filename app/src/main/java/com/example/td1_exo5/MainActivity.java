package com.example.td1_exo5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST_CODE_SEND_SMS = 1 ;
    private EditText numero;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numero = (EditText) findViewById(R.id.numero);
        message = (EditText) findViewById(R.id.message);
        Button envoyer = (Button)findViewById(R.id.btnEnvoie);
        envoyer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                askPermissionAndSendSMS(v);
                sendSMS(v);
            }});

    }


    public void askPermissionAndSendSMS(View v) {
        if (android.os.Build.VERSION.SDK_INT >=  android.os.Build.VERSION_CODES.M) {
            int sendSmsPermisson = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS);

            if (sendSmsPermisson != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSION_REQUEST_CODE_SEND_SMS
                );

                return;
        }
    }

    }


    public void sendSMS(View v){



        String a = numero.getText().toString();
        String b = message.getText().toString();

        List<String> items = Arrays.asList(a.split(";"));
        
        if(!b.isEmpty() & a.length()>=4) {

            Iterator<String> i = items.iterator();
            while (i.hasNext()) {

                SmsManager.getDefault().sendTextMessage(i.next(),null, b, null, null);
            }
            message.setText("");
            numero.setText("");
            Toast.makeText(this, "Message envoyé avec succès",Toast.LENGTH_LONG).show();}
        else {
            Toast.makeText(this, "Veuillez saisir un numéro et message valide",Toast.LENGTH_LONG).show();
        }

    }


}