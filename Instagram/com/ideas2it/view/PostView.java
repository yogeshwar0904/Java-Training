package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.constant.Constant;
import com.ideas2it.controller.PostController;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Post;
import com.ideas2it.model.User;
/**
 * add the post for user
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class PostView {
    private PostController postController;
    private Scanner scanner;

    public PostView() {
        this.postController = new PostController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Adds post in user 
     * account. 
     */ 
    public void postMenu(User user) {
        StringBuilder userControl = new StringBuilder();   
        int choice;
        String backToMenu = "";

        do {
            userControl.append(" Enter 1 for add post") 
                       .append("\n Enter 2 for display post")
                       .append("\n Enter 3 for remove post")
                       .append("\n Enter 4 for update post");

            try {
                System.out.println(userControl);
                choice = scanner.nextInt();
                scanner.skip("\r\n");

                switch (choice) {
                case Constant.ADD_POST:
                    uploadPost(user);
                    break;

                case Constant.DISPLAY_POST:
                    displayPost(user);
                    break;

                case Constant.REMOVE_POST:
                    deletePost();
                    break;

                case Constant.UPDATE_POST:
                    update();
                    break;

                default:
                    CustomLogger.warn("Entered value is Invalid!!");
                    break;
                }
            } catch (InputMismatchException inputMismatch) {
                CustomLogger.error("Enter only Numbers");
                scanner.next();
            }
                userControl.setLength(0);
                System.out.println("Enter Y for continue \nEnter any key for Exit");
                backToMenu = scanner.next();
        } while (backToMenu.equalsIgnoreCase("Y"));
    }

    /**
     * upload the post for user
     *
     * @param String account name
     *        account name of user.
     */
    private void uploadPost(User user) { 
        String title;
        String content;
        Post post;
        System.out.println("Enter the post title");  
        title = scanner.nextLine();
        System.out.println("Enter contents of your post");
	content = scanner.nextLine();
        post = postController.uploadPost(user, title, content);

        if(null == post) {
	    System.out.println("Post not created");
        } else {
            System.out.println("Post created successfully");
            System.out.println(post);
        }
    }

    /**
     * display the post
     */ 
    private void displayPost(User user) {
        List<Post> posts;
        posts = postController.getUserPost(user); 

        if (null != posts) {
            System.out.println(posts);
        } else {
            System.out.println("No Post Uploaded yet");            
        }  
    }

    /**
     * To delete the post.
     */ 
    private void deletePost() {
        String postId;
        System.out.println("Enter the post Id");
        postId = scanner.nextLine();

        if (postController.delete(postId)) {
            System.out.println("Post is deleted");
        } else {
            System.out.println("Something went wrong");
        }        
    }

    /**
     * update the Account    
     */
    private void update() {
        StringBuilder postUpdate = new StringBuilder();
        int choice;
        System.out.println("enter post id to update");
        String postId = scanner.next();
        Post post = null; 

        try {
            postUpdate.append(" Enter 1 for update post content")
                      .append("\n Enter 2 for update post tittle");

            System.out.println(postUpdate);
            choice = scanner.nextInt();
            scanner.skip("\r\n");     
        
            switch (choice) {

            case Constant.UPDATE_POST_CONTENT:
                String updatePostContent;
                updatePostContent = getContent();
                post = postController.update(content, updatePostContent,
                                           Constant.UPDATE_POST_CONTENT);
                break;

            case Constant.UPDATE_POST_TITLE:
                String updatePostTitle;
                updatePostTitle = getTitle();
                post = postController.update(title, updatePostTitle,
                                                  Constant.UPDATE_POST_TITLE);
                break;

            default:
                postUpdate.delete(0, postUpdate.length() - 1);
                postUpdate.append("Entered value is Invalid!! ")
                           .append("\n enter correct option to update");
                CustomLogger.warn(postUpdate.toString());
                break;
            }

            if (postId.equals(post.getPostId())) {
                CustomLogger.info("post updated Successfully");
            } else {
                CustomLogger.info("post not updated");
            }
 
        } catch (InputMismatchException intputMismatch) {
            CustomLogger.error("Enter only Numbers"); 
            scanner.next();     
        }
    }
}