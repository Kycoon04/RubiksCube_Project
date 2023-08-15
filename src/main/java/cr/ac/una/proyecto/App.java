package cr.ac.una.proyecto;

import cr.ac.una.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
/*
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.setTitle("Rubik's Cube");
        FlowController.getInstance().goMain("MainView");
    }
    public static void main(String[] args) {
        launch(args);
    }
}