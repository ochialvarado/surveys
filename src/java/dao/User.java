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
public class User {

   private Integer userId;
   private String name;
   private String password;
   private String email;
   private Timestamp startDate;
   
}
