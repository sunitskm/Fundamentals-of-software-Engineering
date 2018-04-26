/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lenovo
 */
@Named("AddCandidates")
@SessionScoped
public class AddCandidates implements Serializable {
    private String canFirstName;
    private String canLastName;
    private String canEmailId;
    private String canZip;
    private String canPrecinct;
    private String canCity;
    private String canState;
    private String canRace;
    List<String>   raceOptions;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;

    public AddCandidates() {
        System.out.println("Inside constructor of add candidates");
        raceOptions = new ArrayList<>();
        raceOptions.add("Governor");
        raceOptions.add("Senate"); 
    }
    
    public List<String> getRaceOptions() {
        return raceOptions;
    }
    
    public String getCanFirstName() {
        return canFirstName;
    }

    public void setCanFirstName(String canFirstName) {
        this.canFirstName = canFirstName;
    }

    public String getCanLastName() {
        return canLastName;
    }

    public void setCanLastName(String canLastName) {
        this.canLastName = canLastName;
    }

    public String getCanEmailId() {
        return canEmailId;
    }

    public void setCanEmailId(String canEmailId) {
        this.canEmailId = canEmailId;
    }

    public String getCanZip() {
        return canZip;
    }

    public void setCanZip(String canZip) {
        this.canZip = canZip;
    }

    public String getCanPrecinct() {
        return canPrecinct;
    }

    public void setCanPrecinct(String canPrecinct) {
        this.canPrecinct = canPrecinct;
    }
    

    public String getCanCity() {
        return canCity;
    }

    public void setCanCity(String canCity) {
        this.canCity = canCity;
    }

    public String getCanState() {
        return canState;
    }

    public void setCanState(String canState) {
        this.canState = canState;
    }

    public String getCanRace() {
        return canRace;
    }

    public void setCanRace(String canRace) {
        this.canRace = canRace;
    }

  public String addCand(){
    statement =null;
    connection = null;  
    
    
    try{
             System.out.println("Inside try");
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
             
                 System.out.println("Data Source is not null");
                 
                 if(connection!=null){
                 
                 SQL = " insert into cand (first_name, last_name, email_id,zip,precinct,city,state,race)"
        + " values (?, ?, ?, ?, ?, ?, ?,?)";

                 PreparedStatement preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setString (1, canFirstName);
                  preparedStmt.setString (2, canLastName);
                  preparedStmt.setString (3, canEmailId);
                  preparedStmt.setString (4, canZip);
                  preparedStmt.setString (5, canPrecinct);
                  preparedStmt.setString (6, canCity);
                  preparedStmt.setString (7, canState);
                  preparedStmt.setString (8, canRace);
                 System.out.println("Connection is not null");
                 preparedStmt.execute();      
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
    
  return "addCandidates";
  }
  
    

   
    
    
}

