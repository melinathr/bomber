package controller;

import model.App;
import model.Result;
import model.User;

public class ProfileController {
    public Result changeUsername(String newUsername) {
        if (newUsername.isEmpty()) return new Result(false, "please enter a username");

        if (newUsername.equals(App.getLoggedInUser().getUsername()))
            return new Result(false, "new name is same with current name");

        if (isUserExit(newUsername)) return new Result(false,
                "there is a User with this username, please enter another username");
        if (!isUsernameValid(newUsername))
            return new Result(false, "invalid username");


        App.getLoggedInUser().setUsername(newUsername);
        return new Result(true, "username change successfully");
    }

    public Result changePassword(String newPassword) {
        if(App.isIsGuest()) return new Result(false, "guest can't have password");
        if (newPassword.equals(App.getLoggedInUser().getPassword()))
            return new Result(false, "new password is same with current name");
        if (!isPasswordValid(newPassword))
            return new Result(false, "invalid password");


        App.getLoggedInUser().setPassword(newPassword);
        return new Result(true, "username change successfully");
    }

    public void deleteAccount(){
        User.getAllUsers().remove(App.getLoggedInUser());
        App.setLoggedInUser(null);

    }
    private boolean isUserExit(String username) {
        for (User user : User.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameValid(String username) {
        if (username.isEmpty()) return false;
        if (!username.matches("[a-zA-Z_]*")) return false;
        return true;
    }

    private boolean isPasswordValid(String password) {
        if (password.isEmpty()) return false;
        if (password.length() < 6) return false;
        if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*")) return false;
        return true;
    }
}
