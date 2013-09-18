/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author DX
 */
public class User extends ActionForm{
    
    String username="";
    String password="";

    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String u){
        username=u;
    }
    
    public String getPassowrd(){
        return password;
    }
    
    public void setPassword(String p){
        password=p;
    }
    
    
    
}
