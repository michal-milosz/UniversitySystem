package loginapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {
    /*
    To run this application from .jar file using command line, in cmd type:
    C:\Users\Michal\IdeaProjects\UniversitySystem>
    java --module-path "C:\Users\Michal\IdeaProjects\Libraries\JavaFX_library\openjfx-11.0.2_windows-x64_bin-sdk\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar out/artifacts/SchoolSystem_jar/SchoolSystem.jar
     */

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
