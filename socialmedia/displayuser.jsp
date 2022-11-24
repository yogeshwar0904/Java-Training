<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Instagram App</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class ="navbar navbar-expand-md navbar-dark" style="background-color: blue">
                    <div>
                        <a href="displayuser.jsp"  class="navbar-brand">Instagram App</a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Users</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New User</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>User ID</th>
                                <th>AccountName</th>
                                <th>UserName</th>
                                <th>Mobilenumber</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="user" items="${accountNames}">

                                <tr>
                                    <td>
                                        <c:out value="${user.userId}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.accountName}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.userName}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.mobileNumber}" />
                                    </td>

                                    <td><a href="edit?userId=<c:out value='${user.userId}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?userId=<c:out value='${user.userId}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>