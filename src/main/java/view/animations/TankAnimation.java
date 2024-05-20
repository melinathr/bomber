package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.App;
import model.Motorcycle;
import model.Tank;

public class TankAnimation extends Transition {
    private final Pane pane;
    private final Tank tank;
    public TankAnimation(Pane pane, Tank tank) {
        this.pane = pane;
        this.tank = tank;
        this.setCycleDuration(Duration.millis(5000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double x = tank.getX() + 1;
        if(x >= 550){
            pane.getChildren().remove(tank);
            this.stop();
            GameController.getTanks().remove(tank);
        }
        if (!App.getLoggedInUser().getGame().isPause()) {
            tank.setX(x);
        }
    }
}
