package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.*;
import view.EndGameMenu;
import view.Main;
import view.MainMenu;
import view.animations.ShootingAnimation;
import view.animations.TankAnimation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameController {
    static Button button1 = new Button();
    static Button button2 = new Button();
    static Button button3 = new Button();
    static Button button4 = new Button();
    static Button button5 = new Button();
    static Button button6 = new Button();
    static Text text = new Text();
    static Ice ice1 = new Ice(100, 100);
    static Ice ice2 = new Ice(200, 200);
    static Ice ice3 = new Ice(300, 100);
    static Ice ice4 = new Ice(400, 200);
    static Ice ice5 = new Ice(500, 100);

    private static ArrayList<Car> cars = new ArrayList<>();
    private static ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    private static ArrayList<Tank> tanks = new ArrayList<>();
    private static ArrayList<Mig> migs = new ArrayList<>();

    public static void moveUp(Airplane airplane) {
        if (airplane.getY() > -30) {
            airplane.setY(airplane.getY() - 10);
        }
    }

    public static void moveDown(Airplane airplane) {
        if (airplane.getY() < 250) {
            airplane.setY(airplane.getY() + 10);
        }
    }

    public static void moveLeft(Airplane airplane) {
        if (airplane.getX() > -10) {
            airplane.setX(airplane.getX() - 10);
            airplane.setScaleX(-1);
            airplane.setWay(-1);
        } else {
            airplane.setX(540);
        }
    }

    public static void moveRight(Airplane airplane) {
        if (airplane.getX() < 540) {
            airplane.setX(airplane.getX() + 10);
            airplane.setScaleX(1);
            airplane.setWay(1);
        } else {
            airplane.setX(-10);
        }
    }

    public static void shoot(Airplane airplane, Pane pane) {
        Bomb bomb = new Bomb(airplane.getX(), airplane.getY(), 1);
        pane.getChildren().add(bomb);
        ShootingAnimation shootingAnimation = new ShootingAnimation(pane, bomb, airplane, 1);
        shootingAnimation.play();
        App.getLoggedInUser().getGame().addAllShoot(1);
    }

    public static ArrayList<Car> getCars() {
        return cars;
    }

    public static ArrayList<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    public static ArrayList<Tank> getTanks() {
        return tanks;
    }

    public static void cluster(Airplane airplane, Pane pane) {
        if (App.getLoggedInUser().getGame().getNumberOfCluster() != 0) {
            Bomb bomb1 = new Bomb(airplane.getX(), airplane.getY(), 1);
            pane.getChildren().add(bomb1);
            ShootingAnimation shootingAnimation1 = new ShootingAnimation(pane, bomb1, airplane, 1);
            shootingAnimation1.play();

            Bomb bomb2 = new Bomb(airplane.getX(), airplane.getY(), 1);
            pane.getChildren().add(bomb2);
            ShootingAnimation shootingAnimation2 = new ShootingAnimation(pane, bomb2, airplane, 10);
            shootingAnimation2.play();

            Bomb bomb3 = new Bomb(airplane.getX(), airplane.getY(), 1);
            pane.getChildren().add(bomb3);
            ShootingAnimation shootingAnimation3 = new ShootingAnimation(pane, bomb3, airplane, -10);
            shootingAnimation3.play();

            App.getLoggedInUser().getGame().reduceNumberOfCluster();
            App.getLoggedInUser().getGame().addAllShoot(3);
        }
    }

    public static void radioActive(Airplane airplane, Pane pane) {
        if (App.getLoggedInUser().getGame().getNumberOfRadioActive() != 0) {
            Bomb bomb = new Bomb(airplane.getX(), airplane.getY(), 2);
            pane.getChildren().add(bomb);
            ShootingAnimation shootingAnimation = new ShootingAnimation(pane, bomb, airplane, 1);
            shootingAnimation.play();

            App.getLoggedInUser().getGame().reduceNumberOfRadioActive();
            App.getLoggedInUser().getGame().addAllShoot(1);
        }
    }

    public static void pause(Airplane airplane, Pane pane) {
        button1.setLayoutX(100);
        button1.setLayoutY(100);
        button1.setText("save");

        button2.setLayoutX(150);
        button2.setLayoutY(100);
        button2.setText("exit");

        button3.setLayoutX(200);
        button3.setLayoutY(100);
        button3.setText("music 1");

        button4.setLayoutX(270);
        button4.setLayoutY(100);
        button4.setText("music 2");

        button5.setLayoutX(340);
        button5.setLayoutY(100);
        button5.setText("music 3");

        button6.setLayoutX(100);
        button6.setLayoutY(130);
        button6.setText("stop music");

        String style = "-fx-background-color: rgb(255, 177, 255); -fx-text-fill: white; -fx-font-size: 10px; -fx-padding: 5px 10px;";
        button1.setStyle(style);
        button2.setStyle(style);
        button3.setStyle(style);
        button4.setStyle(style);
        button5.setStyle(style);
        button6.setStyle(style);


        App.getLoggedInUser().getGame().setPause(!App.getLoggedInUser().getGame().isPause());

        String theText = "R: radioActive\n" +
                "C: cluster\n" +
                "G: add radioActive\n" +
                "Ctrl: add cluster\n" +
                "Tab: frieze";
        text.setText(theText);
        text.setX(200);
        text.setY(150);

        if ((App.getLoggedInUser().getGame().isPause() && !App.getLoggedInUser().getGame().isIcy())
                || (!App.getLoggedInUser().getGame().isPause() && App.getLoggedInUser().getGame().isIcy())) {
            pane.getChildren().addAll(button1, button2, button3, button4, button5, button6, text);
        } else {
            pane.getChildren().removeAll(button1, button2, button3, button4, button5, button6, text);

        }


        button1.setOnMouseClicked(event -> {
            try {
                saveGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        button2.setOnMouseClicked(event -> {
            try {
                exitWithoutSaving();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        button3.setOnMouseClicked(event -> changeMusic(1, pane));
        button4.setOnMouseClicked(event -> changeMusic(2, pane));
        button5.setOnMouseClicked(event -> changeMusic(3, pane));
        button6.setOnMouseClicked(event -> stopMusic( pane));

        airplane.requestFocus();
    }

    private static void stopMusic(Pane pane) {
        App.getLoggedInUser().getGame().getMediaPlayer().pause();
        pane.getChildren().get(0).requestFocus();
    }

    private static void changeMusic(int i, Pane pane) {
        App.getLoggedInUser().getGame().getMediaPlayer().pause();
        App.getLoggedInUser().getGame().setNumOfMusic(i);
        App.getLoggedInUser().getGame().setMediaPlayer(new MediaPlayer(new Media(Main.class.getResource(
                "/media/music" + App.getLoggedInUser().getGame().getNumOfMusic() + ".mp3").toExternalForm())));
        App.getLoggedInUser().getGame().getMediaPlayer().play();

        pane.getChildren().get(0).requestFocus();
    }

    private static void exitWithoutSaving() throws IOException {
        App.getLoggedInUser().getGame().getMediaPlayer().pause();
        App.getLoggedInUser().setLastGame(null);
        pause();
        (new MainMenu()).start(Main.stage);
    }

    private static void pause() {
        App.getLoggedInUser().getGame().timeline1.pause();
        App.getLoggedInUser().getGame().timeline2.pause();
        App.getLoggedInUser().getGame().timeline3.pause();
        App.getLoggedInUser().getGame().timeline4.pause();
        App.getLoggedInUser().getGame().timeline5.pause();
        App.getLoggedInUser().getGame().timeline6.pause();
        App.getLoggedInUser().getGame().timeline7.pause();
        App.getLoggedInUser().getGame().timeline8.pause();
        App.getLoggedInUser().getGame().timeline9.pause();
        App.getLoggedInUser().getGame().timeline10.pause();
    }

    private static void saveGame() throws IOException {
        GameController.getTanks().clear();
        App.getLoggedInUser().setLastGame(App.getLoggedInUser().getGame());
         App.getLoggedInUser().getGame().getMediaPlayer().pause();
        (new MainMenu()).start(Main.stage);
    }

    public static void ice(Pane pane) {
        if (App.getLoggedInUser().getGame().isIcy()) {
            App.getLoggedInUser().getGame().setIcy(false);
            App.getLoggedInUser().getGame().setProgressKill(0);
            App.getLoggedInUser().getGame().setPause(true);

            pane.getChildren().add(ice1);
            pane.getChildren().add(ice2);
            pane.getChildren().add(ice3);
            pane.getChildren().add(ice4);
            pane.getChildren().add(ice5);

            App.getLoggedInUser().getGame().setProgressKill(0);

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(15),
                    actionEvent -> deleteIcy(pane)));
            timeline.setCycleCount(-1);
            timeline.play();
        }
    }

    private static void deleteIcy(Pane pane) {
        App.getLoggedInUser().getGame().setPause(false);

        pane.getChildren().remove(ice1);
        pane.getChildren().remove(ice2);
        pane.getChildren().remove(ice3);
        pane.getChildren().remove(ice4);
        pane.getChildren().remove(ice5);
    }

    public static void fullHP(Airplane airplane) {
        airplane.setHp(100);
    }

    public static void addTank(Pane pane) {
        Random rand = new Random();
        int randomInt = rand.nextInt(500);
        Tank tank = new Tank(randomInt);
        GameController.getTanks().add(tank);
        pane.getChildren().add(tank);
        TankAnimation tankAnimation = new TankAnimation(pane, tank);
        tankAnimation.play();
    }

    public static ArrayList<Mig> getMigs() {
        return migs;
    }

    public static void GameOver() throws Exception {
        App.getLoggedInUser().getGame().getMediaPlayer().pause();
        App.getLoggedInUser().setLastGame(null);
        App.getLoggedInUser().getGame().setAccuracy(
                100 * ((double) App.getLoggedInUser().getGame().getSuccessfulShoot() / (3 * App.getLoggedInUser().getGame().getAllShoot())));
        pause();
        checkBest();

        (new EndGameMenu()).start(Main.stage);
    }

    private static void checkBest() {
        if (App.getLoggedInUser().getGame().getKill() > App.getLoggedInUser().getBestGameByKill()) {
            App.getLoggedInUser().setBestGameByKill(App.getLoggedInUser().getGame().getKill());
        }
        if (App.getLoggedInUser().getGame().getAccuracy() > App.getLoggedInUser().getBestGameByAccuracy()) {
            App.getLoggedInUser().setBestGameByAccuracy(App.getLoggedInUser().getGame().getAccuracy());
        }
        if (App.getLoggedInUser().getGame().getLevelAndKill() > App.getLoggedInUser().getBestGameByLevel()) {
            App.getLoggedInUser().setBestGameByLevel(App.getLoggedInUser().getGame().getLevelAndKill());
        }
    }
}
