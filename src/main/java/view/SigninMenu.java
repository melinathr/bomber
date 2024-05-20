package view;

import controller.SigninController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Result;
import model.User;

import java.net.URL;

public class SigninMenu extends Application {
    public TextField username;
    public TextField password;
    public TextField passwordConfirm;
    SigninController controller = new SigninController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/Signin.fxml");
        assert url != null;
        BorderPane root = FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void backToStart() throws Exception {
        (new StartMenu()).start(Main.stage);
    }

    public void checkInfo() throws Exception {
        Result result = controller.checkInfo(username.getText(), password.getText(), passwordConfirm.getText());
        if(!result.isSuccessful()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("error signing in");
            alert.setHeaderText("can't sign in.");
            alert.setContentText(result.toString());
            alert.show();
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("sign in successfully");
            alert.setHeaderText(":)");
            alert.setContentText(result.toString());
            alert.show();

            (new StartMenu()).start(Main.stage);
        }
    }
}
