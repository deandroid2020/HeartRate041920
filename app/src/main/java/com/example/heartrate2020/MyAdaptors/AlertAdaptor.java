package com.example.heartrate2020.MyAdaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heartrate2020.Model.Alert;
import com.example.heartrate2020.R;
import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AlertAdaptor extends BaseAdapter {


    Activity activity;
    LayoutInflater inflater;
    List <Alert> listofalerts ;


    public AlertAdaptor(Activity activity, List<Alert> listofalerts) {
        this.activity = activity;
        this.listofalerts = listofalerts;

    }

    @Override
    public int getCount() {
        return listofalerts.size();
    }

    @Override
    public Object getItem(int position) {
        return listofalerts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.alertrow,null);

        TextView name =convertView.findViewById(R.id.rowname);
        TextView Hrate = convertView.findViewById(R.id.rowrate);
        TextView Rtime = convertView.findViewById(R.id.rowtime);
        TextView status = convertView.findViewById(R.id.rowstatus);
        ImageView img = convertView.findViewById(R.id.alertimg);

        name.setText(listofalerts.get(position).getName());
        Hrate.setText(listofalerts.get(position).getHrate());
        status.setText(listofalerts.get(position).getStatus());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Rtime.setText(dateFormat.format(listofalerts.get(position).getTimealert()));


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+listofalerts.get(position).getCLatx()
                +","+listofalerts.get(position).getCLaty()+"&daddr="+listofalerts.get(position).getLatx()+","+listofalerts.get(position).getLaty()));
        activity.startActivity(intent);

            }
        });



        return convertView;
    }




}
