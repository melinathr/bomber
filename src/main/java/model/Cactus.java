package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

import java.util.ArrayList;

public class Cactus extends Rectangle {
    private int helth = 5;
    private int numberOfCactus;
    public static ArrayList<Cactus> catuses = new ArrayList<>();
    public Cactus(int i) {
        super(130 * i,290, 40, 40);
        this.numberOfCactus = i;
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/cactus" + i + ".png").toString())));
        catuses.add(this);
    }

    public int getHelth() {
        return helth;
    }

    public void reduceHelth() {
        this.helth --;
    }

    public int getNumberOfCactus() {
        return numberOfCactus;
    }

    public void setNumberOfCactus(int numberOfCactus) {
        this.numberOfCactus = numberOfCactus;
    }
}
