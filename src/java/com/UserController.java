
package com;

import java.io.IOException;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.sql.Timestamp;

import dao.UserDao;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;



import dao.UserDao;

import model.User;



public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static String INSERT_OR_EDIT = "/user.jsp";

    private static String LIST_USER = "/listUser.jsp";

    private UserDao dao;



    public UserController() {

        super();

        dao = new UserDao();

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward="";

        String action = request.getParameter("action");

        System.out.println(action);

        if (action.equalsIgnoreCase("delete")){

            int userId = Integer.parseInt(request.getParameter("userId"));

            dao.deleteUser(userId);

            forward = LIST_USER;

            request.setAttribute("users", dao.getAllUsers());   

        } else if (action.equalsIgnoreCase("edit")){

            forward = INSERT_OR_EDIT;

            int userId = Integer.parseInt(request.getParameter("userId"));

            User user = dao.getUserById(userId);

            request.setAttribute("user", user);

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



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();

        user.setName(request.getParameter("name"));


        try {

            Date startDate = new SimpleDateFormat("MM/dd/yyyy H:i:s").parse(request.getParameter("startDate"));

            user.setStartDate(startDate);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        user.setEmail(request.getParameter("email"));

        String userId = request.getParameter("userId");

        if(userId == null || userId.isEmpty())

        {

            dao.addUser(user);

        }

        else

        {

            user.setUserId(Integer.parseInt(userId));

            dao.updateUser(user);

        }

        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);

        request.setAttribute("users", dao.getAllUsers());

        view.forward(request, response);

    }

}



