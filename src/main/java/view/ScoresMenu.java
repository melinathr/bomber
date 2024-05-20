package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

public class ScoresMenu extends Application {
    public HBox one;
    public HBox two;
    public HBox three;
    Text text1 = new Text();
    Text text2 = new Text();
    Text text3 = new Text();

    @Override
    public void start(Stage stage) throws Exception {

        URL url = Main.class.getResource("/FXML/Scores.fxml");
        assert url != null;
        BorderPane root = FXMLLoader.load(url);

        ArrayList<User> users = byKill();
        show(users, root, Main.stage);
    }

    public void backToMain(MouseEvent mouseEvent) throws IOException {
        (new MainMenu()).start(Main.stage);
    }

    private ArrayList<User> byKill() {
        ArrayList<User> sortUser = User.getAllUsers();
        sortUser.sort(Comparator.comparing(User::getBestGameByKill).reversed());
        return sortUser;
    }

    private ArrayList<User> byLevel() {
        ArrayList<User> sortUser = User.getAllUsers();
        sortUser.sort(Comparator.comparing(User::getBestGameByLevel).reversed());
        return sortUser;
    }

    private ArrayList<User> byAccuracy() {
        ArrayList<User> sortUser = User.getAllUsers();
        sortUser.sort(Comparator.comparing(User::getBestGameByAccuracy).reversed());
        return sortUser;
    }

    public void kill() throws IOException {
        URL url = Main.class.getResource("/FXML/Scores.fxml");
        assert url != null;
        BorderPane root = FXMLLoader.load(url);

        ArrayList<User> users = byKill();
        show(users, root, Main.stage);
    }

    public void level() throws IOException {
        URL url = Main.class.getResource("/FXML/Scores.fxml");
        assert url != null;
        BorderPane root = FXMLLoader.load(url);

        ArrayList<User> users = byLevel();
        show(users, root, Main.stage);
    }

    public void accuracy() throws IOException {
        URL url = Main.class.getResource("/FXML/Scores.fxml");
        assert url != null;
        BorderPane root = FXMLLoader.load(url);

        ArrayList<User> users = byAccuracy();
        show(users, root, Main.stage);
    }

    private void show(ArrayList<User> users, BorderPane root, Stage stage) {
        if (!users.isEmpty()) {
            text1.setText(users.get(0).getUsername());
            text1.setX(290);
            text1.setY(140);
            root.getChildren().add(text1);
        }
        if (users.size() > 1) {
            text2.setText(users.get(1).getUsername());
            text2.setX(290);
            text2.setY(180);
            root.getChildren().add(text2);
        }
        if (users.size() > 2) {
            text3.setText(users.get(2).getUsername());
            text3.setX(290);
            text3.setY(220);
            root.getChildren().add(text3);
        }
        stage.setScene(new Scene(root));
        stage.show();
    }
}