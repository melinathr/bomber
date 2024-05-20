package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

public class Car extends Rectangle {
    private int helth = 10;
    public Car() {
        super(0,300, 30, 40);
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/car"+ App.getLoggedInUser().getGame().getIndexCarPic() +".png").toString())));
        App.getLoggedInUser().getGame().addIndexCarPic();
    }

    public int getHelth() {
        return helth;
    }

    public void reduceHelth() {
        this.helth --;
    }
}
