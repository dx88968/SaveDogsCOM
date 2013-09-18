/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author DX
 */
public class RegisterAction extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,HttpServletResponse res){
        String username=req.getParameter("username");
        String password1=req.getParameter("password1");
        String password2=req.getParameter("password2");
        if(username.equals("")||password1.equals("")||password2.equals("")||!password1.equals(password2)){
            req.getSession().setAttribute("warning", "Illegal Input! Please retry");
            return mapping.findForward("registeruser");
        }
        Statement st;
        try {
            st=DBConnecter.getDBConnection().createStatement();
            String sql="insert into accounts (name,password) values ('"+username+"','"+password1+"')";
            st.execute(sql);
        } catch (SQLException ex) {
            req.getSession().setAttribute("warning", "Change a username!");
            return mapping.findForward("registeruser");
        }
        req.getSession().setAttribute("warning","Register Successfully!");
        req.getSession().setAttribute("dxlogin", username);
        return mapping.findForward("loggedin");
    }
    
}
