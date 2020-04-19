package com.example.heartrate2020;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {

        prefs = cntx.getSharedPreferences("userInfo",0);

    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }

    public void setSaved (boolean flag)
    {
        prefs.edit().putBoolean( "Saved"  , flag).commit();
    }

    public boolean getSaved ()
    {
       return prefs.getBoolean("Saved" , false);
    }

    public void LogOut ()
    {
    SharedPreferences.Editor editor =prefs.edit();
    editor.clear();
    editor.commit();
    }

    public void setMemberId(int MemberId)
    {
        prefs.edit().putInt("MemberId" , MemberId ).commit();
    }

    public int getMemberId()
    {
        return prefs.getInt("MemberId" , 0);
    }


    public void setType (String Type)
    {
        prefs.edit().putString("Type" , Type).commit();
    }

    public String getType ()
    {
        return prefs.getString("Type" , "");
    }

    public void setName(String Name)
    {
        prefs.edit().putString("Name" ,Name ).commit();
    }

    public String getName()
    {
        return prefs.getString("Name" , "");
    }

    public void setDacId(String DacID)
    {
        prefs.edit().putString("DacId" , DacID).commit();
    }

    public String GetDacID()
    {
        return  prefs.getString(  "DacId" ,"g");
    }

}