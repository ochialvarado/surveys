/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Stateless;

/**
 *
 * @author omar.chia
 */
@Stateless
public class QuestionAnswerResult {

   private Integer questionId;
   private Integer answerTypeId;
   private Integer surveyId;
   private String title;
   private String help;
   private String permalink;
   private String answerTypeText;
   
   public String getAnswerTypeText() {
        return answerTypeText;
    }

    public void setAnswerTypeText(String answerTypeText) {
        this.answerTypeText = answerTypeText;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerTypeId() {
        return answerTypeId;
    }

    public void setAnswerTypeId(Integer answerTypeId) {
        this.answerTypeId = answerTypeId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }
}
