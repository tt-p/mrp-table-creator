package GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {


    public void start(Stage primaryStage) {
        InputWin inputWin = new InputWin();
        primaryStage = inputWin;
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
