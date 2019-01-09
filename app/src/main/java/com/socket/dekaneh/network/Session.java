package com.socket.dekaneh.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jakewharton.processphoenix.ProcessPhoenix;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.socket.dekaneh.network.model.User;

@Singleton
public class Session {

    private Context context;

    private static final String ACCESS_TOKEN = "access_token";
    private static final String USER_ID = "user_id";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String CLIENT_TYPE = "client_type";
    private static final String EMAIL = "email";
    private static final String OWNER_NAME = "owner_name";
    private static final String SHOP_NAME = "shop_name";
    private static final String USER_NAME = "user_name";
    private static final String GENDER = "gender";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String IS_LOGGED_ON = "is_logged_on";
    private static final String FIREBASE_TOKEN = "firebase_token";
    private static final String HIDE_HISTORY = "hide_history";

    @Inject
    public Session(Context context) {
        this.context = context;
    }

    public void setAccessToken(String accessToken) {
        getPreference()
                .edit()
                .putString(ACCESS_TOKEN, accessToken)
                .apply();
    }

    public String getAccessToken() {
        return getPreference().getString(ACCESS_TOKEN, "");
    }

    public void setUserId(String userId) {
        getPreference()
                .edit()
                .putString(USER_ID, userId)
                .apply();
    }

    public String getUserId() {
        return getPreference().getString(USER_ID, "");
    }

    public void setPhoneNumber(String phoneNumber) {
        getPreference()
                .edit()
                .putString(PHONE_NUMBER, phoneNumber)
                .apply();
    }

    public String getPhoneNumber() {
        return getPreference().getString(PHONE_NUMBER, "");
    }

    public void setClientType(String phoneNumber) {
        getPreference()
                .edit()
                .putString(CLIENT_TYPE, phoneNumber)
                .apply();
    }

    public String getClientType() {
        return getPreference().getString(CLIENT_TYPE, "");
    }


    public void setEmail(String email) {
        getPreference()
                .edit()
                .putString(EMAIL, email)
                .apply();
    }

    public String getEmail() {
        return getPreference().getString(EMAIL, "");
    }

    public void setOwnerName(String ownerName) {
        getPreference()
                .edit()
                .putString(OWNER_NAME, ownerName)
                .apply();
    }

    public String getOwnerName() {
        return getPreference().getString(OWNER_NAME, "");
    }

    public void setShopName(String shopName) {
        getPreference()
                .edit()
                .putString(SHOP_NAME, shopName)
                .apply();
    }

    public String getShopName() {
        return getPreference().getString(SHOP_NAME, "");
    }

    public void setUserName(String userName) {
        getPreference()
                .edit()
                .putString(USER_NAME, userName)
                .apply();
    }

    public String getUserName() {
        return getPreference().getString(USER_NAME, "");
    }

    public void setGender(String gender) {
        getPreference()
                .edit()
                .putString(GENDER, gender)
                .apply();
    }

    public String getGender() {
        return getPreference().getString(GENDER, "");
    }

    public void setLatitude(String latitude) {
        getPreference()
                .edit()
                .putString(LATITUDE, latitude)
                .apply();
    }

    public String getLatitude() {
        return getPreference().getString(LATITUDE, "");
    }


    public void setLongitude(String longitude) {
        getPreference()
                .edit()
                .putString(LONGITUDE, longitude)
                .apply();
    }

    public String getLongitude() {
        return getPreference().getString(LONGITUDE, "");
    }

    public void setLoggedOn(boolean loggedOn) {
        getPreference()
                .edit()
                .putBoolean(IS_LOGGED_ON, loggedOn)
                .apply();
    }

    public boolean isLoggedOn() {
        return getPreference().getBoolean(IS_LOGGED_ON, false);
    }


    public void setFirebaseToken(String token) {
        getPreference()
                .edit()
                .putString(FIREBASE_TOKEN, token)
                .apply();
    }

    public String getFirebaseToken() {
        return getPreference().getString(FIREBASE_TOKEN, "");
    }


    public void setHideHistory(boolean hideHistory) {
        getPreference()
                .edit()
                .putBoolean(HIDE_HISTORY, hideHistory)
                .apply();
    }

    public boolean getHideHistory() {
        return getPreference().getBoolean(HIDE_HISTORY, false);
    }

    public void setUser(User user, String accessToken) {
        setAccessToken(accessToken);
        setUserId(user.getId());
        setEmail(user.getEmail());
        setGender(user.getGender());
        setUserName(user.getUsername());
        setPhoneNumber(user.getPhoneNumber());
        setOwnerName(user.getOwnerName());
        setShopName(user.getShopName());
        setLoggedOn(true);
        setHideHistory(user.isHideHistory());
        setClientType(user.getClientType().toString());
        if (user.getLocationPoint() != null) {
            setLatitude(user.getLocationPoint().getLat());
            setLongitude(user.getLocationPoint().getLng());
        }
    }

    public void setUser(User user) {
        setUserId(user.getId());
        setEmail(user.getEmail());
        setGender(user.getGender());
        setUserName(user.getUsername());
        setPhoneNumber(user.getPhoneNumber());
        setOwnerName(user.getOwnerName());
        setShopName(user.getShopName());
        setLoggedOn(true);
        setHideHistory(user.isHideHistory());
        setClientType(user.getClientType().toString());
        if (user.getLocationPoint() != null) {
            setLatitude(user.getLocationPoint().getLat());
            setLongitude(user.getLocationPoint().getLng());
        }
    }

    public void logout() {
        setLoggedOn(false); // to make sure :P
        getPreference().edit().clear().apply();
        ProcessPhoenix.triggerRebirth(context);
    }

    private SharedPreferences getPreference() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public User getUser() {
        return new User(
                getUserId(),
                getPhoneNumber(),
                getEmail(),
                getClientType(),
                getOwnerName(),
                getShopName(),
                getHideHistory()
        );
    }

}
