/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

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
public class LogoutAction extends Action{
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,HttpServletResponse res){
        System.out.println("Start execute ("+form+")...");
        System.out.println("Attept to logout");
        req.getSession().setAttribute("dxlogin", null);
        return mapping.findForward("loggedin");
    }
}
