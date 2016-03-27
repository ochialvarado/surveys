/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Timestamp;
import javax.ejb.Stateless;

/**
 *
 * @author omar.chia
 */
@Stateless
public class SurveyQuestionAnswerMultiple {

   private Integer multipleId;
   private Integer surveyQuestionAnswerId;
   private Integer questionOptionId;

    public Integer getMultipleId() {
        return multipleId;
    }

    public void setMultipleId(Integer multipleId) {
        this.multipleId = multipleId;
    }

    public Integer getSurveyQuestionAnswerId() {
        return surveyQuestionAnswerId;
    }

    public void setSurveyQuestionAnswerId(Integer surveyQuestionAnswerId) {
        this.surveyQuestionAnswerId = surveyQuestionAnswerId;
    }

    public Integer getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(Integer questionOptionId) {
        this.questionOptionId = questionOptionId;
    }
   
   
}
