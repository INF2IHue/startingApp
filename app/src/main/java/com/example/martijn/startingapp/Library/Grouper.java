package com.example.martijn.startingapp.Library;

import android.util.Log;

import com.philips.lighting.hue.listener.PHGroupListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResource;
import com.philips.lighting.model.PHGroup;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLight;

import java.util.List;
import java.util.Map;

/**
 * Created by Martijn on 18-3-2015.
 */
public class Grouper
{
    //fiel to store the current bridge.
    private PHBridge bridge;
    //field to store the group listener
    private PHGroupListener gl;

    /**
     * Constructor of the Grouper Class
     */
    public Grouper()
    {
        //get the current bridge
        bridge = PHHueSDK.getInstance().getSelectedBridge();
        //create a new listener
        gl = new PHGroupListener() {
            /**
             * When a group is created
             * @param phGroup Group where to listen to
             */
            @Override
            public void onCreated(PHGroup phGroup)
            {
                Log.d("Grouper", "Creation Succesful");
            }

            /**
             * When receiving group details.
             * @param phGroup Group where to listen to
             */
            @Override
            public void onReceivingGroupDetails(PHGroup phGroup)
            {
                Log.d("Grouper", "Retrieval of GroupDetails was a succes");
            }

            /**
             * When receiving bridge resources
             * @param phBridgeResources List of bridgeresources
             */
            @Override
            public void onReceivingAllGroups(List<PHBridgeResource> phBridgeResources)
            {
                Log.d("Grouper", "Retrieval of groups was a succes");
            }

            /**
             * When the creation of a group was succesful
             */
            @Override
            public void onSuccess()
            {
                Log.d("Grouper", "Group was succesfull");
            }

            /**
             * When there is an error
             * @param i
             * @param s
             */
            @Override
            public void onError(int i, String s)
            {
                Log.d("Grouper", "Error encountered in the grouper");
            }

            /**
             * When the group is updated
             * @param stringStringMap
             * @param phHueErrors
             */
            @Override
            public void onStateUpdate(Map<String, String> stringStringMap, List<PHHueError> phHueErrors)
            {
                Log.d("Grouper", "Group updated");
            }
        };

    }


    /**
     * Create a new group
     * @param lightIDs List with lights to add to a group
     * @param groupName name of the group
     */
    public void newGroup(List<String> lightIDs, String groupName)
    {
        bridge.createGroup(groupName, lightIDs, null);
    }

    /**
     * Get all groups
     * @return groups
     */
    public Map<String, PHGroup> getGroups()
    {
        return bridge.getResourceCache().getGroups();
    }

    /**
     * Retrieve all lights
     * @return lights
     */
    public List<PHLight> getLights()
    {
        return bridge.getResourceCache().getAllLights();
    }

    /**
     * Delete a grouop
     * @param groupName Name of the groups
     */
    public void deleteGroup(String groupName)
    {
        bridge.deleteGroup(groupName, gl);
    }
}
