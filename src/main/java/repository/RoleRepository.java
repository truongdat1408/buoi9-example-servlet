package repository;

import config.MySqlConfig;
import model.RoleColumn;
import model.RoleModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {

    public int addNewRole(String name, String desc) {
        int isSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "INSERT INTO roles(name, description) VALUE (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, desc);
            isSuccess = statement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Lỗi query addNewRole: " + throwables.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    public int deleteRoleById(int id) {
        int isDeleteSuccess = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "DELETE FROM roles r WHERE r.id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            isDeleteSuccess = statement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Lỗi query deleteRoleById: " + throwables.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return isDeleteSuccess;
    }

    public List<RoleModel> getAllRoles() {
        Connection connection = MySqlConfig.getConnection();
        List<RoleModel> listRoles = new ArrayList<>();
        String query = "SELECT * FROM roles";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(RoleColumn.ID.getValue());
                String name = resultSet.getString(RoleColumn.NAME.getValue());
                String description = resultSet.getString(RoleColumn.DESCRIPTION.getValue());

                RoleModel roleModel = new RoleModel();
                roleModel.setId(id);
                roleModel.setRoleName(name);
                roleModel.setDescription(description);

                listRoles.add(roleModel);
            }
        } catch (SQLException throwables) {
            System.out.println("Lỗi query getAllRoles: " + throwables.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return listRoles;
    }
}
