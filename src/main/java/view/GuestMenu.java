package view;

import controller.Logincontroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Result;

import java.io.IOException;
import java.net.URL;

public class GuestMenu extends Application {
    public TextField username;
    Logincontroller controller = new Logincontroller();


    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/Guest.fxml");
        assert url != null;
        BorderPane root = FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void loginAsGuest() throws IOException {
        Result result = controller.loginAsGuest(username.getText());
        if (!result.isSuccessful()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("error to login in as guest");
            alert.setHeaderText("can't login.");
            alert.setContentText(result.toString());
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("login successfully");
            alert.setHeaderText(":)");
            alert.setContentText(result.toString());
            alert.show();

            (new MainMenu()).start(Main.stage);
        }
    }

    public void backToStart(MouseEvent mouseEvent) throws Exception {
        (new StartMenu()).start(Main.stage);
    }
}
