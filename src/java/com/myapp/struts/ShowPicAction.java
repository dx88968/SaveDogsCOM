/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
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
public class ShowPicAction extends Action{
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,HttpServletResponse res){
        String pid;
        Blob img = null;
        pid = req.getParameterValues("pid")[0];
        System.out.println("Pid="+pid+"******************************");
        try{
        Statement st=DBConnecter.getDBConnection().createStatement();
        ResultSet rs=st.executeQuery("select pic from dogs where id="+pid);
        while(rs.next()!=false){
            img=rs.getBlob(1);
        }
        }catch(Exception ex){
            
        }
        try{
        InputStream is=null;
        if(img!=null) {
                is = img.getBinaryStream();
            }else{
            File f=new File(DBConnecter.getPath()+"Resources\\defaultDog.jpg");
            is=new FileInputStream(f);
        }
        int length = 0;
        try {
           length = is.available();
        } catch (IOException e) {
        }
        byte[] buf = new byte[length];
        try {
            is.read(buf);
        } catch (IOException e) {
         }
        res.setContentType("image/jpg");
        OutputStream toClient = null;
        try {
           toClient = res.getOutputStream();
           toClient.write(buf); //得到向客户端输出二进制数据的对象
           toClient.close();
        }catch (IOException e) {
        }
        }catch(Exception ex){
            System.out.println("Error while parsing image");
        }
        return null;//Only used to parse image, use "null"
    }
    
}
