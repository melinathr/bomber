package view;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.animations.*;

import java.io.IOException;
import java.util.Objects;

public class Game extends Application {

    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/FXML/Game.fxml")));

        Airplane airplane = createAirplane(pane);
        pane.getChildren().add(airplane);

        addCactus(pane);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setLayoutX(5);
        vBox.setLayoutY(5);
        Text text = new Text();
        text.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
        ProgressBar progressBar = new ProgressBar(0);
        addInfo(text, airplane, pane, progressBar);
        vBox.getChildren().add(text);
        vBox.getChildren().add(progressBar);
        vBox.setSpacing(5);
        pane.getChildren().add(vBox);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setLayoutX(500);
        hBox.setLayoutY(5);
        Text aware = new Text();
        text.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
        hBox.getChildren().add(aware);
        pane.getChildren().add(hBox);


        App.getLoggedInUser().getGame().setMediaPlayer(new MediaPlayer(new Media(
                getClass().getResource("/media/music1.mp3").toExternalForm())));
        if (!App.getLoggedInUser().isMute()) {
            App.getLoggedInUser().getGame().getMediaPlayer().play();
        }

        Background background = new Background(setBackground());
        pane.setBackground(background);

        Scene scene = new Scene(pane);
        stage.setScene(scene);

        if (App.getLoggedInUser().isGray()) {
            ColorAdjust gray = new ColorAdjust();
            gray.setSaturation(-1);
            scene.getRoot().setEffect(gray);
        }

        pane.getChildren().get(0).requestFocus();

        setFirstWaveTimeLine(pane, airplane, text, progressBar, aware);
        if (App.getLoggedInUser().getGame().getWave() > 1) {
            wave2(pane, airplane);
            if (App.getLoggedInUser().getGame().getWave() == 3) {
                wave3(pane, airplane);
            }
        }

