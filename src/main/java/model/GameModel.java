package model;

import javafx.animation.Timeline;
import javafx.scene.media.MediaPlayer;

public class GameModel {
    private int numOfMusic = 1;
    private int indexCarPic = 1;
    private int indexMotorPic = 1;
    private int indexTankPic = 1;
    private int indexCloudPic = 1;
    private int kill = 0;
    private int allShoot = 0;
    private int successfulShoot = 0;
    private int numberOfRadioActive = 0;
    private int numberOfCluster = 0;
    private int wave = 1;
    private boolean isPause = false;
    private boolean cactus1 = false;
    private boolean cactus2 = false;
    private boolean cactus3 = false;
    private boolean hasIcy = false;
    private int tankKills = 0;
    private double accuracy;
    private int levelAndKill;
    private int progressKill = 0;
    private MediaPlayer mediaPlayer;
    public Timeline timeline1;
    public Timeline timeline2;
    public Timeline timeline3;
    public Timeline timeline4;
    public Timeline timeline5;
    public Timeline timeline6;
    public Timeline timeline7;
    public Timeline timeline8;
    public Timeline timeline9;
    public Timeline timeline10;
    private String endGameSituation;

    public int getIndexCarPic() {
        return indexCarPic;
    }

    public void addIndexCarPic() {
        indexCarPic++;
        if (indexCarPic == 4) indexCarPic = 1;
    }

    public int getIndexMotorPic() {
        return indexMotorPic;
    }

    public void addIndexMotorPic() {
        indexMotorPic++;
        if (indexMotorPic == 3) indexMotorPic = 1;
    }

    public int getIndexTankPic() {
        return indexTankPic;
    }

    public void addIndexTankPic() {
        indexTankPic++;
        if (indexTankPic == 3) indexTankPic = 1;
    }

    public int getIndexCloudPic() {
        return indexCloudPic;
    }

    public void addIndexCloudPic() {
        indexCloudPic++;
        if (indexCloudPic == 4) indexCloudPic = 1;
    }

    public int getKill() {
        return kill;
    }

    public void addKill(int num) {
        this.kill += num;
    }

    public int getAllShoot() {
        return allShoot;
    }

    public void addAllShoot(int num) {
        this.allShoot += num;
    }

    public int getSuccessfulShoot() {
        return successfulShoot;
    }

    public void addSuccessfulShoot() {
        this.successfulShoot++;
    }

    public int getNumberOfRadioActive() {
        return numberOfRadioActive;
    }

    public void addNumberOfRadioActive() {
        this.numberOfRadioActive++;
    }

    public void reduceNumberOfRadioActive() {
        this.numberOfRadioActive--;
    }

    public int getNumberOfCluster() {
        return numberOfCluster;
    }

    public void addNumberOfCluster() {
        this.numberOfCluster++;
    }

    public void reduceNumberOfCluster() {
        this.numberOfCluster--;
    }

    public int getWave() {
        return wave;
    }

    public void addWave() {
        this.wave++;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public int getNumOfMusic() {
        return numOfMusic;
    }

    public void setNumOfMusic(int numOfMusic) {
        this.numOfMusic = numOfMusic;
    }

    public boolean isIcy() {
        return hasIcy;
    }

    public void setIcy(boolean icy) {
        hasIcy = icy;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public int getTankKills() {
        return tankKills;
    }

    public void setTankKills(int tankKills) {
        this.tankKills = tankKills;
    }

    public boolean isCactus1() {
        return cactus1;
    }

    public void setCactus1(boolean cactus1) {
        this.cactus1 = cactus1;
    }

    public boolean isCactus2() {
        return cactus2;
    }

    public void setCactus2(boolean cactus2) {
        this.cactus2 = cactus2;
    }

    public boolean isCactus3() {
        return cactus3;
    }

    public void setCactus3(boolean cactus3) {
        this.cactus3 = cactus3;
    }

    public int getProgressKill() {
        return progressKill;
    }

    public void setProgressKill(int progressKill) {
        if(progressKill == 5) App.getLoggedInUser().getGame().setIcy(true);
        if (progressKill > 5) progressKill = 5;
        this.progressKill = progressKill;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getLevelAndKill() {
        return levelAndKill;
    }

    public void setLevelAndKill(int levelAndKill) {
        this.levelAndKill = levelAndKill;
    }

    public String getEndGameSituation() {
        return endGameSituation;
    }

    public void setEndGameSituation(String endGameSituation) {
        this.endGameSituation = endGameSituation;
    }
}
