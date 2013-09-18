/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author DX
 */
public class RegisterDog extends Action{
    
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,HttpServletResponse res) throws FileNotFoundException{
                int num = 0;
		String userName = (String)req.getSession().getAttribute("dxlogin");
		
                // get form params
                String dogName = "";
		double fundNeed = 0;
		String dogDesc = "";
                
                
                StrutsUploadForm myForm = (StrutsUploadForm)form;

                // Process the FormFile
                FormFile myFile = myForm.getTheFile();
                String contentType = myFile.getContentType();
                String fileName  = myFile.getFileName();
                int fileSize = myFile.getFileSize();
                
                dogName = myForm.getDog_name();
                try
                {
                    fundNeed = Double.parseDouble(myForm.getDog_fund());
                }catch(Exception e) {}
                dogDesc = myForm.getDog_desc();
                
		// get form content type
		String ctype = req.getContentType();
		
		Connection dbConn = null;    		
    		PreparedStatement pState = null;    		
    		String line = null;    		
    		String value = null;    		
    		
    		try
    		{
                    InputStream picIS = myFile.getInputStream();
    		        // Resize image
                    BufferedImage srcImg = ImageIO.read(picIS);
                    BufferedImage scaledImg = new BufferedImage(300, 300,
                                                BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2D = scaledImg.createGraphics();
                    g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2D.drawImage(srcImg,0, 0, 300, 300, null);
                    
                    // create a temporary file.
                    File tempFile = File.createTempFile("tempFile", ".tmp");
                    OutputStream outputStream = new FileOutputStream(tempFile);

                    ImageIO.write(scaledImg, "png", outputStream);
                    outputStream.close();
                   
                    
                    //get an InputStream from the previous file.
                    InputStream input = new FileInputStream(tempFile);

                    // For debug        		
                    //System.out.println("Value:" + value);        		

                    // Get database connection
                    dbConn = DBConnecter.getDBConnection();

                    // Execute query string
                    // Field:dog_name, added_by, pic, desc, moneyNeeded
                    if(fileSize!=0){
                    String queryString = "INSERT INTO `dogs` (`dogName`, `added_by`, `pic`, `description`, `moneyNeeded`) values(?,?,?,?,?)";        		
                    pState = dbConn.prepareStatement(queryString);
                    pState.setString(1, dogName);
                    pState.setString(2, userName);
                    pState.setBinaryStream(3, input,scaledImg.getWidth(null)*scaledImg.getHeight(null)*100);
                    pState.setString(4, dogDesc);
                    pState.setDouble(5, fundNeed);	
                    }else{
                        String queryString = "INSERT INTO `dogs` (`dogName`, `added_by`, `description`, `moneyNeeded`) values(?,?,?,?)";        		
                    pState = dbConn.prepareStatement(queryString);
                    pState.setString(1, dogName);
                    pState.setString(2, userName);
                    pState.setString(3, dogDesc);
                    pState.setDouble(4, fundNeed);	
                    }
                    
                    num = pState.executeUpdate();
                    pState.close();
                    
                    //be sure to close here.
                    input.close();
                    //delete temporary file when you finish to use it.
                    //if streams where not correctly closed this might fail (return false)
                    tempFile.delete();
                    
                    if(num > 0)    		
                    {        		
                        req.getSession().setAttribute("registered", "true");
                    }
    		}    		
    		catch(Exception e)    		
    		{
                    e.printStackTrace();
             }	
        return mapping.findForward("register");
    }
}
