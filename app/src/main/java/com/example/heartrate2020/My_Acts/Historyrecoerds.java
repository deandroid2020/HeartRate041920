package com.example.heartrate2020.My_Acts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.heartrate2020.Model.Reading;
import com.example.heartrate2020.R;
import com.example.heartrate2020.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Historyrecoerds extends AppCompatActivity {

    String TAG="------------";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Reading> readingList = new ArrayList<>();
    Handler handler;
    Double at=0.0 , ay=0.0 , ab=0.0 , as=0.0;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyrecoerds);
        session= new Session(getApplicationContext());

        final TextView today , yesday , toavg , yesavg;
        today=findViewById(R.id.todadate);
        yesday=findViewById(R.id.yesdate);
        toavg=findViewById(R.id.todayavg);
        yesavg=findViewById(R.id.yesavg);

        getDocId(session.getMemberId());

        Calendar cal = Calendar.getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("EEE yyyy-MM-dd");

        cal.add(Calendar.DATE, -1);
        final Date y=cal.getTime();
        cal.add(Calendar.DATE, -2);
        final Date b=cal.getTime();
        cal.add(Calendar.DATE, -7);
        final Date s=cal.getTime();


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FindAvarage (new Date(), y , b , s);
                today.setText(dateFormat.format(new Date()));
                DecimalFormat df = new DecimalFormat("####0.00");
                toavg.setText(df.format(at));
                yesday.setText(dateFormat.format(y));
                yesavg.setText(df.format(ay));
            }
        }, 1000);


    }


        private void getDocId(int id) {


            db.collection("patient").whereEqualTo("PId", String.valueOf(id)).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " ============> " + document.getId());

                                    FirebaseFirestore ab = FirebaseFirestore.getInstance();
                                    ab.collection("patient").document(document.getId()).collection("Tracking").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    Log.d(TAG , document.get("heart_rate").toString());

                                                    Reading reading= new Reading();
                                                    reading.setReading(Double.valueOf(document.get("heart_rate").toString()));
                                                    reading.setTime(document.getDate("time"));
                                                    readingList.add(reading);

                                                }
                                            }
                                        }
                                    });
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("ffffffffffffff", e.getMessage());
                }
            });

        }

        private  void FindAvarage (Date today , Date yester , Date before , Date Seven)
        {

            int t=0 , y=0 , b=0 , s=0;
            Double tc=0.0 , yc=0.0 , bc=0.0 , sc=0.0;

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


            for (Reading r : readingList){

                Log.d("Noooooooow " ,dateFormat.format(r.getTime()));
                Log.d("Readiiiiing" ,dateFormat.format(yester));

                if (dateFormat.format(r.getTime()).equals(dateFormat.format(today))) {
                    Log.d("sss" ,r.getReading()+" -------");
                    t++;
                    tc+=r.getReading();
                     }
                if (dateFormat.format(r.getTime()).equals(dateFormat.format(yester))) {
                    y++;
                    yc+=r.getReading();
                    }
                if (dateFormat.format(r.getTime()).equals(dateFormat.format(before))) {
                    b++;
                    bc+=r.getReading();


                    }
                if (dateFormat.format(r.getTime()).equals(dateFormat.format(Seven))) {
                    s++;
                    sc+=r.getReading();
                    }
            }

            at=  tc/t;
            ay=yc/y;
            ab=bc/b;
            as=sc/s;

        }



}
