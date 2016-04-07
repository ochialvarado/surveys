package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.AnswerType;
import model.Survey;
import model.QuestionAnswerResult;
import util.DbUtil;
import service.SessionService;

public class SurveyDao {
    private Connection connection;
    private SessionService sessionService;
    
    public SurveyDao() {
        connection = DbUtil.getConnection();
        sessionService = new SessionService();
    }
    
    public void addSurvey(Survey survey) {
        try {
            Statement statement = connection.createStatement();
            Timestamp currentTime = DbUtil.getCurrentTimeStamp();
            String sqlString = "INSERT INTO \"surveys\" (\"title\",\"description\",\"user_id\",\"start_date\") VALUES (\'"+ survey.getTitle() +"\',\'"+ survey.getDescription() +"\', "+ survey.getUserId() +",\'"+ currentTime +"\')";
            
            System.out.println(statement.executeUpdate(sqlString));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSurvey(int surveyId) {
        try {
            Statement statement = connection.createStatement();
            String sqlString = "UPDATE \"surveys\" SET \"is_visible\"=false WHERE \"survey_id\"= " + surveyId;
            System.out.println(sqlString);
            statement.executeUpdate(sqlString);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSurvey(Survey survey) {
        try {
           Statement statement = connection.createStatement();
           String sqlString = "UPDATE \"surveys\" SET \"title\"=\'"+ survey.getTitle() +"\', \"description\"=\'"+ survey.getDescription() +"\' WHERE \"survey_id\"="+ survey.getSurveyId();
           System.out.println(sqlString);
           statement.executeUpdate(sqlString);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Survey> getAllSurveys(Integer userId) {

        List<Survey> surveys = new ArrayList<Survey>();

        try {
            System.out.println(connection);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM \"surveys\" WHERE \"is_visible\"=true AND \"user_id\"=" + userId;
            System.out.println(sqlQuery);
            ResultSet rs = statement.executeQuery(sqlQuery);
            

            while (rs.next()) {
                Survey survey = new Survey();
                survey.setSurveyId(rs.getInt("survey_id"));
                survey.setDescription(rs.getString("description"));
                survey.setTitle(rs.getString("title"));
               
                surveys.add(survey);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return surveys;
    }

    public Survey getSurveyById(int surveyId) {
        Survey survey = new Survey();

        try {
            System.out.println(connection);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"surveys\" WHERE \"survey_id\"="+ surveyId );
            System.out.println(rs);

            if (rs.next()) {
                survey.setSurveyId(rs.getInt("survey_id"));
                survey.setDescription(rs.getString("description"));
                survey.setTitle(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return survey;
    }
    
    public List<QuestionAnswerResult> getQuestions(Integer surveyId) {
        List<QuestionAnswerResult> list = new ArrayList<QuestionAnswerResult>();

        try {
            System.out.println(connection);
            Statement statement = connection.createStatement();
            String sql = "SELECT \"questions\".*, \"answer_types\".\"title\" as \"answer_type\" FROM \"questions\" INNER JOIN \"answer_types\" ON \"questions\".\"answer_type_id\"=\"answer_types\".\"answer_type_id\" WHERE \"survey_id\"="+surveyId+" ORDER BY \"question_id\" ASC";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(rs);

            while (rs.next()) {
               QuestionAnswerResult object = new QuestionAnswerResult();
               object.setAnswerTypeId(rs.getInt("answer_type_id"));
               object.setAnswerTypeText(rs.getString("answer_type"));
               object.setTitle(rs.getString("title"));
               object.setSurveyId(rs.getInt("survey_id"));
               object.setQuestionId(rs.getInt("question_id"));
               
               list.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;         
    }
    
    
    public Integer surveyHasResults(int surveyId) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM \"survey_results\" WHERE \"survey_id\"="+ surveyId + " FETCH FIRST 1 ROWS ONLY";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
               return 100;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public List<AnswerType> getAnswerTypes() {
        List<AnswerType> list = new ArrayList<AnswerType>();

        try {
            System.out.println(connection);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM \"answer_types\" ";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(rs);

            while (rs.next()) {
               AnswerType object = new AnswerType();
               object.setAnswerTypeId(rs.getInt("answer_type_id"));
               object.setTitle(rs.getString("title"));
               object.setValue(rs.getString("value"));
               
               list.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;         
    }
}