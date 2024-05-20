package model;

import controller.GameController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

public class Mig extends Rectangle {
    private int helth = 5;
    Airplane airplane;
    public Mig(Airplane airplane) {
        super(540 ,airplane.getY(), 40, 60);
        this.airplane = airplane;
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/mig.png").toString())));
        GameController.getMigs().add(this);
    }

    public int getHelth() {
        return helth;
    }

    public void reduceHelth() {
        this.helth --;
    }
}
