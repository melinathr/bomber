package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.App;

import java.net.URL;
import java.util.ResourceBundle;

public class AvatarMenu extends Application {
    public TextField adress;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/Avatar.fxml");
        assert url != null;
        Pane root = FXMLLoader.load(url);

        addAvatarImages(root);

        stage.setScene(new Scene(root));
        stage.show();
    }



    private void addAvatarImages(Pane root) {
        Rectangle rectangle1 = new Rectangle(130, 100, 50,50);
        Image image1 = new Image(Main.class.getResource("/images/Avatar1.png").toString());
        rectangle1.setFill(new ImagePattern(image1));
        root.getChildren().add(rectangle1);


        Rectangle rectangle2 = new Rectangle(210, 100, 50,50);
        Image image2 = new Image(Main.class.getResource("/images/Avatar2.png").toString());
        rectangle2.setFill(new ImagePattern(image2));
        root.getChildren().add(rectangle2);


        Rectangle rectangle3 = new Rectangle(290, 100, 50,50);
        Image image3 = new Image(Main.class.getResource("/images/Avatar3.png").toString());
        rectangle3.setFill(new ImagePattern(image3));
        root.getChildren().add(rectangle3);


        Rectangle rectangle4 = new Rectangle(370, 100, 50,50);
        Image image4 = new Image(Main.class.getResource("/images/Avatar4.png").toString());
        rectangle4.setFill(new ImagePattern(image4));
        root.getChildren().add(rectangle4);


        Rectangle rectangle5 = new Rectangle(450, 100, 50,50);
        Image image5 = new Image(Main.class.getResource("/images/Avatar5.png").toString());
        rectangle5.setFill(new ImagePattern(image5));
        root.getChildren().add(rectangle5);


        rectangle1.setOnMouseClicked(event -> handleRectangleClick(image1));
        rectangle2.setOnMouseClicked(event -> handleRectangleClick(image2));
        rectangle3.setOnMouseClicked(event -> handleRectangleClick(image3));
        rectangle4.setOnMouseClicked(event -> handleRectangleClick(image4));
        rectangle5.setOnMouseClicked(event -> handleRectangleClick(image5));
    }

    private void handleRectangleClick(Image image) {
        App.getLoggedInUser().setAvatar(image);
    }

    public void back() throws Exception {
        (new ProfileMenu()).start(Main.stage);
    }

    public void setAvatar() {
        Image image = new Image(Main.class.getResource(adress.getText()).toString());
        handleRectangleClick(image);

    }


}
