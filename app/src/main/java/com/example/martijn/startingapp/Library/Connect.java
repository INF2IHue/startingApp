package com.example.martijn.startingapp.Library;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.util.Log;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHMessageType;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHHueParsingError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Connect extends ListActivity {
    // Init fields
    private PHHueSDK phHueSDK;
    private String appName;
    private String userName;
    private String IPAdress;
    private String macAdress;

    // Constructor
    public Connect() {
        // Set default appname, username, ipadress and macadress
        appName = "DefaultApp";
        userName = "newdeveloper";
        IPAdress = "0.0.0.0";
        macAdress = "00:00:00:00:00:00";
    }

    // Start the connection to the bridges
    public void startConnection() {
        // Get the phhuesdk instance
        this.phHueSDK = PHHueSDK.getInstance();

        // Set the app name
        phHueSDK.setAppName(appName);
        phHueSDK.setDeviceName(android.os.Build.MODEL);

        // Init the bridge Listener
        phHueSDK.getNotificationManager().registerSDKListener(listener);

        // Find bridges on the network
        findBridges();

        // Connect to the found bridge with an IP adress and an username
        PHAccessPoint accessPoint = new PHAccessPoint();

        accessPoint.setIpAddress(IPAdress);
        accessPoint.setUsername(userName);

        Log.d("Connect", IPAdress);
        Log.d("Connect", userName);

        // Check if you are already connected to a bridge
        if (!phHueSDK.isAccessPointConnected(accessPoint)) {
            phHueSDK.connect(accessPoint);
        }
        else
        {
            phHueSDK.connect(accessPoint);
        }
    }

    // Get a Json array with value's from the database and put it in a string
    public void setTextToTextView(JSONArray jsonArray){
        // Create a new empty string and loop through the array
        String s = "";
        for (int i=0; i<jsonArray.length(); i++){
            // Create a temporary empty json object
            JSONObject json = null;
            // Try if you can get an object from the array
            try{
                json = jsonArray.getJSONObject(i);
                // Get it from the table ipAdress
                s = s+ json.getString("ipAdress");

                // If it is not possible, print an error
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        // Set the IP adress we got from the array
        IPAdress = s;
        startConnection();
    }

    // Set the standard username to connect to the bridge
    public void setUserName(String username)
    {
        userName = username;
    }

    // Get all the info from the database in a new thread
    private class GetAllInfo extends AsyncTask<DatabaseConnect,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(DatabaseConnect... params) {

            //It is executed on background thread
            return params[0].GetAllInfo(macAdress);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            // After when we got the info from the database, make it to 1 string
            setTextToTextView(jsonArray);
        }
    }

    // Get the ip adress from the database
    public void getIpAdress(String macAdress) {
        // Send the Mac adress to the database for comparison and get the IP back
        this.macAdress = macAdress;

        // Get the IPadress from the database
        new GetAllInfo().execute(new DatabaseConnect());
    }

    // Start a bridge search
    private void findBridges() {
        phHueSDK = PHHueSDK.getInstance();
        PHBridgeSearchManager sm = (PHBridgeSearchManager) phHueSDK.getSDKService(PHHueSDK.SEARCH_BRIDGE);
        sm.search(true, true);
    }

    // Set the name of the application
    public void setNewAppName(String appName) {
        // Make the user set the name of the application
        this.appName = appName;
    }

    // Initiate a listener ( Standard Listener from PHHueSDK )
    public PHSDKListener listener = new PHSDKListener() {

        @Override
        public void onCacheUpdated(List<Integer> integers, PHBridge phBridge) {
            if (integers.contains(PHMessageType.LIGHTS_CACHE_UPDATED)) {

            }
        }

        @Override
        public void onBridgeConnected(PHBridge bridge) {
            phHueSDK.setSelectedBridge(bridge);
            phHueSDK.enableHeartbeat(bridge, PHHueSDK.HB_INTERVAL);
        }


        @Override
        public void onAuthenticationRequired(PHAccessPoint phAccessPoint) {
            phHueSDK.startPushlinkAuthentication(phAccessPoint);
        }

        @Override
        public void onAccessPointsFound(List<PHAccessPoint> phAccessPoints) {

        }

        @Override
        public void onError(int i, String s) {

        }

        @Override
        public void onConnectionResumed(PHBridge phBridge) {

        }

        @Override
        public void onConnectionLost(PHAccessPoint phAccessPoint) {

        }

        @Override
        public void onParsingErrors(List<PHHueParsingError> phHueParsingErrors) {

        }
    };
}