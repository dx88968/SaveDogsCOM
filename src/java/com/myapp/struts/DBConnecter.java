/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DX
 */
public class DBConnecter {
    
    static Connection con;
    static String port="3306";
    static String DatabaseName="test";
    static String User="root";
    static String pwd="16886611";
    static boolean needinit=false;
    
    
    
    static String configpath="";
    static String lpath="";
    
    static HashMap<String,Integer> vars= new HashMap<String,Integer>();
    
    private DBConnecter(){
        
    }
    
    public static String getPath(){
        return lpath;
    }
    
    public static void setPath(String path){
        lpath=path;
    }
    
    public static void setNeedInit(boolean ni){
        needinit=ni;
    }
    
    public static boolean getNeedInit(){
        return needinit;
    }
    
    public static void setConfigPath(String path){
        configpath=path;
    }
    
    public static String getConfigPath(){
        return configpath;
    }
    
    public static void initDB(){
        Statement st=null;
        try {
            st=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnecter.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            st.execute("SET FOREIGN_KEY_CHECKS=0");
            st.execute("DROP TABLE IF EXISTS accounts");
            st.execute("CREATE TABLE accounts "
                    + "(uid  int(10) NOT NULL AUTO_INCREMENT,"
                    + "name  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
                    + "password  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ," 
                    +"PRIMARY KEY (uid))AUTO_INCREMENT=1");
            st.execute("DROP TABLE IF EXISTS dogs");
            st.execute("CREATE TABLE dogs "
                    + "(id  int(10) UNSIGNED NOT NULL AUTO_INCREMENT ,"
                    + "dogName  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
                    + "added_by  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
                    + "pic  longblob NULL ,"
                    + "description  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,"
                    + "moneyNeeded  decimal(10,0) NOT NULL ,"
                    + "currency  decimal(10,0) NULL DEFAULT NULL ,"
                    + "PRIMARY KEY (id))"
                    + "AUTO_INCREMENT=1");  
            st.execute("INSERT INTO accounts VALUES ('1', 'admin', '123456')");
            st.execute("INSERT INTO dogs VALUES ('1', 'Test Dog', 'admin', null, 'This is a dog for test', '234', null)");
            st.execute("ALTER TABLE dogs AUTO_INCREMENT=2");
            st.execute("DROP TABLE IF EXISTS records");
            st.execute("CREATE TABLE records (id  int(10) NOT NULL AUTO_INCREMENT , Donator  varchar(20) NOT NULL , target  varchar(20) NOT NULL ,amount  decimal NOT NULL ,date  date NOT NULL ,PRIMARY KEY (id))AUTO_INCREMENT=1");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void dispatchConfig(String line){
        if(vars.isEmpty()){
            vars.put("port", 1);
            vars.put("password", 2);
            vars.put("DataBaseName", 3);
            vars.put("user", 4);
        }
        String type=line.split(":")[0];
        Integer id=vars.get(type);
        if(id!=null){
            switch(id){
                case 1:
                    port=line.split(":")[1];
                    break;
                case 2:
                    pwd=line.split(":")[1];
                    break;
                case 3:
                    DatabaseName=line.split(":")[1];
                    break;
                case 4:
                    User=line.split(":")[1];
                    break;
            }
        }
    }
    
    public static Connection getDBConnection(){
        if(con==null){
            try{
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://127.0.0.1:"+port+"/"+DatabaseName;
                String user = User;
                String password = pwd;
                Class.forName(driver);
                con= DriverManager.getConnection(url, user, password);
                if(!con.isClosed()) {
                    System.out.println("Succeeded connecting to the Database!");
                }
            }
            catch(SQLException sex){
            }
            catch(Exception ex){
                System.out.println(ex.toString());
            }
        }
        return con;
    }
    
    
}
