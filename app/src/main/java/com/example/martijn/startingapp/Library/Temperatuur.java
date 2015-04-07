package com.example.martijn.startingapp.Library;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Temperatuur
{
    // Variables
    private int temperatuur;

    // Constructor
    public Temperatuur()
    {
        new GetAllInfo().execute(new TempConnect());
    }

    // Method to return the temperature
    public int getTemperatuur()
    {
        return temperatuur;
    }


    private void setTemp(String temp)
    {
        temperatuur = Integer.valueOf(temp);
    }
    // Method to get the temperature from the database
    public void setTextToTextView(JSONArray jsonArray){
        Log.d("asdf","temp failed")    ;
        String s = "";
        for (int i=0;i<jsonArray.length();i++){
            Log.d("asdf","temp failed4")    ;
            JSONObject json = null;
            try{
                json = jsonArray.getJSONObject(i);
                Log.d("asdf","temp failed6")    ;
                s= json.getString("temperatuur");
                Log.d("asdf","temp failed")    ;

            } catch (JSONException e){
                e.printStackTrace();
                Log.d("asdf","temp failed2")    ;
            }
            Log.d("asdf","temp failed8")    ;
        }
        setTemp(s);
        Log.d("asdf","" + temperatuur)    ;

    }

    private class GetAllInfo extends AsyncTask<TempConnect,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(TempConnect... params) {

            //It is executed on background thread

            return params[0].GetAllInfo();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {

            setTextToTextView(jsonArray);
        }
    }
}


