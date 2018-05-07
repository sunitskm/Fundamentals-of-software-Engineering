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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lenovo
 */
@Named("AddPrecinct")
@SessionScoped
public class AddPrecinct implements Serializable {
    private String precinctName;
    private String zip;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;


    public AddPrecinct() {
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String addPrecinct(){
        statement =null;
    connection = null;  
    
    
    try{
             System.out.println("Inside try");
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
             
                 System.out.println("Data Source is not null");
                 
                 if(connection!=null){
                 
                 SQL = " insert into prec_zip (precinct_name,zip_code)"
        + " values (?,?)";

                 PreparedStatement preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setString (1, precinctName);
                  preparedStmt.setString (2, zip);
                 System.out.println("Connection is not null");
                 preparedStmt.execute();     
                 System.out.println(precinctName);
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
    
  return "admindashboard";
  }
  
        
    
    
}
