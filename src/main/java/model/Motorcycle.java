package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

public class Motorcycle extends Rectangle {
    private int helth = 5;
    public Motorcycle() {
        super(0,300, 25, 35);
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/motorcycle"+ App.getLoggedInUser().getGame().getIndexMotorPic() +".png").toString())));
        App.getLoggedInUser().getGame().addIndexMotorPic();
    }

    public int getHelth() {
        return helth;
    }

    public void reduceHelth() {
        this.helth --;
    }
}
