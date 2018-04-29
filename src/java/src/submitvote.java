/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 *
 * @author Jack
 */

@ManagedBean   
@SessionScoped
public class submitvote implements Serializable{
    Connection connection;
    Statement statement;
    Statement statement2; 
    ResultSet resultSet;
    String SQL;
    String cands;
    CandDetails c;
    
    public String submit(int id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca?useSSL=false","root","");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca","root","");
            statement = connection.createStatement(); 
            SQL = "select * from candidates where id like '" + id + "'";
            resultSet = statement.executeQuery(SQL);

            resultSet.next();
            int votes = resultSet.getInt(8);
            System.out.println(votes);
            votes+=1;
            
            SQL = "update candidates set votes = ? where id = ?";
            PreparedStatement stmt = connection.prepareStatement(SQL); 
            stmt.setInt(1, votes);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            
            Statement statement3 = connection.createStatement();
            SQL = "select votes from candidates where id like '" + id + "'";
            resultSet = statement3.executeQuery(SQL);
            resultSet.next();
            System.out.println(resultSet.getInt(1));
            statement3.close();
            
                
            
        } catch(Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception occured in the process");
            }
            finally {
                try {
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }    
            }
    return "votesubmitted";
    }
}
    
    
    

