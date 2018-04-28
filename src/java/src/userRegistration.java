/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@SessionScoped
public class userRegistration {
    
    private String userFirstName;
    private String userLastName;
    private String userEmailId;
    private String userSSN;
    private String userStreetAddress;
    private String userCity;
    private String userState;
    private String userZip;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;
    
    
    public userRegistration(){
        
      
    }
   

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
    
    public String getUserEmailId() {
        return userFirstName;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserSSN() {
        return userSSN;
    }

    public void setUserSSN(String userSSN) {
        this.userSSN = userSSN;
    }

    public String getUserStreetAddress() {
        return userStreetAddress;
    }

    public void setUserStreetAddress(String userStreetAddress) {
        this.userStreetAddress = userStreetAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }
    public String add(String uid,String type){
         System.out.println("Inside Add");
         int i=0;
         
         System.out.println("Uid is "+uid);
         statement =null;
         connection = null;
         try{
             System.out.println("Inside try");
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
             
                 System.out.println("Data Source is not null");
                 
                 if(connection!=null){
                 
                 SQL = " insert into userreg (userid, first_name, last_name,email_id,ssn,st_addr,city,state,zip,type,approved)"
        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                 PreparedStatement preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setString (1, uid);
                  preparedStmt.setString (2, userFirstName);
                  preparedStmt.setString (3, userLastName);
                  preparedStmt.setString (4, userEmailId);
                  preparedStmt.setString (5, userSSN);
                  preparedStmt.setString (6, userStreetAddress);
                  preparedStmt.setString (7, userCity);
                  preparedStmt.setString (8, userState);
                  preparedStmt.setString (9, userZip);
                  preparedStmt.setString (10, type);
                  preparedStmt.setString (11, "0");
                 
                  
                 System.out.println("Connection is not null");
                 preparedStmt.execute();      
                 i=1;
                 System.out.println("Data Added Successfully");
                 
                 SQL = " insert into ZIP (zip_code)"
        + " values (?)";

                  preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setString (1, userZip);
                 System.out.println("Connection is not null");
                 preparedStmt.execute();     
                 System.out.println(userZip);
                 System.out.println("Data Added Successfully");
                 }
             
         }
         catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
        if(i>0){
            return "success_reg";
        }
        else{
            return "unsuccess_reg";
        }
       
    }
}
    

