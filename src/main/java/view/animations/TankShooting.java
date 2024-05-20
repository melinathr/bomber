package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.App;
import model.Bomb;
import model.Explosion;

import java.io.IOException;

public class TankShooting extends Transition {
    private final Pane pane;
    private final Bomb bomb;
    private final Airplane airplane;
    private final double firstX;
    private final double lastX;
    private final double firstY;
    private final double lastY;
    private boolean isHeat = false;

    public TankShooting(Pane pane, Bomb bomb, Airplane airplane, double firstX, double lastX, double firstY, double lastY) {
        this.pane = pane;
        this.bomb = bomb;
        this.airplane = airplane;
        this.firstX = firstX;
        this.firstY = firstY;
        this.lastX = lastX;
        this.lastY = lastY;

        this.setCycleDuration(Duration.millis(5000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double changeX = (lastX - firstX) / 100;
        double changeY = (lastY - firstY) / 100;
        double y = bomb.getY() + changeY;
        double x = bomb.getX() + changeX;

        if (airplane.getBoundsInParent().intersects(bomb.getLayoutBounds())) {
            if (!isHeat) {
                airplane.setHp(airplane.getHp() - 5);
                if (airplane.getHp() <= 0){
                    try {
                        GameController.GameOver();
                        App.getLoggedInUser().getGame().setEndGameSituation("GAME OVER");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                    pane.getChildren().remove(bomb);

                Explosion explosion = new Explosion(airplane.getX(), airplane.getY(), 1);
                pane.getChildren().add(explosion);
                isHeat = true;
            }
        }

        if (y < 0) {
            pane.getChildren().remove(bomb);
        }
        if (!App.getLoggedInUser().getGame().isPause()) {
            bomb.setY(y);
            bomb.setX(x);
        }
    }
}
