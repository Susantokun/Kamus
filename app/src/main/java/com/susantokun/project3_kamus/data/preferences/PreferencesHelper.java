package com.susantokun.project3_kamus.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Susantokun on 11 November 2018
 */

public class PreferencesHelper implements IPreferencesHelper {
    private final SharedPreferences mPrefs ;
    private static final String KEY_FIRST_RUN = "first_run";
    private Context context ;

    public PreferencesHelper(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    @Override
    public void setFirstRun(boolean isFirstRun) {
        mPrefs.edit().putBoolean(KEY_FIRST_RUN, isFirstRun).apply();
    }

    @Override
    public Boolean getFirstRun() {
        return mPrefs.getBoolean(KEY_FIRST_RUN, true);
    }
}
