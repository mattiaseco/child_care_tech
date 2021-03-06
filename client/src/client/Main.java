package client;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)throws Exception{
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        stage.setTitle("");
        stage.setScene(new Scene(root, 600, 400));
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.show();

        /*SocketClient client = new SocketClient("127.0.0.1", 1337);
        try {
            client.startClient();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }*/

    }

}