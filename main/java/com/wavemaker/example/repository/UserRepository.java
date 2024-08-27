package com.wavemaker.example.repository;

import com.wavemaker.example.model.User;
import com.wavemaker.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private final Connection connection;
    public UserRepository() throws SQLException {
        this.connection= DatabaseConnection.connectToDatabase();
    }
    public User findByUserName(String username)
    {
        User user=null;
        String query="Select * from USERS where user_name=?";
        try(PreparedStatement stmt=connection.prepareStatement(query))
        {
            stmt.setString(1,username);
            ResultSet rs= stmt.executeQuery();
            if(rs.next())
            {
                user=new User();
                user.setUserId(rs.getInt("ID"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
