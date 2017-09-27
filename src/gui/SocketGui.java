package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SocketGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SocketGui_fxml.fxml"));
        primaryStage.setTitle("Socket GUI");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }


}
