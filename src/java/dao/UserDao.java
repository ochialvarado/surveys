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
            Statement statement = connection.createStatement();
            String sqlString = "INSERT INTO \"users\" (\"name\",\"password\",\"email\") VALUES (\'"+ user.getName() +"\',\'"+ user.getPassword() +"\', \'"+ user.getEmail() +"\')";
            System.out.println(sqlString);
            statement.executeUpdate(sqlString);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            Statement statement = connection.createStatement();
            String sqlString = "DELETE FROM \"users\" WHERE \"user_id\"= " + userId;
            System.out.println(sqlString);
            statement.executeUpdate(sqlString);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
           Statement statement = connection.createStatement();
           String sqlString = "UPDATE \"users\" SET \"name\"=\'"+ user.getName() +"\', \"password\"=\'"+ user.getPassword() +"\', \"email\"=\'"+ user.getEmail() +"\' WHERE \"user_id\"="+ user.getUserId();
           System.out.println(sqlString);
           statement.executeUpdate(sqlString);

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
            System.out.println(connection);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"users\" WHERE \"user_id\"="+ userId );
            System.out.println(rs);

            if (rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
}