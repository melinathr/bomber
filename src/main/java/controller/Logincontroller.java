package controller;

import model.App;
import model.Result;
import model.User;

import java.util.Objects;

public class Logincontroller {
    public Result checkInfo(String username, String password) {
        if (!isUserExit(username)) return new Result
                (false, "invalid username, there is no user with this username");
        if (!Objects.requireNonNull(getUserByUsername(username)).getPassword().equals(password))
            return new Result(false, "password not match");

        App.setLoggedInUser(getUserByUsername(username));
        return new Result(true, "login Succesful");
    }

    public Result loginAsGuest(String username){
        if (!isUsernameValid(username)) return new Result(false, "invalid username, please enter valid username");
        if (isUserExit(username))
            return new Result(false, "this username had already used, please enter another username");

        App.setIsGuest(true);
        User guest = new User(username, null);
        App.setLoggedInUser(guest);
        return new Result(true, "login Succesful");
    }

    private boolean isUserExit(String username) {
        for (User user : User.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    private User getUserByUsername(String username) {
        for (User user : User.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private boolean isUsernameValid(String username) {
        if (username.isEmpty()) return false;
        if (!username.matches("[a-zA-Z_]*")) return false;
        return true;
    }
}

