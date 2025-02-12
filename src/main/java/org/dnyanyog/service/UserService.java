package org.dnyanyog.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.dnyanyog.common.DBUtils;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // ✅ Fetch user by ID
    public UserResponse getUserById(int id) {
        UserResponse userResponse = new UserResponse();  // Create a new instance of UserResponse
        String query = "SELECT * FROM user WHERE user_id = " + id;  // Unsafe concatenation for simplicity
        
        try {
            ResultSet rs = DBUtils.executeSelectQuery(query);
            if (rs.next()) {
                userResponse.setUserId(rs.getInt("user_id"));
                userResponse.setUserName(rs.getString("user_name"));
                userResponse.setUserEmail(rs.getString("user_email"));
                userResponse.setUserMobile(rs.getString("user_mobile"));
                userResponse.setResponseCode("0000");
                userResponse.setResponseMessage("User details fetched successfully!");
            } else {
                userResponse.setResponseCode("911");
                userResponse.setResponseMessage("User not found!");
            }
            rs.close();
        } catch (SQLException e) {
            userResponse.setResponseCode("911");
            userResponse.setResponseMessage("Database error: " + e.getMessage());
        }
        return userResponse;
    }

    // ✅ Add new user
    public UserResponse saveUser(UserRequest user) {
        UserResponse userResponse = new UserResponse();  // Create a new instance
        String query = "INSERT INTO user (user_name, user_email, user_mobile) VALUES (?, ?, ?)";  // Parameterized query

        try {
            // Execute the DML query with parameters using PreparedStatement
            DBUtils.executeDMLQuery(query, user.getName(), user.getEmail(), user.getMobile());

            // Set the response fields
            userResponse.setUserName(user.getName());
            userResponse.setUserEmail(user.getEmail());
            userResponse.setUserMobile(user.getMobile());
            userResponse.setResponseCode("0000");
            userResponse.setResponseMessage("User added successfully!");
        } catch (SQLException e) {
            userResponse.setResponseCode("911");
            userResponse.setResponseMessage("User addition failed: " + e.getMessage());
        }
        return userResponse;}


    // ✅ Get all users
    public List<UserResponse> getAllUsers() {
        List<UserResponse> userListResponse = new ArrayList<>();
        String query = "SELECT * FROM user";
        
        try {
            ResultSet rs = DBUtils.executeSelectQuery(query);
            while (rs.next()) {
                UserResponse userResponse = new UserResponse();  // Create a new instance of UserResponse
                userResponse.setUserId(rs.getInt("user_id"));
                userResponse.setUserName(rs.getString("user_name"));
                userResponse.setUserEmail(rs.getString("user_email"));
                userResponse.setUserMobile(rs.getString("user_mobile"));
                userResponse.setResponseCode("0000");
                userResponse.setResponseMessage("User details fetched successfully!");
                userListResponse.add(userResponse);
            }
            rs.close();
        } catch (SQLException e) {
            UserResponse errorResponse = new UserResponse();  // Create a new instance of UserResponse for error
            errorResponse.setResponseCode("911");
            errorResponse.setResponseMessage("Database error: " + e.getMessage());
            userListResponse.add(errorResponse);
        }
        return userListResponse;
    }
}
