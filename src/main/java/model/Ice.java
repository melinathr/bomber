package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

public class Ice extends Rectangle {
    public Ice( int x, int y) {
        super(x,y, 30, 30);
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/ice.png").toExternalForm())));
    }
}
