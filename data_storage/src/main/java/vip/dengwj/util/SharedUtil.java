package vip.dengwj.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtil {
    public static SharedUtil mShared;

    private SharedPreferences preferences;

    public static SharedUtil getInstance(Context context) {
        if (mShared == null) {
            mShared = new SharedUtil();
            mShared.preferences = context.getSharedPreferences("shopping", Context.MODE_PRIVATE);
        }
        return mShared;
    }

    public void setBoolean(String key, boolean val) {
        preferences.edit()
                .putBoolean(key, val)
                .apply();
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        return preferences.getBoolean(key, defaultVal);
    }
}
