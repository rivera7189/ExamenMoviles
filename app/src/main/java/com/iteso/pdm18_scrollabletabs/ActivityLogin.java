package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iteso.pdm18_scrollabletabs.beans.User;

public class ActivityLogin extends AppCompatActivity {
    EditText user,pass;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.User);
        pass = findViewById(R.id.Pass);
        log = findViewById(R.id.LogIn);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveUser();
                Intent intent = new Intent(ActivityLogin.this , ActivityMain.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void SaveUser(){
        SharedPreferences sp = getSharedPreferences("com.iteso.store.USER_PREFERENCE",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("NAME",user.getText().toString());
        editor.putString("PASSWORD",user.getText().toString());
        editor.putBoolean("LOG",true);
        editor.apply();
    }
}
