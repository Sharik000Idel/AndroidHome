package com.example.text2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.util.ArrayList;


public class MyLoginActivity extends AppCompatActivity {

    EditText login , password;
    Button In_log , Registr;

    String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);

        login = findViewById(R.id.login_inlog);
        password = findViewById(R.id.password_inlog);

        In_log = findViewById(R.id.inlog_log_but);
        Registr = findViewById(R.id.inlog_new_reg_but);



        In_log.setOnClickListener(v -> {
            if (Reader_file().contains(login.getText().toString()+"+"+password.getText().toString()) &&
                    !login.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                Intent intent = new Intent(this , MainActivity.class);
                startActivity(intent);
            }
        });

        Registr.setOnClickListener(v -> {
            Intent intent = new Intent(this , RegistrActivity.class);
            startActivity(intent);


        });

    }
    private String Reader_file() {

        String users = "";

        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            users = new String(bytes);

        } catch (Exception e) {
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (Exception ex) { }
            return users;
        }
    }
}