package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

public class Airplane extends Rectangle {
    private int way = 1;
    private int hp = 100;
    public Airplane() {
        super(0,0, 50, 50);
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/airplane.png").toString())));
    }

    public int getWay() {
        return way;
    }

    public void setWay(int way) {
        this.way = way;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
