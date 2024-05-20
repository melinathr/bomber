package model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

import java.util.ArrayList;

public class Explosion extends Rectangle {
    static public ArrayList<Explosion> explosions = new ArrayList<>();

    public Explosion(double x, double y, int i) {
        super(x, y, 50, 50);
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/explosion" + i + ".png").toString())));
        explosions.add(this);
    }
}
