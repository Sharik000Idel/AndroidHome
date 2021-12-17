package com.example.text2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class RegistrActivity extends AppCompatActivity {
    String FILE_NAME = "content.txt";
    EditText login , password1 , password2;
    Button registr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        login = findViewById(R.id.ed_login);
        password1 = findViewById(R.id.ed_password1);
        password2 = findViewById(R.id.ed_password2);

        registr = findViewById(R.id.but_regis_regis);

        registr.setOnClickListener(v -> {

            if (!login.getText().toString().isEmpty() && !password1.getText().toString().isEmpty() &&
                    password1.getText().toString().equals(password2.getText().toString())){

                FileOutputStream fos = null;
                try {

                    String text = login.getText().toString() + "+" +password1.getText().toString() ;

                    fos = openFileOutput(FILE_NAME, MODE_APPEND);
                    fos.write(text.getBytes());
                    Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
                finally{
                    try{
                        if(fos!=null)
                            fos.close();
                    }
                    catch(Exception e){ }
                }
                Intent intent = new Intent(this , MyLoginActivity.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(this, "Неверный формат", Toast.LENGTH_SHORT).show();
            }

        });
    }
}