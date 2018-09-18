package brain_socket.com.dekaneh.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import brain_socket.com.dekaneh.network.model.User;

@Singleton
public class Session {

    private Context context;

    private static final String ACCESS_TOKEN = "access_token";
    private static final String USER_ID = "user_id";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIL = "email";
    private static final String OWNER_NAME = "owner_name";
    private static final String SHOP_NAME = "shop+name";
    private static final String USER_NAME = "user_name";
    private static final String GENDER = "gender";

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

    public void setUser(User user, String accessToken) {
        setAccessToken(accessToken);
        setUserId(user.getId());
        setEmail(user.getEmail());
        setGender(user.getGender());
        setUserName(user.getUsername());
        setPhoneNumber(user.getPhoneNumber());
        setOwnerName(user.getOwnerName());
        setShopName(user.getShopName());
    }

    private SharedPreferences getPreference() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isLoggedOn() {
        return !getAccessToken().equals("");
    }
}
