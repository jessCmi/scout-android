package edu.smith.smithscape;

import android.app.Application;

import edu.smith.smithscape.services.TurbolinksSessionManager;
import edu.smith.smithscape.utils.UserPreferences;

/**
 * Created by ezturner on 8/23/16.
 */
public class Scout extends Application {

    private static Scout instance;

    public static Scout getInstance(){
        return instance;
    }

    private UserPreferences userPreferences;
    private TurbolinksSessionManager sessionManager;
    private ScoutAnalytics scoutAnalytics;

    @Override
    public void onCreate(){
        super.onCreate();
        new UserPreferences(getApplicationContext());
        instance = this;
        sessionManager = new TurbolinksSessionManager();
        userPreferences = new UserPreferences(this);

        scoutAnalytics = ScoutAnalytics.getInstance();
        if(scoutAnalytics == null)
            scoutAnalytics = new ScoutAnalytics(this);

    }

    public UserPreferences getPreferences(){
        return userPreferences;
    }

    public TurbolinksSessionManager getTurbolinksManager(){
        return sessionManager;
    }

}
