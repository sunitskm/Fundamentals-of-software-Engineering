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
/**
 *
 * @author Jack
 */

@ManagedBean   
@SessionScoped
public class electionvote implements Serializable{
    public static ArrayList<CandDetails> list = new ArrayList();
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;
    String cands;
    
    public List getList(){
        return list;
    }
    
    public String queryData(String race){
        return dbData(race);
    }
    public String dbData(String race){
        ElectionDetails v = new ElectionDetails();
        CandDetails c; 
        list = new ArrayList<>();
        try{
            //System.out.println("Attermpting connection to database");
            Class.forName("com.mysql.jdbc.Driver");
           // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca","root","");
            statement = connection.createStatement(); 
            SQL = "select * from candidates where race like '" + race + "'";
            resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                c = new CandDetails();
                c.setId(resultSet.getInt(1));
                c.setRace(resultSet.getString(2));
                c.setCanFirstName(resultSet.getString(3) + " " + resultSet.getString(4));
                //c.setCanLastName(setTwo.getString(4));
                list.add(c);
            } 
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
    return "vote";
    }
}
    
    
    

