package com.example.heartrate2020.My_Acts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heartrate2020.MainActivity;
import com.example.heartrate2020.R;
import com.example.heartrate2020.Session;
import com.example.heartrate2020.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LogIn extends AppCompatActivity {


    Session session ;
    users usr = new users();
    List< users> usersList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button button;
    EditText passwordtext;
    EditText usertext;
    Handler handler ;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        button = findViewById(R.id.loginbutton);
        passwordtext = findViewById(R.id.passwordinput);
        usertext = findViewById(R.id.userinput);

        session = new Session(getApplicationContext());
        handler = new Handler();

        checkBox = findViewById(R.id.checkBox);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(getApplicationContext(), DrPage.class));

                if (usertext.getText().toString().isEmpty() || passwordtext.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "user or password is empty", Toast.LENGTH_LONG).show();

                } else {
                    CeckUserAndPassword(usertext.getText().toString().trim(), passwordtext.getText().toString().trim());

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (usr.getUser_Name() == null) {
                                Toast.makeText(getApplicationContext(), "user is wrong!!", Toast.LENGTH_LONG).show();

                            } else {

                                session.setType(usr.getUser_Type());

                            if (checkBox.isChecked()){
                                session.setSaved(true);
                            }
                                if (session.getType().equals("P")) {
                                    startActivity(new Intent(getApplicationContext(), PatientPage.class));
                                }
                                if (session.getType().equals("D")) {
                                  //  session.setMemberId(2000);
                                    startActivity(new Intent(getApplicationContext(), DrPage.class));
                                }
                            }


                        }
                    }, 2000);

                }
            }

        });
    }



    public void CeckUserAndPassword(final String user, final String password) {
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete (@NonNull Task< QuerySnapshot > task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        if (user.equals(document.get("User_Name").toString()) && password.equals(document.get("Password").toString())) {
                            usr.setUser_Name(document.get("User_Name").toString());
                            usr.setUser_Type(document.get("User_Type").toString());
                            usr.setPassword(document.get("Password").toString());
                            usr.setDacId(document.getId());
                            session.setMemberId(Integer.valueOf(document.get("PId").toString()));
                            Log.d("ddddd" ,  document.get("PId").toString());
                        }
                    }
                } else {
                    Log.w("kjk", "Error getting documents.", task.getException());
                }
            }
        });

    }


}
