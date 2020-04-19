package com.example.heartrate2020.My_Acts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.heartrate2020.CustomItemClickListener;
import com.example.heartrate2020.Model.Pationt;
import com.example.heartrate2020.MyAdaptors.AlertAdaptor;
import com.example.heartrate2020.MyAdaptors.PatientListAdaptor;
import com.example.heartrate2020.R;
import com.example.heartrate2020.Session;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.QueryListener;

import java.util.ArrayList;
import java.util.List;

public class Alert extends AppCompatActivity {


    ListView listView;
    List<com.example.heartrate2020.Model.Alert> alertList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    AlertAdaptor alertAdaptor;
    String TAG;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    private Double lat=0.0, lng=0.0 ;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        session= new Session(getApplicationContext());

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        alertAdaptor = new AlertAdaptor(this, alertList  );
        listView = findViewById(R.id.alertlistv);
        listView.setAdapter(alertAdaptor);

        getalert();
    }

    public void getalert() {


        db.collection("patient").whereEqualTo("DrId", String.valueOf(session.getMemberId())).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " ============> " + document.getId());

                                final com.example.heartrate2020.Model.Alert alert = new com.example.heartrate2020.Model.Alert();
                                alert.setName(document.get("pname").toString());

                                FirebaseFirestore ab = FirebaseFirestore.getInstance();
                                ab.collection("patient").document(document.getId()).collection("Tracking").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                if (Integer.valueOf(document.get("heart_rate").toString()) > 100) {
                                                    alert.setStatus("abnormal");
                                                    alert.setHrate(document.get("heart_rate").toString());
                                                    alert.setTimealert(document.getDate("time"));
                                                    alert.setLatx(document.getDouble("latitude"));
                                                    alert.setLaty(document.getDouble("longitude"));
                                                    alert.setCLatx(lat);
                                                    alert.setCLaty(lng);

                                                } else if (Integer.valueOf(document.get("heart_rate").toString()) < 50) {
                                                    alert.setStatus("risky");
                                                    alert.setHrate(document.get("heart_rate").toString());
                                                    alert.setTimealert(document.getDate("time"));
                                                    alert.setLatx(document.getDouble("latitude"));
                                                    alert.setLaty(document.getDouble("longitude"));
                                                    alert.setCLatx(lat);
                                                    alert.setCLaty(lng);
                                                }
                                                alertList.add(alert);
                                            }
                                            alertAdaptor.notifyDataSetChanged();
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

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled())
            {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    lat= location.getLatitude();
                                    lng= location.getLongitude();

                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat= mLastLocation.getLatitude();
            lng=mLastLocation.getLongitude();

        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }

}
