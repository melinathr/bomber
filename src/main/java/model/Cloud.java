package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

public class Cloud extends Rectangle {
    public Cloud() {
        super(0,50 * App.getLoggedInUser().getGame().getIndexCloudPic(), 50, 50);
        setFill(new ImagePattern(new Image(Main.class.getResource("/images/cloud" + App.getLoggedInUser().getGame().getIndexCloudPic() + ".png").toString())));
        App.getLoggedInUser().getGame().addIndexCloudPic();
    }
}
