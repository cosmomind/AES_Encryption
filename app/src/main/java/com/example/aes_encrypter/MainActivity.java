package com.example.aes_encrypter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    EditText key, message_et;
    TextView message;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        key = findViewById(R.id.textView);
        message_et = findViewById(R.id.textView1);
        message = findViewById(R.id.message1);
        button =  findViewById(R.id.button3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_en = message_et.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"riyamathew3636@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
                intent.putExtra(Intent.EXTRA_TEXT,email_en);
                intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });

    }



    public void encrypt(View view) {
        try {

            String encrypted = AESCrypt.encrypt(key.getText().toString(), message_et.getText().toString());
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("label", encrypted);
            clipboardManager.setPrimaryClip(clipData);
            key.setText("");
            message_et.setText(encrypted);
            Toast.makeText(this, "Your message was copied to clipboard", Toast.LENGTH_SHORT).show();


        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }

    public void decrypt(View view) {
        try {

            String encrypted = AESCrypt.decrypt(key.getText().toString(), message_et.getText().toString());
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("label", encrypted);
            clipboardManager.setPrimaryClip(clipData);
            key.setText("");
            message_et.setText(encrypted);
            Toast.makeText(this, "Your message was copied to clipboard", Toast.LENGTH_SHORT).show();


        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }




}