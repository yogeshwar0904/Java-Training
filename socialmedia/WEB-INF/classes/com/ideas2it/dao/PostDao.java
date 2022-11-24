package com.ideas2it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.model.User;
import com.ideas2it.model.Post;

/**
 * To perform create,update,search and detele for the user
 *
 * @version    1.2 03 Nov 2022
 * @author     Yogeshwar
 */
public class PostDao {

    Connection connection;  
    String query;    
    PreparedStatement statement;

    /**
     * upload the user post
     *
     * @param UserPost userPost
     * @return boolean true if post is uploaded
     */
    public int create(Post post) {   
        int noOfRowsAffected = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
             .append("post(user_id, post_id, post_location, account_name) ")
             .append("VALUES(?,?,?,?,now());");

        try {     
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString()); 
            statement.setString(1,post.getUserId());
            statement.setString(2,post.getPostId());
            statement.setString(3,post.getPostLocation());
            statement.setString(4,post.getAccountName());
            noOfRowsAffected = statement.executeUpdate(); 
            statement.close();
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return noOfRowsAffected;
    }

   public List<Post> displayPost(String userId) {
        List<Post> postOfParticularUser = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT post.user_id, profile.username as posted_user_name, ")
             .append("post.content, post.like_count, post.comment_count ")
             .append("FROM post JOIN profile ON profile.user_id = post.user_id ")
             .append("WHERE post.user_id = ?;");
        ResultSet resultSet;
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,userId);
            resultSet = statement.executeQuery();            
            postOfParticularUser = new ArrayList<>();

            while (resultSet.next()){
                Post post = new Post(resultSet.getString("user_id"),
                                    resultSet.getString("post_id"),
                                   resultSet.getString("post_location"),
                                   resultSet.getString("account_name"));
                postOfParticularUser.add(post);               
            } 
            statement.close(); 
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        } 
        return postOfParticularUser;       
    }

    public int delete(String postId) { 
        int noOfRowsDeleted = 0;
        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement("DELETE FROM post WHERE post_id = ?");
            statement.setString(1, postId);
            noOfRowsDeleted = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return noOfRowsDeleted;
    } 
}