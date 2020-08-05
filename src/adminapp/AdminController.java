package adminapp;

import app.UserProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import user.User;
import user.Users;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonAddStudent;
    @FXML
    private TextField textFieldStudentsFirstName;
    @FXML
    private TextField textFieldStudentsLastName;
    @FXML
    private TextField textFieldStudentsEmail;
    @FXML
    private TableView<UserProperty> tableViewStudents;
    @FXML
    private TableColumn<UserProperty, Integer> tableStudentsColumnId;
    @FXML
    private TableColumn<UserProperty, String> tableStudentsColumnUsername;
    @FXML
    private TableColumn<UserProperty, String> tableStudentsColumnFirstName;
    @FXML
    private TableColumn<UserProperty, String> tableStudentsColumnLastName;
    @FXML
    private TableColumn<UserProperty, String> tableStudentsColumnEmail;

    @FXML
    private Button buttonAddTeacher;
    @FXML
    private TextField textFieldTeachersFirstName;
    @FXML
    private TextField textFieldTeachersLastName;
    @FXML
    private TextField textFieldTeachersEmail;
    @FXML
    private TableView<UserProperty> tableViewTeachers;
    @FXML
    private TableColumn<UserProperty, Integer> tableTeachersColumnId;
    @FXML
    private TableColumn<UserProperty, String> tableTeachersColumnUsername;
    @FXML
    private TableColumn<UserProperty, String> tableTeachersColumnFirstName;
    @FXML
    private TableColumn<UserProperty, String> tableTeachersColumnLastName;
    @FXML
    private TableColumn<UserProperty, String> tableTeachersColumnEmail;

    @FXML
    private Button buttonAddAdmin;
    @FXML
    private TextField textFieldAdminsFirstName;
    @FXML
    private TextField textFieldAdminsLastName;
    @FXML
    private TextField textFieldAdminsEmail;
    @FXML
    private TableView<UserProperty> tableViewAdmins;
    @FXML
    private TableColumn<UserProperty, Integer> tableAdminsColumnId;
    @FXML
    private TableColumn<UserProperty, String> tableAdminsColumnUsername;
    @FXML
    private TableColumn<UserProperty, String> tableAdminsColumnFirstName;
    @FXML
    private TableColumn<UserProperty, String> tableAdminsColumnLastName;
    @FXML
    private TableColumn<UserProperty, String> tableAdminsColumnEmail;

    private final String SQL_STATEMENT =
            "SELECT id, username, password, firstName, lastName, email FROM student";

    private void refreshStudentTable() {
        Users users = Users.getUsersInstance();
        users.downloadStudentData();
        List<User> studentList = users.getStudentList();
        tableViewStudents.setItems(makeObservableList(studentList,
                                                      tableStudentsColumnId,
                                                      tableStudentsColumnUsername,
                                                      tableStudentsColumnFirstName,
                                                      tableStudentsColumnLastName,
                                                      tableStudentsColumnEmail));
    }

    private void refreshTeacherTable() {
        Users users = Users.getUsersInstance();
        users.downloadTeacherData();
        List<User> teacherList = users.getTeacherList();
        tableViewTeachers.setItems(makeObservableList(teacherList,
                                                      tableTeachersColumnId,
                                                      tableTeachersColumnUsername,
                                                      tableTeachersColumnFirstName,
                                                      tableTeachersColumnLastName,
                                                      tableTeachersColumnEmail));
    }

    private void refreshAdminTable() {
        Users users = Users.getUsersInstance();
        users.downloadAdminData();
        List<User> adminList = users.getAdminList();
        tableViewAdmins.setItems(makeObservableList(adminList,
                                                    tableAdminsColumnId,
                                                    tableAdminsColumnUsername,
                                                    tableAdminsColumnFirstName,
                                                    tableAdminsColumnLastName,
                                                    tableAdminsColumnEmail));
    }

    /**
     * Makes the observable list of UserProperty objects based on given list of User objects
     * for columns passed as parameters.
     *
     * @param userList The input list of User objects
     * @param tableColumnId
     * @param tableColumnUsername
     * @param tableColumnFirstName
     * @param tableColumnLastName
     * @param tableColumnEmail
     * @return The observable list of UserProperty objects
     */
    private ObservableList<UserProperty> makeObservableList(List<User> userList,
                                                            TableColumn<UserProperty, Integer> tableColumnId,
                                                            TableColumn<UserProperty, String> tableColumnUsername,
                                                            TableColumn<UserProperty, String> tableColumnFirstName,
                                                            TableColumn<UserProperty, String> tableColumnLastName,
                                                            TableColumn<UserProperty, String> tableColumnEmail) {
        ObservableList<UserProperty> userPropertyObservableList;

        List<UserProperty> userPropertyList = UserProperty.makeUserPropertyList(userList);
        userPropertyObservableList = FXCollections.observableArrayList();
        userPropertyObservableList.addAll(userPropertyList);

        tableColumnId.setCellValueFactory(new PropertyValueFactory<UserProperty, Integer>("id"));
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("username"));
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("lastName"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("email"));

        return  userPropertyObservableList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshStudentTable();
        refreshTeacherTable();
        refreshAdminTable();
    }
}
