package com.example.heartrate2020.My_Acts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heartrate2020.R;
import com.example.heartrate2020.Session;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class updatemyownprofile extends AppCompatActivity {

    ImageView updateownprofile;
    TextView done,cnacel;
    EditText  pname , age , address , nationality,emername,emernumber;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String mymesg;
    Session session;
    String TAG = "-------------";
    String DocId =null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatemyownprofile);

        session = new Session(getApplicationContext());


        updateownprofile=findViewById(R.id.updatemebtn);
        done=findViewById(R.id.itextdone);
        cnacel=findViewById(R.id.itextcancle);
        pname=findViewById(R.id.ieditTextpname);
        age=findViewById(R.id.ieditTextage);
        address=findViewById(R.id.ieditTextaddress);
        nationality=findViewById(R.id.ieditTextnatiunality);
        emername=findViewById(R.id.iemrname);
        emernumber=findViewById(R.id.iemrmobile);

        Intent intent= getIntent();


        DocId = intent.getStringExtra("DocId");
        pname.setText(intent.getStringExtra("pname"));
        age.setText(String.valueOf(intent.getIntExtra("Age",0)));
        address.setText(intent.getStringExtra("address"));
        nationality.setText(intent.getStringExtra("nationality"));
        emername.setText(intent.getStringExtra("emr_name"));
        emernumber.setText(String.valueOf(intent.getIntExtra("emr_number",0)));


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Docid After rertive", DocId);
                if ( DocId !=null){

                    DocumentReference washingtonRef = db.collection("patient").document(DocId);


                    final Map<String, Object> data = new HashMap<>();
                    data.put("pname", pname.getText().toString().trim());
                    data.put("Age", age.getText().toString().trim());
                    data.put("address", address.getText().toString().trim());
                    data.put("nationali5ty", nationality.getText().toString().trim());
                    data.put("emr_name", emername.getText().toString().trim());
                    data.put("emr_number", emernumber.getText().toString().trim());

                    washingtonRef.update(data).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(getApplicationContext() , "Patent Data successfully updated!" , Toast.LENGTH_LONG).show();
                            mymesg="patent profile has been modfied";

                            startActivity(new Intent(getApplicationContext() , PatientPage.class));

                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getApplicationContext() , "Error updating document "+e.toString() , Toast.LENGTH_LONG).show();
                                    Log.d("G" , e.toString() +"   "+e.getMessage());
                                }
                            });
                }

                    }
                });//done is done



        updateownprofile.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    pname.setEnabled(false);
                                                    age.setEnabled(false);
                                                    address.setEnabled(true);
                                                    nationality.setEnabled(false);
                                                    emername.setEnabled(true);
                                                    emernumber.setEnabled(true);

                                                }

                                            }
        );


        cnacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pname.setEnabled(false);
                age.setEnabled(false);
                address.setEnabled(false);
                nationality.setEnabled(false);
                emername.setEnabled(false);
                emernumber.setEnabled(false);


            }


    });


    }//end of OnCreate



    private String randomnumber()
    {
        int[] s = {new Random().nextInt(9), new Random().nextInt(9), new Random().nextInt(9), new Random().nextInt(9)};
        String t = "";
        for (int i = 0; i < s.length; i++) {
            t = t + s[i];
        }
        return t;
    }
}//end of updatemypown class



