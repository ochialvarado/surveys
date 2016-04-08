package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Ages;
import model.AnswerType;
import model.Province;
import model.Question;
import model.Survey;
import model.QuestionAnswerResult;
import model.QuestionOption;
import model.QuestionOptionQuestion;
import model.SurveyQuestionAnswer;
import model.SurveyQuestionAnswerMultiple;
import model.SurveyResult;
import util.DbUtil;
import service.SessionService;

public class SurveyDao {
    private Connection connection;
    private SessionService sessionService;
    private String surveyQuestionString = "";
    
    public SurveyDao() {
        connection = DbUtil.getConnection();
        sessionService = new SessionService();
    }
    
    public void deleteQuestion(int questionId) {
        try {
            Statement statement = connection.createStatement();
            String sqlString = "DELETE FROM \"question_options\" WHERE \"question_id\"="+questionId;
            System.out.println(sqlString);
            statement.executeUpdate(sqlString);
            
            sqlString = "DELETE FROM \"questions\" WHERE \"question_id\"="+questionId;
            System.out.println(sqlString);
            statement.executeUpdate(sqlString);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Integer addSurveyQuestionAnswerMultiple(SurveyQuestionAnswerMultiple questionAnswer) {
        Integer returnedId = 0;
        
        try {
            //Statement statement = connection.createStatement();
            String sqlString = "INSERT INTO \"survey_question_answers_multiple\" (\"survey_question_answer_id\", \"question_option_id\") VALUES ("+ questionAnswer.getSurveyQuestionAnswerId() +","+ questionAnswer.getQuestionOptionId() +")";
            String[] returnId = {"multiple_id"}; 
            System.out.println(sqlString);
            PreparedStatement statement = connection.prepareStatement(sqlString,returnId);
            int affectedRows = statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    returnedId = rs.getInt(1);
                }
                rs.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return returnedId;
    }
    
    public Integer addSurveyQuestionAnswer(SurveyQuestionAnswer questionAnswer) {
        Integer returnedId = 0;
        
        try {
            //Statement statement = connection.createStatement();
            String sqlString = "INSERT INTO \"survey_question_answers\" (\"survey_result_id\", \"question_id\", \"is_text\", \"text_answer\") VALUES ("+ questionAnswer.getSurveyResultId() +","+ questionAnswer.getQuestionId() +", "+ questionAnswer.getIsText() +", \'"+ questionAnswer.getTextAnswer() +"\')";
            String[] returnId = {"survey_question_answer_id"};
            System.out.println(sqlString);
            PreparedStatement statement = connection.prepareStatement(sqlString,returnId);
            int affectedRows = statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    returnedId = rs.getInt(1);
                }
                rs.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return returnedId;
    }
    
    public Integer addSurveyResult(SurveyResult result) {
        Integer returnedId = 0;
        
        try {
            //Statement statement = connection.createStatement();
            String sqlString = "INSERT INTO \"survey_results\" (\"survey_id\", \"provincia_id\", \"genero\", \"edad_id\") VALUES ("+ result.getSurveyId() +","+ result.getProvinceId() +", "+ result.getGenero() +", "+ result.getAgeId() +")";
            String[] returnId = {"survey_result_id"};
            System.out.println(sqlString);
            PreparedStatement statement = connection.prepareStatement(sqlString,returnId);
            int affectedRows = statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    returnedId = rs.getInt(1);
                }
                rs.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return returnedId;
    }
    
    public void addQuestionOption(QuestionOption option) {
        try {
            Statement statement = connection.createStatement();
            String sqlString = "INSERT INTO \"question_options\" (\"description\", \"question_id\", \"value\") VALUES (\'"+ option.getDescription() +"\',"+ option.getQuestionId() +", \'\')";
            System.out.println(sqlString);
            
            statement.executeUpdate(sqlString);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Integer addQuestion(Question question) {
        Integer returnedId = 0;
        
        try {
            //Statement statement = connection.createStatement();
            String sqlString = "INSERT INTO \"questions\" (\"title\",\"answer_type_id\",\"survey_id\") VALUES (\'"+ question.getTitle() +"\',"+ question.getAnswerTypeId() +", "+ question.getSurveyId() +")";
            String[] returnId = {"question_id"};
            System.out.println(sqlString);
            PreparedStatement statement = connection.prepareStatement(sqlString,returnId);
            int affectedRows = statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    returnedId = rs.getInt(1);
                }
                rs.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return returnedId;
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
    
    public List<Province> getProvinces() {
        List<Province> provinces = new ArrayList<Province>();
        
        try {
            System.out.println(connection);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM \"provincia\"";
            System.out.println(sqlQuery);
            ResultSet rs = statement.executeQuery(sqlQuery);
           
            while (rs.next()) {
                Province provincia = new Province();
                provincia.setProvinceId(rs.getInt("provincia_id"));
                provincia.setName(rs.getString("name"));
               
                provinces.add(provincia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return provinces;
    }
    
    public List<Ages> getAges() {
        List<Ages> ages = new ArrayList<Ages>();
        
        try {
            System.out.println(connection);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM \"edades\"";
            System.out.println(sqlQuery);
            ResultSet rs = statement.executeQuery(sqlQuery);
           
            while (rs.next()) {
                Ages edad = new Ages();
                edad.setAgeID(rs.getInt("edad_id"));
                edad.setAgeRange(rs.getString("age_range"));
               
                ages.add(edad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ages;
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
    
    public List<QuestionOptionQuestion> getAllQuestionsData(Integer surveyId) {
        List<QuestionOptionQuestion> list = new ArrayList<QuestionOptionQuestion>();

        try {
            System.out.println(connection);
            Statement statement = connection.createStatement();
            String sql = "SELECT \"questions\".\"answer_type_id\",\"questions\".\"title\", \"question_options\".* FROM \"questions\" INNER JOIN \"question_options\" ON \"questions\".\"question_id\"=\"question_options\".\"question_id\" WHERE \"survey_id\"="+surveyId+" ORDER BY \"question_options\".\"question_option_id\" ASC";
            Integer counter = 1;
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(rs);
            Integer currentQuestion = 0;

            while (rs.next()) {
               QuestionOptionQuestion object = new QuestionOptionQuestion();
               Integer questionId= rs.getInt("question_id");
  
               object.setAnswerTypeId(rs.getInt("answer_type_id"));
               object.setQuestionText(rs.getString("title"));
               object.setDescription(rs.getString("description"));
               object.setQuestionOptionId(rs.getInt("question_option_id"));
               object.setQuestionId(questionId);
               object.setSurveyQuestionId(counter);
                       
               if(currentQuestion != questionId) {
                   currentQuestion = questionId;
                   
                    if("".equals(surveyQuestionString)){
                        surveyQuestionString+= questionId + "type="+ rs.getInt("answer_type_id");
                    }else{ 
                        surveyQuestionString+= "-"+questionId + "type="+ rs.getInt("answer_type_id");
                    }
               }
               
               list.add(object);
               counter ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;         
    }

    public String getSurveyQuestionString() {
        return surveyQuestionString;
    }

    public void setSurveyQuestionString(String surveyQuestionString) {
        this.surveyQuestionString = surveyQuestionString;
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