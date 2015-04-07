package com.example.martijn.startingapp.Library;

import android.util.Log;

import com.philips.lighting.hue.listener.PHBridgeConfigurationListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeConfiguration;
import com.philips.lighting.model.PHHueError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Martijn on 26-3-2015.
 */
public class GeneralSetup
{
    //field to store the lightgroup
    private List <String> lightGroup;
    private PHBridgeConfigurationListener tl;
    //field to store the SDK
    private PHHueSDK phHueSDK;
    //field to store the bridge
    private PHBridge bridge;

    public GeneralSetup()
    {
        phHueSDK = PHHueSDK.getInstance();
        bridge = phHueSDK.getSelectedBridge();

        tl = new PHBridgeConfigurationListener()
        {
            @Override
            public void onReceivingConfiguration(PHBridgeConfiguration phBridgeConfiguration) {
                Log.d("config", "receivingConfig");
            }

            @Override
            public void onSuccess() {
                Log.d("config", "success");
            }

            @Override
            public void onError(int i, String s) {
                Log.d("config", "error");
            }

            @Override
            public void onStateUpdate(Map<String, String> stringStringMap, List<PHHueError> phHueErrors) {
                Log.d("config", "state update");
            }
        };
    }

    /**
     * Update the time on the bridge
     */
    public void updateTime()
    {
        //set time in simpleDateformat
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        //set to UTC
        time.getCalendar().set(Calendar.HOUR, Calendar.HOUR - 1);
        String currentDay = day.format(new Date());
        String currentTime = time.format(new Date());

        PHBridgeConfiguration bridgeConfiguration = new PHBridgeConfiguration();
        Log.d("config", "new bridgeConfig");
        //bridgeConfiguration.setTime("2015-03-24T13:10:57");
        bridgeConfiguration.setTime(currentDay + "T" + currentTime);
        Log.d("config", "time geset");

        bridge.updateBridgeConfigurations(bridgeConfiguration, tl);

        Log.d("config", "tijd geset");
    }

    /**
     * Create a new LightList to store the lights
     */
    public void createLightList()
    {
        lightGroup = new ArrayList<String>();
    }

    /**
     * Add a light to the lightlist
     * @param lightID lightID
     */
    public void addToLightList(String lightID)
    {
        lightGroup.add(lightID);
    }

    /**
     * retrieve the lightlist
     * @return list with all lights in the list
     */
    public  List<String> getLightList()
    {
        return lightGroup;
    }

}
