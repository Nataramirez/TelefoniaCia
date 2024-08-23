package co.edu.Telefonia.app;

import co.edu.Telefonia.modelos.TelefoniaCia;
import co.edu.Telefonia.modelos.enums.TipoPantalla;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelefoniaCiaApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(TelefoniaCiaApp.class.getResource(TipoPantalla.INICIO.getRuta()));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle(TipoPantalla.INICIO.getNombre());
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(TelefoniaCiaApp.class, args);
    }
}
