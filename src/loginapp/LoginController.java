package loginapp;

import adminapp.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import user.CurrentUser;
import user.UserGroup;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    private Button buttonLogin;
    @FXML
    private Label labelWrongCredentials;

    private Stage getStage() {
        return (Stage)buttonLogin.getScene().getWindow();
    }

    public void hideStage() {
        getStage().hide();
    }

    public void showStage() {
        getStage().show();
    }

    @FXML
    public void buttonLoginClicked(ActionEvent event) {
        CurrentUser currentUser = CurrentUser.getCurrentUserInstance();
        UserGroup userGroup = currentUser.authenticateUser(textFieldUsername.getText(),
                                                    passwordFieldPassword.getText());
        if (userGroup == UserGroup.UNDEFINED) {
            labelWrongCredentials.setText("Wrong Credentials");
        } else {
            hideStage();
            switch (userGroup) {
                case STUDENT:
                    studentLogin();
                    break;
                case TEACHER:
                    teacherLogin();
                    break;
                case ADMIN:
                    adminLogin();
                    break;
                default:
                    break;
            }
        }
    }

    private void studentLogin() {
        openDashboard("/studentapp/student.fxml", "Student Dashboard");
    }

    private void teacherLogin() {
        openDashboard("/teacherapp/teacher.fxml", "Teacher Dashboard");
    }

    private void adminLogin() {
        openDashboard("/adminapp/admin.fxml", "Admin Dashboard");
    }

    private void openDashboard(String fxmlPath, String title) {
        try {
            Stage stage = new Stage();
            Pane pane = FXMLLoader.load(getClass().getResource(fxmlPath));
            stage.setScene(new Scene(pane));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
