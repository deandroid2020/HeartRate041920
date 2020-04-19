package com.example.heartrate2020.My_Acts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.heartrate2020.R;
import com.example.heartrate2020.Session;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddPatient extends AppCompatActivity {
ImageView btnadd;
TextView cancel;
Session session;
    EditText  pname , age , address , nationality,emername,emernumber;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        cancel =findViewById(R.id.ptextcancle);
        pname=findViewById(R.id.peditTextpname);
        age=findViewById(R.id.peditTextage);
        address=findViewById(R.id.peditTextaddress);
        nationality=findViewById(R.id.peditTextnatiunality);
        emername=findViewById(R.id.pemrname);
        emernumber=findViewById(R.id.pemrmobile);

      btnadd=findViewById(R.id.addnewpoatinetimg);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Map<String, Object> data = new HashMap<>();
                data.put("pname", pname.getText().toString().trim());
                data.put("Age", age.getText().toString().trim());
                data.put("address", address.getText().toString().trim());
                data.put("nationali5ty", nationality.getText().toString().trim());
                data.put("emr_name", emername.getText().toString().trim());
                data.put("emr_number", emernumber.getText().toString().trim());
                data.put("DrId",session.getMemberId());
                data.put("PId", randomnumber());


                        db.collection("patient").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                //          Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                startActivity(new Intent(getApplicationContext() , ManagePatient.class));
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //          Log.w(TAG, "Error adding document", e);
                                    }
                                });

            }
        });//end of addpatientbtnlisner


    }


    private String randomnumber()
    {
        int[] s = {new Random().nextInt(9), new Random().nextInt(9), new Random().nextInt(9), new Random().nextInt(9)};
        String t = "";
        for (int i = 0; i < s.length; i++) {
            t = t + s[i];
        }
        return t;
    }
}

