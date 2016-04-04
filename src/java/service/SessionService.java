/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author omar.chia
 */
public class SessionService {
    public User checkSession(HttpServletRequest request, HttpServletResponse response) {
        Object sessionObject = request.getSession().getAttribute("sessionUser");
        
        if(sessionObject == null) {
            try {
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SessionService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return (User)sessionObject;
        }
        return null;
    }
}
