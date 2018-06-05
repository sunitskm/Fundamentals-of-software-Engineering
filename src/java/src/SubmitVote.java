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
public class SubmitVote implements Serializable{
    Connection connection;
    Statement statement;
    Statement statement2; 
    ResultSet resultSet;
    String SQL;
    String cands;
    CandDetails c;
    
    public String submit(int id, String uid){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca","root","");
            statement = connection.createStatement(); 
            SQL = "select * from candidates where id like '" + id + "'";
            resultSet = statement.executeQuery(SQL);
            
            resultSet.next();
            System.out.println("resultSet(2): "+resultSet.getString(2));
            System.out.println("userid: " + uid);
            //statement.close();
            
            int votes = resultSet.getInt(8);
            System.out.println(votes);
            votes+=1;
            
            if(resultSet.getString(2).equals("United States President")) {
                statement.close();
                statement = connection.createStatement();
                SQL = "UPDATE user_voting SET presidential='1' WHERE userid like('"+ uid +"')";
                statement.executeUpdate(SQL);
            } else {
                String[] splitRace = resultSet.getString(2).split(" ");
                for(int i = 0; i < splitRace.length; i++) {
                    System.out.println("splitRace " + i + ": " + splitRace[i]);
                }

                statement.close();
                statement = connection.createStatement();
                if(splitRace[0].equals("Senate")) {               
                    SQL = "UPDATE user_voting SET senate='1' WHERE userid like('"+ uid +"')";
                    statement.executeUpdate(SQL);
                } else if(splitRace[0].equals("House")) {
                   SQL = "UPDATE user_voting SET house_of_rep='1' WHERE userid like('"+ uid +"')";
                    statement.executeUpdate(SQL); 
                }
                
            }
            
            
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
    
    
    

