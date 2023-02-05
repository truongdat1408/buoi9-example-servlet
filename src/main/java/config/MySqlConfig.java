package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConfig {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3307/crm_app";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "123456";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            //Đăng ký sử dụng driver cho cơ sở dữ liệu MySQL
            Class.forName(JDBC_DRIVER);
            // Mở kết nối tới CSDL theo
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Lỗi Kết nối CSDL: " + e.getMessage());
        }

        return connection;
    }

}
