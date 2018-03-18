/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@SessionScoped
public class ApproveUsers {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;
    
    public ArrayList<VoterDetails> voters = new ArrayList<VoterDetails>();
    
    public ApproveUsers(){
        System.out.println("Inside constructor of approve users");
        
    }
    public void displayUsers(){
        System.out.println("Approve Users Attermpting connection to database.Inside function");
        try{
        System.out.println("Approve Users Attermpting connection to database");
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
        statement = connection.createStatement(); 
        SQL = "Select * from USERREG where approved like ('" + 1 +"')";
        resultSet = statement.executeQuery(SQL);
        while (resultSet.next()) {
        voters.add(new VoterDetails(resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9)));       
    } 
       
    }
    catch(Exception ex){
    ex.printStackTrace();
    System.out.println("Exception occured in the process");
    }
    finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
        }
    }

    public ArrayList<VoterDetails> getVoters() {
        System.out.println("Inside voters");
        return voters;
    }

    public void setVoters(ArrayList<VoterDetails> voters) {
        this.voters = voters;
    }

    
    
    
}
