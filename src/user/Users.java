package user;

import dbconnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Users {
    private static Users usersInstance = null;
    private List<User> studentList;
    private List<User> teacherList;
    private List<User> adminList;
    private static final String SQL_STATEMENT =
            "SELECT id, username, password, firstName, lastName, email FROM ";

    private Users() {}

    public List<User> getStudentList() {
        return studentList;
    }

    public List<User> getTeacherList() {
        return teacherList;
    }

    public List<User> getAdminList() {
        return adminList;
    }

    /**
     * Returns the instance of Users singleton class.
     * @return The instance of Users singleton class
     */
    public static Users getUsersInstance() {
        if (usersInstance == null) {
            usersInstance = new Users();
        }
        return usersInstance;
    }

    /**
     * Downloads student data from the database and saves is in studentList.
     */
    public void downloadStudentData() {
        studentList = new ArrayList<>();
        downloadUserData(UserGroup.STUDENT, studentList);
    }

    /**
     * Downloads teacher data from the database and saves is in teacherList.
     */
    public void downloadTeacherData() {
        teacherList = new ArrayList<>();
        downloadUserData(UserGroup.TEACHER, teacherList);
    }

    /**
     * Downloads admin data from the database and saves is in adminList.
     */
    public void downloadAdminData() {
        adminList = new ArrayList<>();
        downloadUserData(UserGroup.ADMIN, adminList);
    }

    /**
     * Downloads data from the database table corresponding to given userGroup and saves it to
     * the list passed as userList parameter.
     * @param userGroup Specifies table do download the data from (admin/teacher/student)
     * @param userList The output list
     */
    private void downloadUserData(UserGroup userGroup, List<User> userList) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_STATEMENT + userGroup.getDbTableName());
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                userList.add(new User(userGroup,
                                      resultSet.getInt("id"),
                                      resultSet.getString("username"),
                                      resultSet.getString("password"),
                                      resultSet.getString("firstName"),
                                      resultSet.getString("lastName"),
                                      resultSet.getString("email")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
