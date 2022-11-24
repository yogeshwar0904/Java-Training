package com.ideas2it.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.constant.Constant;
import com.ideas2it.exception.InstagramManagementException;
import com.ideas2it.model.User;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.service.InstagramService;
import com.ideas2it.util.InstagramUtil;

/**
 * Get the information from UserView and pass 
 * to InstagramService
 *
 * @version     2.0 14 Sept 2022
 * @author      Yogeshwar
 */


public class InstagramController extends HttpServlet {
    private InstagramService instagramService;
    private InstagramUtil instagramUtil;

    public  InstagramController() {
        instagramService = new InstagramService(); 
        instagramUtil = new InstagramUtil();
    }
 
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }

    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
         
        switch (action) {
        case "/create":
            break;
 
        case "/update":
            break;

        case "/search":
            break;

        case "/display":
            displayUser(request, response);
            break;

        case "/delete":
            break;

        default:
            break; 
        }  
    }

    private void displayUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    private void createUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName;
        String name;
        String mobileNumber;
        String password;
        String message;
        User user = null;
        String action = request.getServletPath();
 
        accountName = request.getParameter("accountname");
        name = request.getParameter("username");
        mobileNumber = request.getParameter("mobilenumber");
        password = request.getParameter("password");
        user = new User();
        user.setAccountName(accountName);
        user.setUserName(name);
        user.setMobileNumber(Long.parseLong(mobileNumber));
        user.setPassword(password);
        message = "Account Created SuccessFully";
        request.setAttribute("Message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user.jsp");
        requestDispatcher.forward(request, response);
        response.sendRedirect("login");
   }
 
    /**
     * add the user
     *
     * @param accountName
     *        account name of user
     * @param users
     *        details of the user
     * @return users
     *        details of the user           
     */ 
    public User add(User user) {
        return instagramService.add(user);    
    }

    /**
     * remove the user
     *
     * @param accountName 
     *        name of the account
     * @param password
     *        password of the account
     * @return null if sucessfully deleted         
     */  
    public boolean deleteAccount(String accountName, String password) { 
        try {
            return instagramService.deleteAccount(accountName, password);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return false;
    }

    /** 
     * search the user
     *
     * @param String accountName 
     *        account name of user
     * @return user
     *         account name of user   
     */   
    public User search(String accountName) { 
        try {
            return instagramService.search(accountName);
        } catch(InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * display the user
     *
     * @param 
     * @return Map<String, User> 
     *         accounts of user         
     */   
    public List<String> display() { 
        try {
            return instagramService.display();
        } catch(InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
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
    public User update(String accountName, String updateValue, int choice) { 
        try {
            return instagramService.update(accountName, updateValue, choice); 
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        } 
        return null; 
    } 

    /**
     * validate the account name
     *
     * @param string accountName
     *        account name of user
     * @return if account name valid return true
     *         else return false         
     */  
    public boolean isValidAccountName(String accountName) {
        return instagramUtil.isValidAccountName(accountName);
    }
 
    /**
     * validate the name of user
     *
     * @param name
     *        name of user  
     * @return if name valid return true
     *         else return false       
     */
    public boolean isValidName(String name) {
        return instagramUtil.isValidName(name);
    }

    /**
     * validate the mobile number
     *
     * @param mobileNumber
     *        mobile number of user
     * @return if mobile number valid return true
     *         else return false               
     */
    public boolean isValidMobileNumber(long mobileNumber) {
        return instagramUtil.isValidMobileNumber(mobileNumber);
    }

    /**
     * validate the password
     *
     * @param password
     *        password of user
     * @return if password valid return true
     *         else return false              
     */ 
    public boolean isValidPassword(String password) {
        return instagramUtil.isValidPassword(password);
    }
}