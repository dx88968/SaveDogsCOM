/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author DX
 */
public class StrutsUploadForm extends ActionForm{
    
    private FormFile theFile;
    private String dog_name = "";
    private String dog_fund = "";
    private String dog_desc = "";
    
    public String getDog_name(){
        return dog_name;
    }
    
    public void setDog_name(String name){
        this.dog_name=name;
    }
    
    public String getDog_fund(){
        return dog_fund;
    }
    
    public void setDog_fund(String fund){
        this.dog_fund=fund;
    }
    
    public String getDog_desc(){
        return dog_desc;
    }
    
    public void setDog_desc(String desc){
        this.dog_desc=desc;
    }
    
    public FormFile getTheFile(){
        return theFile;
    }
    
    public void setTheFile(FormFile theFile){
        this.theFile=theFile;
    }
}
