package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.App;

import java.io.IOException;
import java.net.URL;

public class EndGameMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/GameOver.fxml");
        assert url != null;
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setLayoutX(250);
        hBox.setLayoutY(200);
        Text text = new Text();
        String str = App.getLoggedInUser().getGame().getEndGameSituation() +
                "\nkills: " + App.getLoggedInUser().getGame().getKill() + "\n" +
                "accuracy: " + (int)App.getLoggedInUser().getGame().getAccuracy();
        text.setText(str);
        text.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
        hBox.getChildren().add(text);
        root.getChildren().add(hBox);

        stage.setScene(scene);
        stage.show();
    }

    public void GoToMainMenu(MouseEvent mouseEvent) throws IOException {
        (new MainMenu()).start(Main.stage);
    }
}
