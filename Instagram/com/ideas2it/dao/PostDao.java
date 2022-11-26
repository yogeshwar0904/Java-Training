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
    private Connection connection;  
    private String query;    
    PreparedStatement statement;

    /**
     * upload the user post
     *
     * @param UserPost userPost
     * @return boolean true if post is uploaded
     */
    public Post uploadPost(User user, Post post) { 
        int count = 0;  
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
             .append("post(user_id, post_id, content, title) ")
             .append("VALUES(?,?,?,?)");
        System.out.println("query " + query);

        try {     
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString()); 
            statement.setString(1,user.getUserId());
            statement.setString(2,post.getPostId());
            statement.setString(3,post.getTitle());
            statement.setString(4,post.getContent());
            count = statement.executeUpdate(); 
            statement.close();
            if(count>0) {
                return post;
            } else {
                return null;
            }
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return post;
    }

    public List<Post> displayPost(User user) {
        List<Post> posts = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM post")
             .append(" WHERE user_id = ?");
        ResultSet resultSet;
        Post post;
        System.out.println(query);
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,user.getUserId());
            resultSet = statement.executeQuery();            
            while (resultSet.next()){
                post = new Post();
                post.setPostId(resultSet.getString("post_id"));
                post.setContent(resultSet.getString("content"));
                                   post.setTitle(resultSet.getString("title"));
                posts.add(post);               
            } 
            statement.close(); 
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        } 
        return posts;       
    }

    public int delete(String postId) { 
        int noOfRowsDeleted = 0;
        System.out.println(postId);
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM post")
             .append(" WHERE post_id = ?");
        System.out.println(query);
        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query.toString());
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

    /**
     * update the user
     *
     * @param accountName
     *        account name of user
     * @param updatevalue
     *        update detail of user
     * @param choice
     *        choice of user
     * @return Map<String, User>
     *         account of user 
     */   
    public Post update (String postId, Post post, String userId) {
        try {
            connection = DatabaseConnection.getConnection();
            StringBuilder query = new StringBuilder();
            query.append("UPDATE post SET content = ?, title = ?")
                 .append("WHERE post_id = ?");
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,post.getContent())
                     .setString(2,post.getTitle())
                     .setString(3,post.getPostId());
                     //.setString(4,post.getUserId());
            statement.execute();
            statement.close();
        } catch(SQLException exception) {
            CustomLogger.error("SQL exception occure");
            exception.printStackTrace(); 
        } finally {
            DatabaseConnection.closeConnection();
        }
        return post;   
    }
}