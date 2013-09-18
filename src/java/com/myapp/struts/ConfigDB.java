/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.io.File;
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
public class ConfigDB extends Action{
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,HttpServletResponse res){
        System.out.println("Start execute ("+form+")...");
        System.out.println("Attept to ConfigDB");
        String port=req.getParameter("port");
        String user=req.getParameter("user");
        String password=req.getParameter("password");
        String dbname=req.getParameter("DBname");
        File conf=new File(DBConnecter.getConfigPath());
        try{ 
        java.io.FileWriter fw=new java.io.FileWriter(conf,false); 
        java.io.PrintWriter pw=new java.io.PrintWriter(fw); 
         pw.println("port:"+port); 
         pw.println("user:"+user);
         pw.println("DataBaseName:"+dbname);
         pw.println("password:"+password);
         pw.close(); 
         fw.close(); 
         } 
         catch(Exception e){ 
              // out.println(e.getMessage()); 
         }
        return mapping.findForward("config");
    }
}
