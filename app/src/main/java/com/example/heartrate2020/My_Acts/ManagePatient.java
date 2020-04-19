package com.example.heartrate2020.My_Acts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heartrate2020.CustomItemClickListener;
import com.example.heartrate2020.Model.Pationt;
import com.example.heartrate2020.MyAdaptors.PatientListAdaptor;
import com.example.heartrate2020.R;
import com.example.heartrate2020.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ManagePatient extends AppCompatActivity {

    ImageView patientadd;
    RecyclerView recyclerView;
    PatientListAdaptor patientListAdaptor;
    List <Pationt>    pationtList =new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG="----";
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_patient);


        session=new Session(getApplicationContext());
        patientadd=findViewById(R.id.addnewwbtn);

        patientadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , AddPatient.class));
            }
        });

        recyclerView =findViewById(R.id.listofpatient);

        patientListAdaptor= new PatientListAdaptor(getApplicationContext(), pationtList, new CustomItemClickListener() {
    @Override
    public void OnItemClick(Pationt p) {

        Intent intent = new Intent(getApplicationContext(),PatientProfile.class);
        intent.putExtra("DocId",p.getDocID());
        intent.putExtra("pname",p.getPation_Name());
        intent.putExtra("Age",p.getPatientage());
        intent.putExtra("address",p.getPAddress());
        intent.putExtra("nationality",p.getPnationality());
        intent.putExtra("emr_name",p.getEmrName());
        intent.putExtra("emr_number",p.getEmrNumber());
        startActivity(intent);
    }



});

        GridLayoutManager gridLayoutManager =new GridLayoutManager(getApplicationContext(),4);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(patientListAdaptor);

                getpatient();

    }//end OnCreate


public void getpatient() {

        db.collection("patient")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            Pationt p;
                            for (QueryDocumentSnapshot document : task.getResult())
                            {

                                if (Integer.valueOf(document.get("DrId").toString())==session.getMemberId()){

                                    Log.d(TAG, "======================>" + document.getId());
                                    p= new Pationt();
                                    p.setPation_Name(document.get("pname").toString());
                                    p.setPatientage(Integer.valueOf(document.get("Age").toString()));
                                    p.setPAddress(document.get("address").toString());
                                    p.setPnationality(document.get("nationali5ty").toString());
                                    p.setEmrName(document.get("emr_name").toString());
                                    p.setDocID(document.getId());
                                    p.setEmrNumber(Integer.valueOf(document.get("emr_number").toString()));

                                    pationtList.add(p);
                                    patientListAdaptor.notifyDataSetChanged();

                                }
                                //     String s  = document.get("").toString();
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });//end addOnCompleteListener
    }//end getpaitent
}
