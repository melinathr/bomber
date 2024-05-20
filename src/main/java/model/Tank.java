package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

public class Tank extends Rectangle {
    private int helth = 20;
    public Tank(int x) {
        super(x,300, 30, 40);
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/tank"+ App.getLoggedInUser().getGame().getIndexTankPic() +".png").toString())));
        App.getLoggedInUser().getGame().addIndexTankPic();
    }

    public int getHelth() {
        return helth;
    }

    public void reduceHelth() {
        this.helth --;
    }
}
