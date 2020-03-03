package Ventanas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VentanaLogin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("../layout/logIn.fxml"));
        Scene scene = new Scene(root,600,700);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void inicializar(){
        launch();
    }
}
