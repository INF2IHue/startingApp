package com.example.martijn.startingapp.Library;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Martijn on 31-3-2015.
 */
public class DatabaseConnect {

    // Get all the information from the database
    public JSONArray GetAllInfo(String macAdress)
    {
        //URL for getting al info from the database
        // It refers to a PHP file on the server, which will search for the ipadress according to the macadress
        String Overzicht = "http://192.168.0.50/phpconnect/Server/Overzicht.php?variable=" + macAdress;

        //Get httpResponse from url
        //Get httEntity from http response Object
        HttpEntity httpEntity = null;
        try{

            DefaultHttpClient httpClient = new DefaultHttpClient(); //Default http client
            HttpGet httpGet = new HttpGet(Overzicht);

            HttpResponse httpResponse = httpClient.execute(httpGet);

            httpEntity = httpResponse.getEntity();
        } catch (ClientProtocolException e){

            //Signals error in http protocol
            e.printStackTrace();

            //Log errors here
        } catch (IOException e){
            e.printStackTrace();
        }

        //Convert http entity into JSON Array
        JSONArray jsonArray = null;

        if(httpEntity != null){
            try{
                // Get the response and log it
                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);
                // If not, print errors
            } catch (JSONException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        // Return the array with results
        return jsonArray;
    }
}
