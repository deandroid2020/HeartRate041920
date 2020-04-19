package com.example.heartrate2020.Model;


public class Pationt {

    private int Pationt_ID;
    private String Pation_Name;
    private int Patientage;
    private String PAddress;
    private String Pnationality;
    private String EmrName;
    private int EmrNumber;
    private String DocID;

    public String getDocID() {
        return DocID;
    }

    public void setDocID(String docID) {
        DocID = docID;
    }

    public int getPationt_ID() {
        return Pationt_ID;
    }

    public void setPationt_ID(int pationt_ID) {
        Pationt_ID = pationt_ID;
    }

    public String getPation_Name() {
        return Pation_Name;
    }

    public void setPation_Name(String pation_Name) {
        Pation_Name = pation_Name;
    }

    public int getPatientage() {
        return Patientage;
    }

    public void setPatientage(int patientage) {
        Patientage = patientage;
    }

    public String getPAddress() {
        return PAddress;
    }

    public void setPAddress(String PAddress) {
        this.PAddress = PAddress;
    }

    public String getPnationality() {
        return Pnationality;
    }

    public void setPnationality(String pnationality) {
        Pnationality = pnationality;
    }

    public String getEmrName() {
        return EmrName;
    }

    public void setEmrName(String emrName) {
        EmrName = emrName;
    }

    public int getEmrNumber() {
        return EmrNumber;
    }

    public void setEmrNumber(int emrNumber) {
        EmrNumber = emrNumber;
    }


    public Pationt(int pationt_ID, String pation_Name, int patientage, String PAddress, String pnationality, String emrName, int emrNumber) {
        Pationt_ID = pationt_ID;
        Pation_Name = pation_Name;
        Patientage = patientage;
        this.PAddress = PAddress;
        Pnationality = pnationality;
        EmrName = emrName;
        EmrNumber = emrNumber;
    }




    public Pationt() {
    }
}
