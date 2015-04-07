package com.example.martijn.startingapp.Library;

import com.philips.lighting.hue.listener.PHSceneListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLightState;
import com.philips.lighting.model.PHScene;

import java.util.List;
import java.util.Map;

/**
 * Created by Martijn Jonkers on 20-3-2015.
 */
public class Scene {

    // Init Fields
    private String uniqueID;
    private PHSceneListener sl;

    // Construct a new scene with a name, an unique ID and two light id's
    public Scene(String sceneName, String uniqueID, List<String> lightIdentifiers)
    {

        // Get the current bridge its connected to and create a new scene and give it a name.
        // Also override the current default unique ID with the one give when the class was created
        PHBridge bridge = PHHueSDK.getInstance().getSelectedBridge();
        PHScene scene = new PHScene();
        scene.setName(sceneName);
        this.uniqueID = uniqueID;

        sl =  new PHSceneListener() {
            @Override
            public void onScenesReceived(List<PHScene> phScenes) {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onStateUpdate(Map<String, String> stringStringMap, List<PHHueError> phHueErrors) {

            }
        };

        // Set the unique ID and set the currentLightidentifiers with the list we created.
        scene.setSceneIdentifier(uniqueID);
        scene.setLightIdentifiers(lightIdentifiers);
        // Standard listener
        bridge.saveSceneWithCurrentLightStates(scene, sl);
    }

    // This is used to modify previously created scenes.
    public void modifyScene(String uniqueID, String lightID1, PHLightState state)
    {
        // Get the bridge its currently connected to.
        PHBridge bridge = PHHueSDK.getInstance().getSelectedBridge();

        // Save the lightstate and update the list
        bridge.saveLightState(state, lightID1, uniqueID, sl);
    }
}
