package com.wheic.cleanurge.SharedPrefManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.wheic.cleanurge.ModelResponse.User;

public class SharedPrefManager {
    private static String SHARED_PREF_NAME = "SessionStore";
    private SharedPreferences sharedPreferences;
    private Context context;
    private SharedPreferences.Editor editor;


    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveSession(String token){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("Token", token);
        editor.apply();
    }

    public boolean isLoggedIn(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString("Token", null) == null){
            return false; // logged out
        }else{
            return true; // logged in
        }
    }

    public User getUser(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(sharedPreferences.getString("UserName", null),
                sharedPreferences.getString("UserEmail", null),
                sharedPreferences.getInt("UserPhone", -1),
                sharedPreferences.getString("UserAddress", null));
//        return new User(sharedPreferences.getString("UserID", null));
    }

    public void setUser(User user){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        editor.putString("UserID", user.getId());
        editor.putString("UserName", user.getName());
        editor.putString("UserEmail", user.getEmail());
        editor.putInt("UserPhone", user.getPhone());
        editor.putString("UserAddress", user.getAddress());
        editor.apply();
    }

    public String getToken(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("Token", null);
    }

    public void logOut(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
