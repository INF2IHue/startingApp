package com.example.martijn.startingapp.Library;

import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

/**
 * Created by Martijn on 11-3-2015.
 */
public class LampState
{

    public LampState()
    {
    }

    /**
     * turn the lights on
     * @return lightstate to where to set the lamp
     */
    public PHLightState aan()
    {
        PHLightState state = new PHLightState();
        state.setOn(true);
        return state;
    }

    /**
     * turn the lights of
     * @return lightstate to where to set the lamp
     */
    public PHLightState uit()
    {
        PHLightState state = new PHLightState();
        state.setOn(false);
        return state;
    }

    /**
     * specify to which way to set the light
     * @return lightstate to where to set the lamp
     * @param colorMode which mode to work with (huesaturation, ct, none, xy)
     */
    public PHLightState setColorMode(String colorMode)
    {
        PHLightState state = new PHLightState();
        if(colorMode.equals("huesaturation"))
        {
            state.setColorMode(PHLight.PHLightColorMode.COLORMODE_HUE_SATURATION);
        }
        else if (colorMode.equals("ct"))
        {
            state.setColorMode(PHLight.PHLightColorMode.COLORMODE_CT);
        }
        else if (colorMode.equals("none"))
        {
            state.setColorMode(PHLight.PHLightColorMode.COLORMODE_NONE);
        }
        else if (colorMode.equals("xy"))
        {
            state.setColorMode(PHLight.PHLightColorMode.COLORMODE_XY);
        }
        else
        {
            state.setColorMode(PHLight.PHLightColorMode.COLORMODE_UNKNOWN);
        }
        return state;
    }

    /**
     * turn the lights on
     * @return lightstate to where to set the lamp
     * @param effect effect to show on the lamp (colorloop, none)
     */
    public PHLightState setEffect(String effect)
    {
        PHLightState state = new PHLightState();
        if(effect.equals("colorloop"))
        {
            state.setEffectMode(PHLight.PHLightEffectMode.EFFECT_COLORLOOP);
        }
        else if(effect.equals("none"))
        {
            state.setEffectMode(PHLight.PHLightEffectMode.EFFECT_NONE);
        }
        else
        {
            state.setEffectMode(PHLight.PHLightEffectMode.EFFECT_UNKNOWN);
        }
        return state;
    }

    /**
     *
     * set an alert (flashing the light)
     * @param alert specify to how to flash the light (lselect = every second, select = once, none = no flashing)
     * @return lightstate to where to set the lamp
     */
    public PHLightState setAlert(String alert)
    {
        //set a new lightstate
        PHLightState state = new PHLightState();
        //make the light flash every second
        if(alert.equals("lselect"))
        {
            state.setAlertMode(PHLight.PHLightAlertMode.ALERT_LSELECT);
        }
        //make the light flash once
        else if(alert.equals("select"))
        {
            state.setAlertMode(PHLight.PHLightAlertMode.ALERT_SELECT);
        }
        else if(alert.equals("none"))
        {
            state.setAlertMode(PHLight.PHLightAlertMode.ALERT_NONE);
        }
        else
        {
            state.setAlertMode(PHLight.PHLightAlertMode.ALERT_UNKNOWN);
        }
        return state;
    }

    /**
     * set the lamp to a specific color
     * @param hue value of the hue lamp (min = 0, max = 65280, red = 0 or 65280, yellow = 12750, green = 25500 or 36210, blue = 46920, pink = 56100)
     * @return lightstate to where to set the lamp
     */
    public PHLightState setHue(int hue)
    {
        //set a new lightstate
        PHLightState state = new PHLightState();
        state.setHue(hue);
        return state;
    }


    /**
     * set the saturation of the light
     * @param saturate value of the saturation (min = 0, max = 254)
     * @return lightstate to where to set the lamp
     */
    public PHLightState setSaturation(int saturate)
    {
        //set a new lightstate
        PHLightState state = new PHLightState();
        state.setSaturation(saturate);
        return state;
    }

    /**
     * set the brightness of the light
     * @param brightness value of the brightness (min = 0, max = 254)
     * @return lightstate to where to set the lamp
     */
    public PHLightState setBrightness(int brightness)
    {
        //set a new lightstate
        PHLightState state = new PHLightState();
        state.setBrightness(brightness);
        return state;
    }

    /**
     * Set the light with x and y values
     * @param x xvalue
     * @param y yvalue
     * @return  lightstate to where to set the lamp
     */
    public PHLightState setXY(Float x, Float y)
    {
        //set a new lightstate
        PHLightState state = new PHLightState();
        state.setX(x);
        state.setY(y);

        return state;
    }
}
