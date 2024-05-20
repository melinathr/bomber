package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Car;
import model.Cloud;

public class CloudAnimation extends Transition {
    private Pane pane;
    private Cloud cloud;
    public CloudAnimation(Pane pane, Cloud cloud) {
        this.pane = pane;
        this.cloud = cloud;
        this.setCycleDuration(Duration.millis(5000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double x = cloud.getX() + 1;
        if(x >= 550){
            pane.getChildren().remove(cloud);
            this.stop();
        }
        cloud.setX(x);
    }
}
