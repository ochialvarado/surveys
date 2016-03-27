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
public class QuestionOption {

   private Integer questionOptionId;
   private Integer questionId;
   private String description;
   private String value;

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
