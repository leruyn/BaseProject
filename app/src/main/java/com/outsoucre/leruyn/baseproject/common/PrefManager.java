package com.outsoucre.leruyn.baseproject.common;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LeRuyn on 7/3/2018.
 */
public class PrefManager {


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "Pref_Kranse";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String USERID = "userID";
    private static final String NAME = "userName";
    private static final String EMAIL = "email";
    private static final String PHONE = "phoneNumber";
    private static final String TOKEN = "tokenID";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void setInfoUser(String user_id, String name, String email, String phone, String token) {
        editor.putString(USERID, user_id);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(PHONE, phone);
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getTokenID() {
        return pref.getString(TOKEN, "");
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}