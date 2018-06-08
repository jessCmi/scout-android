package edu.smith.smithscape;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.basecamp.turbolinks.TurbolinksSession;

import edu.smith.smithscape.services.TurbolinksSessionManager;
//import edu.smith.smithscape.utils.ScoutLocation;
import edu.smith.smithscape.utils.UserPreferences;

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
//    private ScoutLocation scoutLocation;

    @Override
    public void onCreate(){
        super.onCreate();
        new UserPreferences(getApplicationContext());
        instance = this;
        sessionManager = new TurbolinksSessionManager();
        userPreferences = new UserPreferences(this);
//        scoutLocation = new ScoutLocation(getApplicationContext());

    }

    public UserPreferences getPreferences(){
        return userPreferences;
    }

    public TurbolinksSessionManager getTurbolinksManager(){
        return sessionManager;
    }

}
