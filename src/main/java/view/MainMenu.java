package view;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.App;
import model.GameModel;

import java.io.IOException;
import java.net.URL;

public class MainMenu extends Application {
    MainController controller = new MainController();
    public void start(Stage stage) throws IOException {
        URL url = Main.class.getResource("/FXML/Main.fxml");
        assert url != null;
        Pane root = FXMLLoader.load(url);


        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setLayoutX(295);
        hBox.setLayoutY(110);
        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setText(App.getLoggedInUser().getUsername());
        hBox.getChildren().add(text);
        root.getChildren().add(hBox);


        Rectangle rectangle = new Rectangle(270, 30, 50,50);
        rectangle.setFill(new ImagePattern(App.getLoggedInUser().getAvatar()));
        root.getChildren().add(rectangle);


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void startGame() throws Exception {
        GameModel gameModel = new GameModel();
        App.getLoggedInUser().setGame(gameModel);
        (new Game()).start(Main.stage);
    }

    public void continueGame() throws IOException {
        if(App.getLoggedInUser().getLastGame()!=null){
            GameModel gameModel = App.getLoggedInUser().getLastGame();
            gameModel.setPause(false);
            (new Game()).start(Main.stage);
        }
    }

    public void goToProfileMenu() throws Exception {
        (new ProfileMenu()).start(Main.stage);
    }

    public void goToScoresChart() throws Exception {
        (new ScoresMenu()).start(Main.stage);
    }

    public void goToSetting() throws Exception {
        (new SettingMenu()).start(Main.stage);
    }

    public void logout() throws Exception {
        (new StartMenu()).start(Main.stage);
    }

}
