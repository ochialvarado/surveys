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
public class SurveyQuestionAnswer {

   private Integer surveyQuestionAnswerId;
   private Integer surveyResultId;
   private Integer questionId;
   private String textAnswer;
   private Boolean isText;

    public Integer getSurveyQuestionAnswerId() {
        return surveyQuestionAnswerId;
    }

    public void setSurveyQuestionAnswerId(Integer surveyQuestionAnswerId) {
        this.surveyQuestionAnswerId = surveyQuestionAnswerId;
    }

    public Integer getSurveyResultId() {
        return surveyResultId;
    }

    public void setSurveyResultId(Integer surveyResultId) {
        this.surveyResultId = surveyResultId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public Boolean getIsText() {
        return isText;
    }

    public void setIsText(Boolean isText) {
        this.isText = isText;
    }
}
