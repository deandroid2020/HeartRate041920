package com.example.heartrate2020.My_Acts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.heartrate2020.MainActivity;
import com.example.heartrate2020.R;
import com.example.heartrate2020.Session;

public class DrPage extends AppCompatActivity {

    Session session ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_page);

        Button alertbtn = findViewById(R.id.btnalert);
//        Button report = findViewById(R.id.reportbtn);


        alertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Alert.class));


            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        session =new Session(getApplicationContext());

//report.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        Intent intent = new Intent(DrPage.this , PReport.class);
//        startActivity(intent);
//
//    }
//});

    }


    public void ViewP (View view)
    {
        Intent intent = new Intent(DrPage.this , ManagePatient.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){


            case R.id.menuLogout:

                session.LogOut();
                startActivity(new Intent(getApplicationContext(), LogIn.class));

                break;
                
            case R.id.menuAbout:
                alertDialogDemo();

                //  Toast.makeText(this, "You are logging out", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

//    public void Logout(View view) {
//
//        session.LogOut();
//        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//
//    }


public void alertDialogDemo() {
AlertDialog.Builder builder = new AlertDialog.Builder(this);
builder.setTitle("About us!");
builder.setMessage("This  app was desgined by Reem, Raniah , sundos,Mayar");
builder.setCancelable(true);
builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

    @Override
public void onClick(DialogInterface dialog, int which) {

    Toast.makeText(getApplicationContext(), "Neutral button clicked", Toast.LENGTH_SHORT).show();
}
});
builder.show();
    }

}
