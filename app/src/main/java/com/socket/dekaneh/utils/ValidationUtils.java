package com.socket.dekaneh.utils;

public class ValidationUtils {

    public static String validatePhoneNumber(String phoneNumber) {

        String code = "00963";

        if (phoneNumber.startsWith(code)){
            return phoneNumber;
        }else if (phoneNumber.startsWith("09")) {
            return code + phoneNumber.substring(1);
        }else if (phoneNumber.startsWith("9")) {
            return code + phoneNumber;
        }

        return phoneNumber;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.replaceAll("[^0-9]", "").length() >= 9) {
            return true;
        }
        return false;
    }

}
