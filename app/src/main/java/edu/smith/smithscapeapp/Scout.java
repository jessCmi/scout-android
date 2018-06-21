package edu.smith.smithscapeapp;

import android.app.Application;
import edu.smith.smithscapeapp.services.TurbolinksSessionManager;
import edu.smith.smithscapeapp.utils.UserPreferences;

/**
 * Created by ezturner on 8/23/16.
 */
public class Scout extends Application {

    private static final String LOG_TAG = Scout.class.getSimpleName();
    private static Scout instance;

    public static Scout getInstance(){
        return instance;
    }

    private UserPreferences userPreferences;
    private TurbolinksSessionManager sessionManager;

    @Override
    public void onCreate(){
        super.onCreate();
        new UserPreferences(getApplicationContext());
        instance = this;
        sessionManager = new TurbolinksSessionManager();
        userPreferences = new UserPreferences(this);

    }

    public UserPreferences getPreferences(){
        return userPreferences;
    }

    public TurbolinksSessionManager getTurbolinksManager(){
        return sessionManager;
    }

}
