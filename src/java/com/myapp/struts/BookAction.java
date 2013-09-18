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
public class BookAction extends Action{
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,HttpServletResponse res){
        System.out.println("Start execute ("+form+")...");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        Statement st;
        try {
            st=DBConnecter.getDBConnection().createStatement();
            ResultSet rs=st.executeQuery("select * from accounts where name='"+username+"' and password='"+password+"'");
            if(rs.next()==false){
                req.getSession().setAttribute("warning", "Wrong password or username!");
                return mapping.findForward("loggedin");
            }else{
                req.getSession().setAttribute("warning",null);
                req.getSession().setAttribute("dxlogin", username);
            }
        } catch (SQLException ex) {
        }
        System.out.println(req.getSession().getServletContext().getRealPath(req.getRequestURI()));
        System.out.println("Attept to login :"+username+"  "+password);
        return mapping.findForward("loggedin");
    }
}
