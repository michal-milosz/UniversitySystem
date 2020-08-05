package user;

import dbconnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentUser extends User {

    private static CurrentUser currentUserInstance = null;
    private static final String SQL_STATEMENT_ADMIN = "SELECT username, password FROM admin";
    private static final String SQL_STATEMENT_TEACHER = "SELECT username, password FROM teacher";
    private static final String SQL_STATEMENT_STUDENT = "SELECT username, password FROM student";
    private static final String SQL_STATEMENT_USER_DATA_PART_1 =
            "SELECT id, username, password, firstName, lastName, email FROM ";
    private static final String SQL_STATEMENT_USER_DATA_PART_2 = " WHERE username = ?";

    private CurrentUser() {}

    /**
     * Returns the instance of CurrentUser singleton class.
     * @return The instance of CurrentUser singleton class
     */
    public static CurrentUser getCurrentUserInstance() {
        if (currentUserInstance == null) {
            currentUserInstance = new CurrentUser();
        }
        return currentUserInstance;
    }

    public UserGroup authenticateUser(String username, String password) {
        logout();
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statementAdmin = connection.prepareStatement(SQL_STATEMENT_ADMIN);
             PreparedStatement statementTeacher = connection.prepareStatement(SQL_STATEMENT_TEACHER);
             PreparedStatement statementStudent = connection.prepareStatement(SQL_STATEMENT_STUDENT);
             ResultSet resultSetAdmin = statementAdmin.executeQuery();
             ResultSet resultSetTeacher = statementTeacher.executeQuery();
             ResultSet resultSetStudent = statementStudent.executeQuery()) {

            if (scanResultSet(resultSetStudent, username, password)) {
                userGroup = UserGroup.STUDENT;
            } else if (scanResultSet(resultSetTeacher, username, password)) {
                userGroup = UserGroup.TEACHER;
            } else if (scanResultSet(resultSetAdmin, username, password)) {
                userGroup = UserGroup.ADMIN;
            } else {
                userGroup = UserGroup.UNDEFINED;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userGroup != UserGroup.UNDEFINED)
            try {
                setUserData(username);
            } catch (Exception e) {
                e.printStackTrace();
            }

        return userGroup;
    }

    public void logout() {
        userGroup = UserGroup.UNDEFINED;
        id = 0;
        username = "";
        password = "";
        firstName = "";
        lastName = "";
        email = "";
    }

    /**
     * Checks if a pair of username and password is in the given ResultSet.
     * @param resultSet
     * @param username
     * @param password
     * @return true if a pair of given username and password is present in the given resultSet
     * @throws SQLException
     */
    private boolean scanResultSet(ResultSet resultSet, String username, String password) throws SQLException {
        while (resultSet.next()) {
            if (username.equals(resultSet.getString("username"))
             && password.equals(resultSet.getString("password"))) {
                return true;
            }
        }
        return false;
    }

    private void setUserData(String user) throws Exception {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_USER_DATA_PART_1 +
                                                                       userGroup.getDbTableName() +
                                                                       SQL_STATEMENT_USER_DATA_PART_2)) {
            statement.setString(1, user);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    username = user;
                    password = resultSet.getString("password");
                    firstName = resultSet.getString("firstName");
                    lastName = resultSet.getString("lastName");
                    email = resultSet.getString("email");
                } else {
                    throw new Exception("Empty result set");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
