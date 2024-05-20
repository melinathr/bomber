package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.App;

import java.net.URL;

public class StartMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/Start.fxml");
        assert url != null;
        BorderPane root = FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void signin() throws Exception {
        (new SigninMenu()).start(Main.stage);
    }

    public void login() throws Exception {
        (new LoginMenu()).start(Main.stage);
    }

    public void playAsGuest() throws Exception {
        (new GuestMenu()).start(Main.stage);
    }
}
