package view.animations;

import controller.GameController;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.*;

import java.util.ArrayList;

public class ShootingAnimation extends Transition {
    private Pane pane;
    private Bomb bomb;
    private Airplane airplane;
    private boolean isHeated = false;
    private int way;

    public ShootingAnimation(Pane pane, Bomb bomb, Airplane airplane, int way) {
        this.pane = pane;
        this.bomb = bomb;
        this.airplane = airplane;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.way = way;
    }

    @Override
    protected void interpolate(double v) {
        double y = bomb.getY() + 10;
        double x = bomb.getX() + (airplane.getWay() * way);


        ArrayList<Car> carToRemove = new ArrayList<>();
        for (Car car : GameController.getCars()) {
            if (car.getBoundsInParent().intersects(bomb.getLayoutBounds())) {
                App.getLoggedInUser().getGame().addSuccessfulShoot();
                isHeated = true;
                RotateTransition transition = new RotateTransition();
                transition.setNode(car);
                playRotateTransition(transition);
                car.reduceHelth();
                if (car.getHelth() <= 0) {
                    pane.getChildren().remove(car);
                    carToRemove.add(car);
                    Explosion explosion = new Explosion(car.getX(), car.getY(), 3);
                    pane.getChildren().add(explosion);
                    App.getLoggedInUser().getGame().addKill(2);
                    pane.getChildren().remove(car);
                    App.getLoggedInUser().getGame().setProgressKill(App.getLoggedInUser().getGame().getProgressKill() + 1);
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(
                            getClass().getResource("/media/explosion.mp3").toExternalForm()));
                    mediaPlayer.play();
                }
            }
        }
        GameController.getCars().removeAll(carToRemove);


        ArrayList<Tank> tankToRemove = new ArrayList<>();
        for (Tank tank : GameController.getTanks()) {
            isHeated = true;
            if (tank.getBoundsInParent().intersects(bomb.getLayoutBounds())) {
                App.getLoggedInUser().getGame().addSuccessfulShoot();
                RotateTransition transition = new RotateTransition();
                transition.setNode(tank);
                playRotateTransition(transition);
                tank.reduceHelth();
                if (tank.getHelth() == 0) {
                    pane.getChildren().remove(tank);
                    tankToRemove.add(tank);
                    Explosion explosion = new Explosion(tank.getX(), tank.getY(), 3);
                    pane.getChildren().add(explosion);
                    App.getLoggedInUser().getGame().addKill(4);
                    pane.getChildren().remove(tank);
                    App.getLoggedInUser().getGame().setTankKills(App.getLoggedInUser().getGame().getTankKills() + 1);
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(
                            getClass().getResource("/media/explosion.mp3").toExternalForm()));
                    mediaPlayer.play();
                }
            }
        }
        GameController.getTanks().removeAll(tankToRemove);


        ArrayList<Motorcycle> motorsToRemove = new ArrayList<>();
        for (Motorcycle motor : GameController.getMotorcycles()) {
            if (motor.getBoundsInParent().intersects(bomb.getLayoutBounds())) {
                isHeated = true;
                App.getLoggedInUser().getGame().addSuccessfulShoot();
                RotateTransition transition = new RotateTransition();
                transition.setNode(motor);
                playRotateTransition(transition);
                motor.reduceHelth();
                if (motor.getHelth() == 0) {
                    pane.getChildren().remove(motor);
                    motorsToRemove.add(motor);
                    Explosion explosion = new Explosion(motor.getX(), motor.getY(), 3);
                    pane.getChildren().add(explosion);
                    App.getLoggedInUser().getGame().addKill(1);
                    pane.getChildren().remove(motor);
                    App.getLoggedInUser().getGame().setProgressKill(App.getLoggedInUser().getGame().getProgressKill() + 1);
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(
                            getClass().getResource("/media/explosion.mp3").toExternalForm()));
                    mediaPlayer.play();
                }
            }
        }
        GameController.getMotorcycles().removeAll(motorsToRemove);


        ArrayList<Mig> migsTokill = new ArrayList<>();
        for (Mig mig : GameController.getMigs()) {
            if (mig.getBoundsInParent().intersects(bomb.getLayoutBounds())) {
                isHeated = true;
                pane.getChildren().remove(bomb);
                App.getLoggedInUser().getGame().addSuccessfulShoot();
                mig.reduceHelth();
                if (mig.getHelth() == 0) {
                    pane.getChildren().remove(mig);
                    migsTokill.add(mig);
                    Explosion explosion = new Explosion(mig.getX(), mig.getY(), 3);
                    pane.getChildren().add(explosion);
                    App.getLoggedInUser().getGame().addKill(8);
                    pane.getChildren().remove(mig);
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(
                            getClass().getResource("/media/explosion.mp3").toExternalForm()));
                    mediaPlayer.play();
                }
            }
        }
        GameController.getMigs().removeAll(migsTokill);


        ArrayList<Cactus> cactusToRemove = new ArrayList<>();
        for (Cactus cactus : Cactus.catuses) {
            if (cactus.getBoundsInParent().intersects(bomb.getLayoutBounds())) {
                isHeated = true;
                App.getLoggedInUser().getGame().addSuccessfulShoot();
                cactus.reduceHelth();
                if (cactus.getHelth() == 0) {
                    pane.getChildren().remove(cactus);
                    cactusToRemove.add(cactus);
                    Explosion explosion = new Explosion(bomb.getX(), bomb.getY(), 2);
                    pane.getChildren().add(explosion);
                    if (cactus.getNumberOfCactus() == 2) {
                        App.getLoggedInUser().getGame().addNumberOfCluster();
                        App.getLoggedInUser().getGame().setCactus2(true);
                    } else if (cactus.getNumberOfCactus() == 3) {
                        App.getLoggedInUser().getGame().addNumberOfRadioActive();
                        App.getLoggedInUser().getGame().setCactus3(true);
                    } else {
                        App.getLoggedInUser().getGame().setCactus1(true);
                    }
                    pane.getChildren().remove(cactus);
                    MediaPlayer mediaPlayer = new MediaPlayer(new Media(
                            getClass().getResource("/media/explosion.mp3").toExternalForm()));
                    mediaPlayer.play();
                }
            }
        }
        Cactus.catuses.removeAll(cactusToRemove);


        if (y >= 300) {
            pane.getChildren().remove(bomb);
            Explosion explosion = new Explosion(bomb.getX(), bomb.getY(), 1);
            if (!isHeated) {
                pane.getChildren().add(explosion);
            }
            this.stop();
        }
        bomb.setY(y);
        bomb.setX(x);
    }


    private void playRotateTransition(RotateTransition transition) {
        transition.setDuration(Duration.millis(1000));
        transition.setFromAngle(0);
        transition.setToAngle(360);
        transition.play();
    }
}
