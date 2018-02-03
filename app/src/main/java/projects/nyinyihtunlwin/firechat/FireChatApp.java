package projects.nyinyihtunlwin.firechat;

import android.app.Application;

import projects.nyinyihtunlwin.firechat.utils.ConfigUtils;

/**
 * Created by Dell on 1/29/2018.
 */

public class FireChatApp extends Application {

    public static final String LOG = "FireChat";

    private static ConfigUtils mConfigUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        mConfigUtils = new ConfigUtils(getApplicationContext());
    }

    public static ConfigUtils getConfigUtils() {
        return mConfigUtils;
    }
}
