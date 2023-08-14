import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UniqueAppName extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file for the main screen
        FXMLLoader fxmlLoader = new FXMLLoader(UniqueAppName.class.getResource("MainScreen.fxml"));

        // Create a new Scene using the loaded FXML content
        Scene mainScene = new Scene(fxmlLoader.load());

        // Set the title of the application window
        primaryStage.setTitle("Application Title");

        // Set the Scene for the primary stage
        primaryStage.setScene(mainScene);

        // Make the application window non-resizable
        primaryStage.setResizable(false);

        // Display the primary stage with the loaded Scene
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}
