package ua.dream.chat.utils.validate;

import java.util.Arrays;
import java.util.regex.Pattern;

public final class CheckUserData {

    public static final int MAX_USER_NAME_LENGTH = 16;
    public static final int MIN_USER_NAME_LENGTH = 4;
    public static final int MAX_PASSWORD_LENGTH = 32;
    public static final int MIN_PASSWORD_LENGTH = 4;
    public static final int MAX_DISPLAY_NAME_LENGTH = 32;
    public static final int MIN_DISPLAY_NAME_LENGTH = 1;
    public static final int MAX_MESSAGE_LENGTH = 128;


    private CheckUserData() {

    }

    public static void checkPassword(byte[] password , byte[] dataPassword) {
        if(!Arrays.equals(password , dataPassword)) {
            throw new IllegalArgumentException("wrong user password");
        }
    }

    public static void checkMessage(String message) {
        if(message.length() > MAX_MESSAGE_LENGTH) throw new IllegalArgumentException("Message length is over " + MAX_MESSAGE_LENGTH + " symbols");
    }

    public static void checkUserName(String name) {
        if(name.length() > MAX_USER_NAME_LENGTH) {
            throw new IllegalArgumentException("User name length is over " + MAX_USER_NAME_LENGTH);
        }
        if(name.length() < MIN_USER_NAME_LENGTH) {
            throw new IllegalArgumentException("User name length is lover " + MIN_USER_NAME_LENGTH);
        }
        if(!Pattern.matches("[A-Za-z]([\\\\.A-Za-z0-9_]{1,14})([A-Za-z0-9])" , name)) {
            throw new IllegalArgumentException("Invalid regex login");
        }
    }

    public static void checkUserPassword(String password) {
        if(password.length() > MAX_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("password length is over " + MAX_PASSWORD_LENGTH);
        }
        if(password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("password length is lover " + MIN_PASSWORD_LENGTH);
        }
        if(password.contains(" ")) throw new IllegalArgumentException("password contains empty chars");
    }

    public static void checkDisplayName(String displayName) {
        if(displayName.equalsIgnoreCase("")) return;
        if(displayName.length() > MAX_DISPLAY_NAME_LENGTH) {
            throw new IllegalArgumentException("display name length is over " + MAX_USER_NAME_LENGTH);
        }
        if(displayName.length() < MIN_DISPLAY_NAME_LENGTH) {
            throw new IllegalArgumentException("display name length is lover " + MIN_USER_NAME_LENGTH);
        }
        if(!Pattern.matches("[A-Za-z]([\\\\.A-Za-z0-9_]{1,15})" , displayName)) {
            throw new IllegalArgumentException("Invalid regex login");
        }
    }


}
