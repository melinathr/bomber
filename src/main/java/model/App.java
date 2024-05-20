package model;

public class App {
    static private User loggedInUser = null;
    static private boolean isGuest = false;
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static boolean isIsGuest() {
        return isGuest;
    }

    public static void setIsGuest(boolean isGuest) {
        App.isGuest = isGuest;
    }
}
