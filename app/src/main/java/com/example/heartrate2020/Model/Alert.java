package com.example.heartrate2020.Model;

import java.util.Date;

public class Alert {

    String name;
    String Hrate;
    Date timealert;
    Double Latx;
    Double Laty;
    Double CLatx;
    Double CLaty;
    String Status;



    public Alert() {


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHrate() {
        return Hrate;
    }

    public void setHrate(String hrate) {
        Hrate = hrate;
    }

    public Date getTimealert() {
        return timealert;
    }

    public void setTimealert(Date timealert) {
        this.timealert = timealert;
    }

    public Double getLatx() {
        return Latx;
    }

    public void setLatx(Double latx) {
        Latx = latx;
    }

    public Double getLaty() {
        return Laty;
    }

    public void setLaty(Double laty) {
        Laty = laty;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Double getCLatx() {
        return CLatx;
    }

    public void setCLatx(Double CLatx) {
        this.CLatx = CLatx;
    }

    public Double getCLaty() {
        return CLaty;
    }

    public void setCLaty(Double CLaty) {
        this.CLaty = CLaty;
    }
}
