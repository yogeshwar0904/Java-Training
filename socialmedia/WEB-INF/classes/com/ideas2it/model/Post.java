package com.ideas2it.model;

import com.ideas2it.model.User;

/**
 * upload the post for user.
 *
 * @version     1.0 13 Sept 2022
 * @author      Yogeshwar
 */
public class Post {
    String userId;
    String postId;
    String postLocation;
    String accountName;
 
    public Post(String userId, String postId, String postLocation, String accountName) {
        this.userId = userId;
        this.postId = postId;
        this.postLocation = postLocation;
        this.accountName = accountName;
    }

    public Post(String accountName, String postLocation) {
        this.accountName = accountName;
        this.postLocation = postLocation;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public String getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(String postLocation) {
        this.postLocation = postLocation;
    } 


   public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    } 
  
    public void setUserId(String userId) {
        this.userId = userId;    
    }

    public String toString() {
        StringBuilder post = new StringBuilder();
        post.append("\nUserId : ").append(userId)
            .append("\nPostId : ").append(postId)
            .append("\nAccountname : ").append(accountName)
            .append("\nPostLocation : ").append(postLocation);

        return post.toString();
    }
}