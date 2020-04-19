package com.example.heartrate2020;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.heartrate2020.My_Acts.DrPage;
import com.example.heartrate2020.My_Acts.LogIn;
import com.example.heartrate2020.My_Acts.PatientPage;

public class MainActivity extends AppCompatActivity {

    Session session ;
    Handler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session =new  Session(getApplicationContext());

        handler = new Handler();

        Log.d ("D" , "Before");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.d ("D" , "After");
                if(  session.getSaved() == false)
                {
                    session.LogOut();
                    startActivity(new Intent(getApplicationContext(), LogIn.class));
                    Log.d ("D" , "Login");
                }
                else {
                    if(session.getType().equals("P")){
                        startActivity(new Intent(getApplicationContext(), PatientPage.class));
                    }
                    if (session.getType().equals("D")){

                        startActivity(new Intent(getApplicationContext(), DrPage.class));
                    }
                }
            }
        },1000);
        Log.d ("D" , "%0000");
        }

}







