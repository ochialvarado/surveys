
package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;

import model.User;

import util.DbUtil;



public class UserDao {



    private Connection connection;



    public UserDao() {

        connection = DbUtil.getConnection();

    }



    public void addUser(User user) {

        try {

            PreparedStatement preparedStatement = connection

                    .prepareStatement("insert into users(name,password,email,start_date) values (?, ?, ?, ? )");

            // Parameters start with 1

            preparedStatement.setString(1, user.getName());

            preparedStatement.setString(2, user.getPassword());
            
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.setDate(4, new java.sql.Date(user.getStartDate().getTime()));

            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    public void deleteUser(int userId) {

        try {

            PreparedStatement preparedStatement = connection

                    .prepareStatement("delete from users where user_id=?");

            // Parameters start with 1

            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();



        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    public void updateUser(User user) {

        try {

            PreparedStatement preparedStatement = connection

                    .prepareStatement("UPDATE users SET name=?, password=?, email=?" +

                            "WHERE user_id=?");

            // Parameters start with 1

            preparedStatement.setString(1, user.getName());

            preparedStatement.setString(2, user.getPassword());

            preparedStatement.setString(3, user.getEmail());

            preparedStatement.setInt(4, user.getUserId());

            preparedStatement.executeUpdate();



        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<User>();

        try {
            System.out.println(connection);
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM \"users\"");
            
            
            System.out.println(rs);

            while (rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));

                user.setName(rs.getString("name"));
                
                user.setEmail(rs.getString("email"));

                users.add(user);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }



        return users;

    }

    public Integer login(String username, String password) {
        User user = new User();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"users\" WHERE \"email\"=\'"+ username +"\' AND \"password\"=\'" + password+"\'");

            if (rs.next()) {                
                return rs.getInt("user_id");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }

    public User getUserById(int userId) {

        User user = new User();

        try {

            PreparedStatement preparedStatement = connection.

                    prepareStatement("SELECT * FROM \"users\" WHERE \"user_id\"=?");

            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();



            if (rs.next()) {

                user.setUserId(rs.getInt("user_id"));

                user.setName(rs.getString("name"));

                user.setEmail(rs.getString("email"));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }



        return user;

    }

}


