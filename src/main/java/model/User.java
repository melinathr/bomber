package model;

import javafx.scene.image.Image;
import view.Game;
import view.Main;

import java.util.ArrayList;
import java.util.Random;

public class User {
    private String username;
    private String password;
    private GameModel game;
    private GameModel lastGame;
    private int bestGameByKill = 0;
    private int bestGameByLevel = 0;
    private double bestGameByAccuracy = 0;
    private int indexBackground = 1;

    private Image avatar = new Image(String.valueOf(Main.class.getResource("/images/Avatar"+getRandomInt()+".png")));

    private int getRandomInt() {
        Random rand = new Random();
        int randomInt = rand.nextInt(1,5);
        return randomInt;
    }

    private static ArrayList<User> allUsers = new ArrayList<>();
    private int level = 2;
    private boolean colorful = true;
    private boolean mute = false;
    private boolean wasd = false;
    private boolean isGray = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        allUsers.add(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public GameModel getGame() {
        return game;
    }

    public void setGame(GameModel game) {
        this.game = game;
    }
    public int getIndexBackground() {
        return this.indexBackground;
    }

    public void setIndexBackground(int indexBackground) {
        this.indexBackground = indexBackground;
    }

    public GameModel getLastGame() {
        return lastGame;
    }

    public void setLastGame(GameModel lastGame) {
        this.lastGame = lastGame;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isColorful() {
        return colorful;
    }

    public void setColorful(boolean colorful) {
        this.colorful = colorful;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public boolean isWasd() {
        return wasd;
    }

    public void setWasd(boolean wasd) {
        this.wasd = wasd;
    }

    public boolean isGray() {
        return isGray;
    }

    public void setGray(boolean gray) {
        isGray = gray;
    }

    public int getBestGameByKill() {
        return bestGameByKill;
    }

    public void setBestGameByKill(int bestGameByKill) {
        this.bestGameByKill = bestGameByKill;
    }

    public int getBestGameByLevel() {
        return bestGameByLevel;
    }

    public void setBestGameByLevel(int bestGameByLevel) {
        this.bestGameByLevel = bestGameByLevel;
    }

    public double getBestGameByAccuracy() {
        return bestGameByAccuracy;
    }

    public void setBestGameByAccuracy(double bestGameByAccuracy) {
        this.bestGameByAccuracy = bestGameByAccuracy;
    }
}
