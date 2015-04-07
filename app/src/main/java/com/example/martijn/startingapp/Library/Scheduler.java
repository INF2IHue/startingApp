package com.example.martijn.startingapp.Library;

import android.util.Log;

import com.philips.lighting.hue.listener.PHScheduleListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLightState;
import com.philips.lighting.model.PHSchedule;
import com.philips.lighting.model.PHSchedule.RecurringDay;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Martijn on 12-3-2015.
 */

public class Scheduler
{
    //field to store a schedule
    private PHSchedule schedule;
    //field to store the SDK
    private PHHueSDK phHueSDK;
    //field to store the listener
    private PHScheduleListener sl;
    //field to store the bridge
    private PHBridge bridge;

    /**
     * Schedule constructor
     * @param scheduleName name of the schedule
     */
    public Scheduler(String scheduleName)
    {
        // set the bridge, sdk and schedule
        phHueSDK = PHHueSDK.getInstance();
        bridge = phHueSDK.getSelectedBridge();
        schedule = new PHSchedule(scheduleName);

        //new listener for a schedule
        sl = new PHScheduleListener() {
            @Override
            /*
            When a new schedule is created
             */
            public void onCreated(PHSchedule phSchedule)
            {
                Log.d("Scheduler", "Creation Succesful");
            }

            /**
             * When the schedule is a succes
             */
            @Override
            public void onSuccess()
            {
                Log.d("Scheduler", "Scheduling was a success");
            }

            /**
             * When there is an error in the schedule
             * @param i
             * @param s
             */
            @Override
            public void onError(int i, String s) {
                Log.d("Scheduler", "Error in scheduler");
            }

            /**
             * When there is an status update in the schedule
             * @param stringStringMap
             * @param phHueErrors
             */
            @Override
            public void onStateUpdate(Map<String, String> stringStringMap, List< PHHueError > phHueErrors) {
                Log.d("Scheduler", "scheduler updated");
            }
        };
    }

    /**
     * Set the date in a schedule
     * @param datum datum/time to when the schedule is set
     */
    public void setDate(Date datum)
    {
        schedule.setDate(datum);
    }

    /**
     * set the starttime of the schedule
     * @param start set the start date/time
     */
    public void setStart(Date start)
    {
        schedule.setStartTime(start);
    }

    /**
     * Sets the lightstate in the schedule
     * @param state lightstate
     */
    public void setLightState(PHLightState state)
    {
        schedule.setLightState(state);
    }

    /**
     * sets the specific light
     * @param id lightid
     */
    public void setLight(String id)
    {
        schedule.setLightIdentifier(id);
    }

    /**
     * set the specific group
     * @param id groupid
     */
    public void setGroupID(String id)
    {
        schedule.setGroupIdentifier(id);
    }

    /**
     * set the local time so CET isn't set
     * @param localTime true or false,
     */
    public void setLocalTime(boolean localTime)
    {
        schedule.setLocalTime(localTime);
    }

    /**D
     * sets the schedule
     */
    public void setSchedule()
    {
        bridge.createSchedule(schedule, sl);
    }

    /**
     * Sets when the schedule is to return
     * @param day specify which day /  days is to return (allday, sunday, monday, tuesday, wednesday, thursday, friday, saturday, weekday, weekend)
     */
    public void setRecurringDay(String day)
    {
        switch (day) {
            case "allday":
                schedule.setRecurringDays(RecurringDay.RECURRING_ALL_DAY.getValue());
                break;
            case "sunday":
                schedule.setRecurringDays(RecurringDay.RECURRING_SUNDAY.getValue());
                break;
            case "monday":
                schedule.setRecurringDays(RecurringDay.RECURRING_MONDAY.getValue());
                break;
            case "tuesday":
                schedule.setRecurringDays(RecurringDay.RECURRING_TUESDAY.getValue());
                break;
            case "wednesday":
                schedule.setRecurringDays(RecurringDay.RECURRING_WEDNESDAY.getValue());
                break;
            case "thursday":
                schedule.setRecurringDays(RecurringDay.RECURRING_THURSDAY.getValue());
                break;
            case "friday":
                schedule.setRecurringDays(RecurringDay.RECURRING_FRIDAY.getValue());
                break;
            case "saturday":
                schedule.setRecurringDays(RecurringDay.RECURRING_SATURDAY.getValue());
                break;
            case "weekday":
                schedule.setRecurringDays(RecurringDay.RECURRING_WEEKDAYS.getValue());
                break;
            case "weekend":
                schedule.setRecurringDays(RecurringDay.RECURRING_WEEKEND.getValue());
                break;
            default:
                schedule.setRecurringDays(RecurringDay.RECURRING_NONE.getValue());
                break;
        }

    }

    /**
     * Delete the schedule.
     */
    public void deleteSchedule()
    {
        bridge.removeSchedule(schedule.getName(), sl);
    }
}
