
package com;

import java.io.IOException;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import model.User;

import dao.UserDao;
import service.SessionService;

public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String EDIT_USER = "/updateUser.jsp";
    private static String LIST_USER = "/listUser.jsp";
    private static String LOGIN = "/dashboard.jsp";
    private static String REGISTER = "/createUser.jsp";
    private static String NOTLOGGED = "/index.jsp?error=true";
    private UserDao dao;
    private SessionService sessionService;

    public UserController() {
        super();
        dao = new UserDao();
        sessionService = new SessionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("logout")){
            request.getSession().setAttribute("sessionUser", null);
        }
        
        if (action.equalsIgnoreCase("register")){
            RequestDispatcher registerView = request.getRequestDispatcher(REGISTER);
            registerView.forward(request, response);
        }
        
        if(sessionService.checkSession(request, response) == null) {
            return;
        }

        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            dao.deleteUser(userId);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());   
            
        } else if (action.equalsIgnoreCase("edit")){
            forward = EDIT_USER;
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = dao.getUserById(userId);
            request.setAttribute("User", user);

        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            System.out.println(dao.getAllUsers());
            request.setAttribute("users", dao.getAllUsers());

        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String forward="";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("login")){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Integer userID = dao.login(email, password);
            
            if (userID > 0) {
                forward = LOGIN;
                User user = dao.getUserById(userID);
                request.getSession().setAttribute("sessionUser", user);
                request.setAttribute("User", user);
            } else {
                forward = NOTLOGGED;
            }
        } else {
        
            User user = new User();
            
            String name= request.getParameter("name");
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            
            String userId = request.getParameter("userId");
            
            if(userId == null || userId.isEmpty()) {
                dao.addUser(user);
            } else {
                user.setUserId(Integer.parseInt(userId));
                dao.updateUser(user);
            }
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}