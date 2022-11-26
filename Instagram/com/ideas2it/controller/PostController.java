package com.ideas2it.controller;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.constant.Constant;
import com.ideas2it.exception.InstagramManagementException;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Post;
import com.ideas2it.service.PostService;
import com.ideas2it.model.User;

/**
 * Get the information from PostView and pass 
 * to PostService
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class PostController {
    PostService postService;

    public PostController() {
        this.postService = new PostService();
    }

    /**
     * upload the post for user
     *
     * @param String userId
     *        account id of the user
     * @param String postLocation
     *        location of post taken
     */
    public Post uploadPost(User user, String title, String content)   {
        try {
            return postService.uploadPost(user, title, content);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * display the post of the user
     *
     * @param String userId
     *        id of the user
     * @return userPosts
     *         posts of the user
     */
    public List<Post> getUserPost(User user) {
        List<Post> posts;
        posts = null; 

        try {
            posts = postService.displayPost(user);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return posts;
    }

    /**
     * Delete the post based on the postId 
     * 
     * @param  postId 
     *         id of the post
     * @return bolean 
     *         true or false based 
     */
    public boolean delete(String postId) {
        try {
	    System.out.println(postId);
            return postService.delete(postId);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return false;
    }


    /**
     * update the user
     *
     * @param string accountName
     *        account name of user
     * @param string updateValue
     *        change detail  of user
     * @param int choice
     *        choice of user
     * @return user
     *         details of user
     *          
     */   
    public Post update(String postId, String updateValue, int choice) { 
        try {
            return postService.update(postId, updateValue, choice); 
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        } 
        return null; 
    } 
}