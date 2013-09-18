/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.github.dx88968.monitor.logger.DirectOutputTracker;
import org.github.dx88968.monitor.logger.TraceLevel;

/**
 *
 * @author DX
 */
public class DonateAction extends Action{
    
     @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,HttpServletResponse res){
        String id=req.getParameter("id");
        String amount=req.getParameter("amount");
        String account=req.getParameter("account");
        String dogname="";
        String username=(String) req.getSession().getAttribute("dxlogin");
        DirectOutputTracker.instance.print(null, TraceLevel.ALL, "Trying to donate");
        if(username==null){
            req.getSession().setAttribute("warning","Please log in before you make any donation.");
            return mapping.findForward("loggedin");
        }
        Double increase=0.0;
        if(account.length()!=7){
            req.getSession().setAttribute("donateresult","Illegal Input! Please try again.");
            return mapping.findForward("donate");
        }
        try{
            increase=Double.parseDouble(amount);
            DirectOutputTracker.instance.print(amount, TraceLevel.ALL, "Trying to donate "+amount);
            Integer.parseInt(account);
        }catch(Exception ex){
            req.getSession().setAttribute("donateresult","Illegal Input! Please try again.");
            return mapping.findForward("donate");
        }
        String query="select currency,dogname from dogs where id='"+id+"'";
        try {
            Statement st=DBConnecter.getDBConnection().createStatement();
            ResultSet rs=st.executeQuery(query);
            Double cm=0.0;
            if(rs.next()!=false) {
                cm=rs.getDouble(1);
                dogname=rs.getString(2);
            }
            Double total=cm+increase;
            String sql="update dogs set currency="+total+" where id='"+id+"'";
            st.executeUpdate(sql);
            java.util.Date currentTime = new  java.util.Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            String sql2="insert into records (donator, target, amount, date) values ('"+username+"','"+dogname+"','"+amount+"','"+dateString+"')";
            System.out.println(sql2);
            st.execute(sql2);
        } catch (SQLException ex) {
            ex.printStackTrace();
            req.getSession().setAttribute("donateresult","Fail to update Database! Please try again.");
            return mapping.findForward("donate");
        }
        req.getSession().setAttribute("donateresult","Donate Successfully, thank you for your generous!");
        return mapping.findForward("donate");
    }
    
}
