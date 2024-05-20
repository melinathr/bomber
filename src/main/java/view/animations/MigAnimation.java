package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.App;
import model.Car;
import model.Mig;

public class MigAnimation extends Transition {
    private Pane pane;
    private Mig mig;
    public MigAnimation(Pane pane, Mig mig) {
        this.pane = pane;
        this.mig = mig;
        this.setCycleDuration(Duration.millis(5000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double x = mig.getX() - 1;
        if(x <= -10){
            pane.getChildren().remove(mig);
            this.stop();
            GameController.getMigs().remove(mig);
        }
        if (!App.getLoggedInUser().getGame().isPause()) {
            mig.setX(x);
        }
    }
}
