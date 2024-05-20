package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

public class Bomb extends Rectangle {
    public Bomb(double x, double y, int i) {
        super(x + 15, y + 30, 15, 20);
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/bomb" + i + ".png").toString())));
    }
}
