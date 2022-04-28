package actives.database;

import actives.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://192.168.1.3:3306/activity?autoReconnect=true&useSSL=false&serverTimezone=Europe/London";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "w1", "Qw12345678");
        return dbConnection;
    }

    public ObservableList<User> getUserById(User user) {
        try {
            PreparedStatement statement = getDbConnection().prepareStatement("select * from user where id_user = ?");
            statement.setInt(1, user.getId_user());
            ResultSet result = statement.executeQuery();
            ObservableList<User> list = FXCollections.observableArrayList();
            while (result.next()) {
                User users = new User();
                users.setId_user(result.getInt("id_user"));
                users.setPassword(result.getString("password"));
                list.add(users);
            }
            return list;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
