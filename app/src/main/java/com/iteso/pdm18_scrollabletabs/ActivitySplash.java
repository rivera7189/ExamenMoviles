package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.pdm18_scrollabletabs.beans.User;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                User user = LoadUser();
                Intent intent;
                if(user.isLogged()){
                    intent = new Intent(ActivitySplash.this,ActivityMain.class);
                }
                else{
                    intent = new Intent(ActivitySplash.this,ActivityLogin.class);
                }
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,3000);

    }
    public User LoadUser(){
        SharedPreferences sp = getSharedPreferences("com.iteso.store.USER_PREFERENCE",MODE_PRIVATE);
        User user = new User();
        user.setName(sp.getString("NAME",null));
        user.setPassword(sp.getString("PASSWORD",null));
        user.setLogged(sp.getBoolean("LOG",false));
        sp=null;
        return user;
    }
}
