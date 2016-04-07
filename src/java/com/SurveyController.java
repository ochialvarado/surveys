
package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import model.User;
import model.Survey;

import dao.SurveyDao;
import service.SessionService;

public class SurveyController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/survey.jsp";
    private static String LIST_SURVEY = "/listSurvey.jsp";
    private static String QUESTIONS = "/questions.jsp?s=";
    
    private SurveyDao dao;
    private SessionService sessionService;

    public SurveyController() {
        super();
        dao = new SurveyDao();
        sessionService = new SessionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        User currentUser = sessionService.checkSession(request, response);
        
        if(currentUser == null) {
            return;
        }
        
        if (action.equalsIgnoreCase("addQuestion")){
            int surveyId = Integer.parseInt(request.getParameter("surveyId"));
            forward="addQuestion.jsp";
            
            request.setAttribute("answerTypes", dao.getAnswerTypes());      
            Survey survey = dao.getSurveyById(surveyId);
            request.setAttribute("Survey", survey);
            
        } else if (action.equalsIgnoreCase("listQuestion")){
            int surveyId = Integer.parseInt(request.getParameter("surveyId"));
            forward="listQuestion.jsp";
            
            request.setAttribute("questions", dao.getQuestions(surveyId));      
            Survey survey = dao.getSurveyById(surveyId);
            request.setAttribute("Survey", survey);
            
            if(dao.surveyHasResults(surveyId) > 0) {
                request.setAttribute("imposibleToChange", 1);
            } else {
                request.setAttribute("imposibleToChange", 0);
            }
            
        } else if (action.equalsIgnoreCase("delete")){
            int surveyId = Integer.parseInt(request.getParameter("surveyId"));
            dao.deleteSurvey(surveyId);
            forward = LIST_SURVEY;
            request.setAttribute("surveys", dao.getAllSurveys(currentUser.getUserId()));   
            
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int surveyId = Integer.parseInt(request.getParameter("surveyId"));
            Survey survey = dao.getSurveyById(surveyId);
            request.setAttribute("Survey", survey);

        } else if (action.equalsIgnoreCase("listSurvey")){
            forward = LIST_SURVEY;
            request.setAttribute("surveys", dao.getAllSurveys(currentUser.getUserId()));

        } else if (action.equalsIgnoreCase("add")){
            forward = INSERT_OR_EDIT;
            Survey survey = new Survey();
            request.setAttribute("Survey", survey);
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
        
        User currentUser = sessionService.checkSession(request, response);
        Survey survey = new Survey();
        
        if (action.equalsIgnoreCase("addSurvey")){
            survey.setTitle(request.getParameter("title"));
            survey.setDescription(request.getParameter("description"));
            survey.setUserId(currentUser.getUserId());
            
            String surveyId = request.getParameter("surveyId");
            
            if(surveyId == null || surveyId.isEmpty()) {
                dao.addSurvey(survey);
                forward = LIST_SURVEY;
            } else {
                survey.setSurveyId(Integer.parseInt(surveyId));
                dao.updateSurvey(survey);
                forward = LIST_SURVEY;
            }
            
            request.setAttribute("surveys", dao.getAllSurveys(currentUser.getUserId()));
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}