        stage.show();
    }

    private void addInfo(Text text, Airplane airplane, Pane pane, ProgressBar progressBar) {
        String textString = "Hp = " + airplane.getHp() + "%\n" +
                "kills = " + App.getLoggedInUser().getGame().getKill() + "\n" +
                "clusters = " + App.getLoggedInUser().getGame().getNumberOfCluster() + "\n" +
                "radio active = " + App.getLoggedInUser().getGame().getNumberOfRadioActive() + "\n" +
                "accuracy = " +
                100 * ((double) App.getLoggedInUser().getGame().getSuccessfulShoot() / (3 * App.getLoggedInUser().getGame().getAllShoot()))
                + "%\n" +
                "wave = " + App.getLoggedInUser().getGame().getWave();

        text.setText(textString);

        progressBar.setProgress((double) App.getLoggedInUser().getGame().getProgressKill() / 5);
    }

    private void addCactus(Pane pane) {
        if (!App.getLoggedInUser().getGame().isCactus1()) {
            Cactus cactus1 = new Cactus(1);
            pane.getChildren().add(cactus1);
        }
        if (!App.getLoggedInUser().getGame().isCactus2()) {
            Cactus cactus2 = new Cactus(2);
            pane.getChildren().add(cactus2);
        }
        if (!App.getLoggedInUser().getGame().isCactus3()) {
            Cactus cactus3 = new Cactus(3);
            pane.getChildren().add(cactus3);
        }
    }

    private void setFirstWaveTimeLine(Pane pane, Airplane airplane, Text text, ProgressBar progressBar, Text aware) {
        int level = App.getLoggedInUser().getLevel();
        App.getLoggedInUser().getGame().timeline1 = new Timeline(new KeyFrame(Duration.seconds((double) 10 / level),
                actionEvent -> carMoving(pane)));
        App.getLoggedInUser().getGame().timeline2 = new Timeline(new KeyFrame(Duration.seconds((double) 52 / level),
                actionEvent -> tankMoving(pane)));
        App.getLoggedInUser().getGame().timeline3 = new Timeline(new KeyFrame(Duration.seconds((double) 34 / level),
                actionEvent -> motorMoving(pane)));
        App.getLoggedInUser().getGame().timeline4 = new Timeline(new KeyFrame(Duration.seconds(1),
                actionEvent -> explosionFade(pane)));
        App.getLoggedInUser().getGame().timeline5 = new Timeline(new KeyFrame(Duration.seconds(4),
                actionEvent -> cloud(pane)));
        App.getLoggedInUser().getGame().timeline6 = new Timeline(new KeyFrame(Duration.millis(100),
                actionEvent -> addInfo(text, airplane, pane, progressBar)));
        App.getLoggedInUser().getGame().timeline7 = new Timeline(new KeyFrame(Duration.seconds(1),
                actionEvent -> {
                    try {
                        checkWave(pane, airplane);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }));
        App.getLoggedInUser().getGame().timeline8 = new Timeline(new KeyFrame(Duration.seconds(1),
                actionEvent -> awareText(aware)));

        App.getLoggedInUser().getGame().timeline1.setCycleCount(-1);
        App.getLoggedInUser().getGame().timeline2.setCycleCount(-1);
        App.getLoggedInUser().getGame().timeline3.setCycleCount(-1);
        App.getLoggedInUser().getGame().timeline4.setCycleCount(-1);
        App.getLoggedInUser().getGame().timeline5.setCycleCount(-1);
        App.getLoggedInUser().getGame().timeline6.setCycleCount(-1);
        App.getLoggedInUser().getGame().timeline7.setCycleCount(-1);
        App.getLoggedInUser().getGame().timeline8.setCycleCount(-1);

        App.getLoggedInUser().getGame().timeline1.play();
        App.getLoggedInUser().getGame().timeline2.play();
        App.getLoggedInUser().getGame().timeline3.play();
        App.getLoggedInUser().getGame().timeline4.play();
        App.getLoggedInUser().getGame().timeline5.play();
        App.getLoggedInUser().getGame().timeline6.play();
        App.getLoggedInUser().getGame().timeline7.play();
        App.getLoggedInUser().getGame().timeline8.play();
    }

    private void awareText(Text aware) {
        if (!GameController.getMigs().isEmpty()){
            aware.setText("MIG IS HERE");
        }
        else aware.setText("");
    }

    private void checkWave(Pane pane, Airplane airplane) throws Exception {
        if (App.getLoggedInUser().getGame().getTankKills() > 4 && App.getLoggedInUser().getGame().getWave() == 1) {
            App.getLoggedInUser().getGame().addWave();
            wave2(pane, airplane);
            addNewCactus(pane);
        }
        if (App.getLoggedInUser().getGame().getTankKills() > 4 && App.getLoggedInUser().getGame().getWave() == 2) {
            App.getLoggedInUser().getGame().addWave();
            wave3(pane, airplane);
            addNewCactus(pane);
        }
        if (App.getLoggedInUser().getGame().getTankKills() > 4 && App.getLoggedInUser().getGame().getWave() == 3) {
            GameController.GameOver();
            App.getLoggedInUser().getGame().setEndGameSituation("YOU WIN");
            App.getLoggedInUser().getGame().setTankKills(0);
        }
    }

    private void wave2(Pane pane, Airplane airplane) {
        App.getLoggedInUser().getGame().setTankKills(0);

        App.getLoggedInUser().getGame().timeline9 = new Timeline(new KeyFrame(Duration.seconds(1),
                actionEvent -> shootToAirplane(pane, airplane)));
        App.getLoggedInUser().getGame().timeline9.setCycleCount(-1);
        App.getLoggedInUser().getGame().timeline9.play();
    }

    private void wave3(Pane pane, Airplane airplane) {
        App.getLoggedInUser().getGame().timeline10 = new Timeline(new KeyFrame(Duration.seconds(20),
                actionEvent -> sendMig(pane, airplane)));
        App.getLoggedInUser().getGame().timeline10.setCycleCount(-1);
        App.getLoggedInUser().getGame().timeline10.play();
    }

    private void sendMig(Pane pane, Airplane airplane) {
        if (!App.getLoggedInUser().getGame().isPause()) {
            Mig mig = new Mig(airplane);
            pane.getChildren().add(mig);
            MigAnimation animation = new MigAnimation(pane, mig);
            animation.play();
        }
    }

    private void addNewCactus(Pane pane) {
        if (App.getLoggedInUser().getGame().isCactus1()) {
            Cactus cactus1 = new Cactus(1);
            pane.getChildren().add(cactus1);
            App.getLoggedInUser().getGame().setCactus1(false);
        }
        if (App.getLoggedInUser().getGame().isCactus2()) {
            Cactus cactus2 = new Cactus(2);
            pane.getChildren().add(cactus2);
            App.getLoggedInUser().getGame().setCactus2(false);
        }
        if (App.getLoggedInUser().getGame().isCactus3()) {
            Cactus cactus3 = new Cactus(3);
            pane.getChildren().add(cactus3);
            App.getLoggedInUser().getGame().setCactus3(false);
        }
    }

    private void shootToAirplane(Pane pane, Airplane airplane) {
        for (Tank tank : GameController.getTanks()) {
            if (isNear(tank, airplane)) {
                Bomb bomb = new Bomb(tank.getX(), tank.getY(), 3);
                pane.getChildren().add(bomb);
                TankShooting animation = new TankShooting
                        (pane, bomb, airplane, tank.getX(), airplane.getX(), tank.getY() - 5, airplane.getY());

                animation.play();
            }
        }

        for (Mig mig : GameController.getMigs()) {
            if (isNear(mig, airplane)) {
                Bomb bomb = new Bomb(mig.getX(), mig.getY(), 3);
                pane.getChildren().add(bomb);
                TankShooting animation = new TankShooting
                        (pane, bomb, airplane, mig.getX(), airplane.getX(), mig.getY() - 5, airplane.getY());

                animation.play();
            }
        }
    }

    private boolean isNear(Tank tank, Airplane airplane) {
        double changeX = tank.getX() - airplane.getX();
        double changeY = tank.getY() - airplane.getY();
        return changeY * changeY + changeX * changeX <
                (double) 20000 * (App.getLoggedInUser().getLevel() * App.getLoggedInUser().getLevel());
    }

    private boolean isNear(Mig mig, Airplane airplane) {
        double changeX = mig.getX() - airplane.getX();
        double changeY = mig.getY() - airplane.getY();
        return changeY * changeY + changeX * changeX <
                (double) 20000 * (App.getLoggedInUser().getLevel() * App.getLoggedInUser().getLevel());
    }


    private void cloud(Pane pane) {
        if (!App.getLoggedInUser().getGame().isPause()) {
            Cloud cloud = new Cloud();
            pane.getChildren().add(cloud);
            CloudAnimation cloudAnimation = new CloudAnimation(pane, cloud);
            cloudAnimation.play();
        }
    }

    private void explosionFade(Pane pane) {
        for (Explosion explosion : Explosion.explosions) {
            pane.getChildren().remove(explosion);
        }
    }

    private void motorMoving(Pane pane) {
        if (!App.getLoggedInUser().getGame().isPause()) {
            Motorcycle motorcycle = new Motorcycle();
            GameController.getMotorcycles().add(motorcycle);
            pane.getChildren().add(motorcycle);
            MotorAnimation motorAnimation = new MotorAnimation(pane, motorcycle);
            motorAnimation.play();
        }
    }

    private void tankMoving(Pane pane) {
        if (!App.getLoggedInUser().getGame().isPause()) {
            Tank tank = new Tank(0);
            GameController.getTanks().add(tank);
            pane.getChildren().add(tank);
            TankAnimation tankAnimation = new TankAnimation(pane, tank);
            tankAnimation.play();
        }
    }

    private void carMoving(Pane pane) {
        if (!App.getLoggedInUser().getGame().isPause()) {
            Car car = new Car();
            GameController.getCars().add(car);
            pane.getChildren().add(car);
            CarAnimation carAnimation = new CarAnimation(pane, car);
            carAnimation.play();
        }
    }

    private Airplane createAirplane(Pane pane) {
        Airplane airplane = new Airplane();
        airplane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                double step = 10;
                if (App.getLoggedInUser().isWasd()) {
                    switch (keyEvent.getCode()) {
                        case W:
                            GameController.moveUp(airplane);
                            break;
                        case S:
                            GameController.moveDown(airplane);
                            break;
                        case A:
                            GameController.moveLeft(airplane);
                            break;
                        case D:
                            GameController.moveRight(airplane);
                            break;
                        case SPACE:
                            GameController.shoot(airplane, pane);
                            break;
                        case C:
                            GameController.cluster(airplane, pane);
                            break;
                        case R:
                            GameController.radioActive(airplane, pane);
                            break;
                        case CONTROL:
                            App.getLoggedInUser().getGame().addNumberOfCluster();
                            break;
                        case G:
                            App.getLoggedInUser().getGame().addNumberOfRadioActive();
                            break;
                        case P:
                            GameController.pause(airplane, pane);
                            break;
                        case TAB:
                            GameController.ice(pane);
                            break;
                        case H:
                            GameController.fullHP(airplane);
                            break;
                        case T:
                            GameController.addTank(pane);
                            break;
                        case V:
                            if (App.getLoggedInUser().getGame().getWave() == 1) {
                                App.getLoggedInUser().getGame().addWave();
                                wave2(pane, airplane);
                                addNewCactus(pane);
                            } else if (App.getLoggedInUser().getGame().getWave() == 2) {
                                App.getLoggedInUser().getGame().addWave();
                                wave3(pane, airplane);
                                addNewCactus(pane);
                            }
                            break;
                    }
                } else {
                    switch (keyEvent.getCode()) {
                        case UP:
                            GameController.moveUp(airplane);
                            break;
                        case DOWN:
                            GameController.moveDown(airplane);
                            break;
                        case LEFT:
                            GameController.moveLeft(airplane);
                            break;
                        case RIGHT:
                            GameController.moveRight(airplane);
                            break;
                        case SPACE:
                            GameController.shoot(airplane, pane);
                            break;
                        case C:
                            GameController.cluster(airplane, pane);
                            break;
                        case R:
                            GameController.radioActive(airplane, pane);
                            break;
                        case CONTROL:
                            App.getLoggedInUser().getGame().addNumberOfCluster();
                            break;
                        case G:
                            App.getLoggedInUser().getGame().addNumberOfRadioActive();
                            break;
                        case P:
                            GameController.pause(airplane, pane);
                            break;
                        case TAB:
                            GameController.ice(pane);
                            break;
                        case H:
                            GameController.fullHP(airplane);
                            break;
                        case T:
                            GameController.addTank(pane);
                            break;
                        case W:
                            if (App.getLoggedInUser().getGame().getWave() == 1) {
                                App.getLoggedInUser().getGame().addWave();
                                wave2(pane, airplane);
                                addNewCactus(pane);
                            } else if (App.getLoggedInUser().getGame().getWave() == 2) {
                                App.getLoggedInUser().getGame().addWave();
                                wave3(pane, airplane);
                                addNewCactus(pane);
                            }
                            break;
                    }
                }
            }
        });

        return airplane;
    }

    private BackgroundImage setBackground() {
        Image image = new Image(Main.class.getResource("/images/gameBackground" + App.getLoggedInUser().getIndexBackground() + ".jpg").toString(), 700, 400, false, false);
        BackgroundImage background = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return background;
    }
}