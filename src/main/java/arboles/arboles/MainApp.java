package arboles.arboles;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("vista.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/arboles/arboles/style/estilos.css").toExternalForm()); // Ruta al CSS

        primaryStage.setTitle("Árbol Binario");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
