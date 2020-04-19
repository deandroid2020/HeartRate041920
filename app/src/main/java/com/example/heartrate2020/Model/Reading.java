package com.example.heartrate2020.Model;

import java.util.Date;

public class Reading {

    int id;
    Double Reading;
    Date Time;


    public Reading() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getReading() {
        return Reading;
    }

    public void setReading(Double reading) {
        Reading = reading;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }
}
