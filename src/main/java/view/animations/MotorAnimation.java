package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.App;
import model.Car;
import model.Motorcycle;

public class MotorAnimation extends Transition {
    private final Pane pane;
    private final Motorcycle motor;
    public MotorAnimation(Pane pane, Motorcycle motor) {
        this.pane = pane;
        this.motor = motor;
        this.setCycleDuration(Duration.millis(5000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double x = motor.getX() + 1;
        if(x >= 550){
            pane.getChildren().remove(motor);
            this.stop();
            GameController.getMotorcycles().remove(motor);
        }
        if (!App.getLoggedInUser().getGame().isPause()) {
            motor.setX(x);
        }
    }
}
