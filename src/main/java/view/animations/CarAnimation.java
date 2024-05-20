package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.App;
import model.Bomb;
import model.Car;

public class CarAnimation extends Transition {
    private Pane pane;
    private Car car;
    public CarAnimation(Pane pane, Car car) {
        this.pane = pane;
        this.car = car;
        this.setCycleDuration(Duration.millis(5000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double x = car.getX() + 1;
        if(x >= 550){
            pane.getChildren().remove(car);
            this.stop();
            GameController.getCars().remove(car);
        }
        if (!App.getLoggedInUser().getGame().isPause()) {
            car.setX(x);
        }
    }
}
