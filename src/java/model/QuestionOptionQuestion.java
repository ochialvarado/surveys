/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import javax.ejb.Stateless;

/**
 *
 * @author omar.chia
 */
@Stateless
public class QuestionOptionQuestion {

   private Integer questionId;
   private Integer answerTypeId;
   private Integer questionOptionId;
   private Integer surveyQuestionId; 
   private String description;
   private String questionText;
   private Integer answerCount;
   private String textAnswer;

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getSurveyQuestionId() {
        return surveyQuestionId;
    }

    public void setSurveyQuestionId(Integer surveyQuestionId) {
        this.surveyQuestionId = surveyQuestionId;
    }
   private String value;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getAnswerTypeId() {
        return answerTypeId;
    }

    public void setAnswerTypeId(Integer answerTypeId) {
        this.answerTypeId = answerTypeId;
    }

    public Integer getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(Integer questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
