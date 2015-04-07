package com.example.martijn.startingapp.Library;

import android.util.Log;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.List;

/**
 * Created by Martijn on 18-2-2015.
 */
public class HueChangeState
{
    //field to store the skd
    private PHHueSDK phHueSDK;
    //field to store a list with all the lights
    private List<PHLight> myList;
    //field to store the brug
    private PHBridge brug;

    /**
     * constructor of the huechangestate
     */
    public HueChangeState()
    {
        //set the sdk and bridge
        phHueSDK = PHHueSDK.getInstance();
        brug = phHueSDK.getSelectedBridge();

    }

    /**
     * Make the light change color by executing a lightstate on a light
     * @param state lightstate to send to the light
     * @param lightNumber light to set
     */
    public void execute(PHLightState state, int lightNumber)
    {
        phHueSDK = PHHueSDK.getInstance();
        brug = phHueSDK.getSelectedBridge();

        List<PHLight> myList = brug.getResourceCache().getAllLights();
        //loop through the lamps in the list
        PHLight lamp = myList.get(lightNumber);
        Log.d("config", lamp.getName());
        brug.updateLightState(lamp, state);
    }

    /**
     * Make a group change color by executing a lightstate on a group
     * @param state  lightstate to send to the light
     * @param groupName group to set
     */
    public void executeOnGroup(PHLightState state, String groupName)
    {
        phHueSDK = PHHueSDK.getInstance();
        brug = phHueSDK.getSelectedBridge();

        brug.setLightStateForGroup(groupName, state);
    }


}
