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
public class AnswerType {

   private Integer answerTypeId;
   private String title;
   private String value;

    public Integer getAnswerTypeId() {
        return answerTypeId;
    }

    public void setAnswerTypeId(Integer answerTypeId) {
        this.answerTypeId = answerTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
