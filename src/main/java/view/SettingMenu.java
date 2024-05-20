package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.App;

import java.io.IOException;
import java.net.URL;

public class SettingMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/Setting.fxml");
        assert url != null;
        BorderPane root = FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void backToMain(MouseEvent mouseEvent) throws IOException {
        (new MainMenu()).start(Main.stage);
    }

    public void easy() {
        App.getLoggedInUser().setLevel(1);
    }

    public void medium() {
        App.getLoggedInUser().setLevel(2);

    }

    public void hard() {
        App.getLoggedInUser().setLevel(3);
    }

    public void useWASD() {
        App.getLoggedInUser().setWasd(!App.getLoggedInUser().isWasd());
    }

    public void mute() {
        App.getLoggedInUser().setMute(!App.getLoggedInUser().isMute());
    }

    public void setGray(MouseEvent mouseEvent) {
        App.getLoggedInUser().setGray(!App.getLoggedInUser().isGray());
    }
}
