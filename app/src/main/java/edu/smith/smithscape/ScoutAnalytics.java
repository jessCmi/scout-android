package edu.smith.smithscape;

import android.content.Context;

/**
 * This is the analytics class that will handle all analytics events and ensure that we do not
 * track users who are opted out.
 * Created by ezturner on 12/9/16.
 */

public class ScoutAnalytics {

    private static ScoutAnalytics instance;

    public static ScoutAnalytics getInstance(){
        return instance;
    }


    public ScoutAnalytics(Context context){
        instance = this;
    }

    private boolean isOptedOut(){
        return Scout.getInstance().getPreferences().isOptedOut();
    }


}
