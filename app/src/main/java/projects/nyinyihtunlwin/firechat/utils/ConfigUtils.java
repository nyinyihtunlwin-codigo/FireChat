package projects.nyinyihtunlwin.firechat.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dell on 12/24/2017.
 */

public class ConfigUtils {

    private static final String KEY_CURRENT_USER = "KEY_CURRENT_USER";

    private SharedPreferences mSharedPreferences;

    public ConfigUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE);
    }

    public void saveCurrentUserId(String curentUserId) {
        mSharedPreferences.edit().putString(KEY_CURRENT_USER, curentUserId).apply();
    }

    public String loadCurrentUserId() {
        return mSharedPreferences.getString(KEY_CURRENT_USER, "");
    }
}
