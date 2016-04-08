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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import model.Question;
import model.QuestionOption;
import model.QuestionOptionQuestion;
import model.SurveyQuestionAnswer;
import model.SurveyQuestionAnswerMultiple;
import model.SurveyResult;
import model.SurveyResultFirstAnswer;
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
        
        if (action.equalsIgnoreCase("firstStep")){
            int surveyId = Integer.parseInt(request.getParameter("id"));
            Survey survey = dao.getSurveyById(surveyId);
            request.setAttribute("Survey", survey);
            request.setAttribute("provincias", dao.getProvinces());      
            request.setAttribute("edades", dao.getAges()); 
            forward ="firstStep.jsp";
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
            return;
        } else {
            User currentUser = sessionService.checkSession(request, response);
            
            if(currentUser == null) {
                return;
            }
            
            if(action.equalsIgnoreCase("report")) {
                
                int surveyId = Integer.parseInt(request.getParameter("id"));
                Survey surveyItem = dao.getSurveyById(surveyId);
                List<QuestionOptionQuestion> optionlist = new ArrayList<QuestionOptionQuestion>();
                
                optionlist = dao.getAllQuestionsData(surveyId);
                
                request.setAttribute("firstStepStatics", dao.getSurveyGlobalAnswer(surveyId));
                
                request.setAttribute("totalInterviewed", dao.getTotalInterviewed());
                request.setAttribute("Survey", surveyItem);
                request.setAttribute("questions", optionlist);
                
                forward ="report.jsp";
                
                //final ConcurrentMap<String, AtomicLong> map = new ConcurrentHashMap<String, AtomicLong>();
                //map.putIfAbsent("foo", new AtomicLong(0));
                //map.get("foo").incrementAndGet();
                
            
            } else if (action.equalsIgnoreCase("deleteQuestion")){
                int optionId = Integer.parseInt(request.getParameter("id"));
                int surveyId = Integer.parseInt(request.getParameter("survey_id"));
                dao.deleteQuestion(optionId);
                forward ="listQuestion.jsp";

                request.setAttribute("questions", dao.getQuestions(surveyId));      
                Survey survey = dao.getSurveyById(surveyId);
                request.setAttribute("Survey", survey);

                if(dao.surveyHasResults(surveyId) > 0) {
                    request.setAttribute("imposibleToChange", 1);
                } else {
                    request.setAttribute("imposibleToChange", 0);
                }
            } else if (action.equalsIgnoreCase("addQuestion")){
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
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String forward="";
        String action = request.getParameter("action");
        Survey survey = new Survey();
        
        if (action.equalsIgnoreCase("saveSurvey")){
            String[] explodedPostFields = request.getParameter("forBucle").split("-");
            String fieldName;
            String stringForBd;
            Integer questionOptionId;
            Integer typeOfQuestion;
            Integer questionId;
            Integer saveSurveyId = Integer.parseInt(request.getParameter("survey_id"));
            
            
            for (String answerElement: explodedPostFields) {           
                String[] explodedField = answerElement.split("type=");
                questionId = Integer.parseInt(explodedField[0]);
                typeOfQuestion = Integer.parseInt(explodedField[1]);
                fieldName = "question_"+ questionId ;
                
                Integer surveyQuestionAnswerId;
                Boolean isText;
                
                if(typeOfQuestion != 3){
                    SurveyQuestionAnswer surveyQuestionAnswer = new SurveyQuestionAnswer();
                    isText = false;
                    
                    surveyQuestionAnswer.setIsText(isText);
                    surveyQuestionAnswer.setQuestionId(questionId);
                    surveyQuestionAnswer.setSurveyResultId(saveSurveyId);
                    surveyQuestionAnswer.setTextAnswer("");
                    
                    surveyQuestionAnswerId = dao.addSurveyQuestionAnswer(surveyQuestionAnswer);
                    
                    
                    if(typeOfQuestion == 2) {
                        fieldName = "question_"+ questionId +"[]" ;
                        String[] questionsAnswers = request.getParameterValues(fieldName);
                        
                        Integer test;
                        
                        for (Integer i=0; i < questionsAnswers.length; i++) {
                            SurveyQuestionAnswerMultiple surveyQuestionMultiple = new SurveyQuestionAnswerMultiple();
                            surveyQuestionMultiple.setQuestionOptionId(Integer.parseInt(questionsAnswers[i]));
                            surveyQuestionMultiple.setSurveyQuestionAnswerId(surveyQuestionAnswerId);
                            
                            dao.addSurveyQuestionAnswerMultiple(surveyQuestionMultiple);
                        }
                       
                    } else {
                       questionOptionId = Integer.parseInt(request.getParameter(fieldName));
                       SurveyQuestionAnswerMultiple surveyQuestionMultiple = new SurveyQuestionAnswerMultiple();
                       
                       surveyQuestionMultiple.setQuestionOptionId(questionOptionId);
                       surveyQuestionMultiple.setSurveyQuestionAnswerId(surveyQuestionAnswerId);
                               
                       dao.addSurveyQuestionAnswerMultiple(surveyQuestionMultiple);
                    }
                    
                } else {
                    stringForBd = request.getParameter(fieldName);
                    
                    SurveyQuestionAnswer surveyQuestionAnswer = new SurveyQuestionAnswer();
                    isText = true;
                    
                    surveyQuestionAnswer.setIsText(isText);
                    surveyQuestionAnswer.setQuestionId(questionId);
                    surveyQuestionAnswer.setSurveyResultId(saveSurveyId);
                    surveyQuestionAnswer.setTextAnswer(stringForBd);
                    
                    surveyQuestionAnswerId = dao.addSurveyQuestionAnswer(surveyQuestionAnswer);
                }
            }
            
            forward="thanks.jsp";
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
            
        } else if (action.equalsIgnoreCase("saveFirstSurvey")){
            Integer saveSurveyId;
            SurveyResult surveyResult = new SurveyResult();
            List<QuestionOptionQuestion> optionlist = new ArrayList<QuestionOptionQuestion>();
            QuestionOption option = new QuestionOption();
            
            surveyResult.setSurveyId(Integer.parseInt(request.getParameter("survey_id")));
            surveyResult.setProvinceId(Integer.parseInt(request.getParameter("provincia")));
            surveyResult.setAgeId(Integer.parseInt(request.getParameter("edad")));
            surveyResult.setGenero(Integer.parseInt(request.getParameter("genero")));
            
            saveSurveyId = dao.addSurveyResult(surveyResult);
            Survey surveyItem = dao.getSurveyById(Integer.parseInt(request.getParameter("survey_id")));
            optionlist = dao.getAllQuestionsData(Integer.parseInt(request.getParameter("survey_id")));
            
            request.setAttribute("Survey", surveyItem);
            request.setAttribute("questions", optionlist);
            request.setAttribute("questionsId", dao.getSurveyQuestionString());
            request.setAttribute("saveSurveyId", saveSurveyId);
            
            forward="makeSurvey.jsp";
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
             
        } else {
            User currentUser = sessionService.checkSession(request, response);

            if (action.equalsIgnoreCase("addQuestion")){
                Integer questionId;
                Integer surveyId = Integer.parseInt(request.getParameter("survey_id"));
                String answer1 = request.getParameter("answer_1");
                Question questionObject = new Question();
                QuestionOption optionObject = new QuestionOption();

                questionObject.setAnswerTypeId(Integer.parseInt(request.getParameter("question_type")));
                questionObject.setTitle(request.getParameter("question_1"));
                questionObject.setSurveyId(surveyId);

                questionId = dao.addQuestion(questionObject);

                if(!"".equals(answer1)) {
                    String parameterValue;

                    for(Integer i=1;i<6;i++){
                        parameterValue = request.getParameter("answer_"+i);
                        if(!"".equals(parameterValue)) {
                            optionObject.setQuestionId(questionId);
                            optionObject.setDescription(parameterValue);
                            dao.addQuestionOption(optionObject);
                        }
                    }
                } else {
                    optionObject.setQuestionId(questionId);
                    optionObject.setDescription("void");
                    dao.addQuestionOption(optionObject);
                }

                Survey surveyItem = dao.getSurveyById(surveyId);
                request.setAttribute("questions", dao.getQuestions(surveyId));      
                request.setAttribute("Survey", surveyItem);
                request.setAttribute("imposibleToChange", 0);
                forward="listQuestion.jsp";

            } else if (action.equalsIgnoreCase("addSurvey")){
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
}