package com.mylab021.dao;


import com.mylab021.pojo.User;
import org.junit.Test;

import java.sql.*;

public class JdbcTest {

    @Test
    public void JdbcTest01() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF-8",
                    "root",
                    "mylab@123");

            System.out.println("connection: "+ connection);

            String sql = "SELECT * FROM user WHERE username = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "lucy");

            System.out.println("preparedStatement: " + preparedStatement);

            resultSet = preparedStatement.executeQuery();

            System.out.println("resultSet: " + resultSet);
            User user = new User();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                user.setId(id);
                user.setUsername(username);
            }
            System.out.println(user);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
