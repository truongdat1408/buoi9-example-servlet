package repository;

import config.MySqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {
    public int countUsersByEmailAndPassword(String email, String password){
        Connection connection = MySqlConfig.getConnection();
        int count = 0;
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT count(*) AS count FROM users WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException throwables) {
            System.out.println("Lỗi query login trong doPost: " + throwables.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return count;
    }
}
