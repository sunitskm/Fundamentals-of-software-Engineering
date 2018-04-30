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
public class queryelectionsmanager implements Serializable{
    public static ArrayList<ElectionDetails> list = new ArrayList();
    Connection connection;
    Statement statement;
    Statement statement2;
    ResultSet resultSet;
    ResultSet setTwo;
    String SQL;
    List<String> enterAsOptions;
    String cands;

    public List getList(){
        ElectionDetails v;
        CandDetails c; 
        list = new ArrayList<>();
        try{
            //System.out.println("Attermpting connection to database");
            Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca?useSSL=false","root","");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca","root","");
            statement = connection.createStatement();      
            
            SQL = "select * from election";
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                v = new ElectionDetails();
                v.setEid(resultSet.getInt(1));
                v.setPrecinct(resultSet.getString(2));
                v.setRace(resultSet.getString(3));
                v.setDate(resultSet.getString(4));
                v.setIsOngoing(resultSet.getInt(5));
                
                statement2 = connection.createStatement(); 
                SQL = "select id, race, first_name, last_name from candidates where race like '" + v.getRace() + "'";
                setTwo = statement2.executeQuery(SQL);
                
                /*while(setTwo.next()){
                    c = new CandDetails();
                    c.setId(setTwo.getInt(1));
                    c.setRace(setTwo.getString(2));
                    c.setCanFirstName(setTwo.getString(3) + " " + setTwo.getString(4));
                    //c.setCanLastName(setTwo.getString(4));
                    list.add(c);
                }*/
                
                cands = "";
                while(setTwo.next()){
                    cands += setTwo.getString(3) + " " + setTwo.getString(4) + ", ";
                }
                try{
                    cands = cands.substring(0, cands.length()-2);
                    v.setCandidates(cands);
                } catch (Exception ex){ }
                list.add(v);
            } 
        } catch(Exception ex) {
            ex.printStackTrace();
            //System.out.println("Exception occured in the process");
            }
            finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }    
            }
    return list;
    }
}
    
    
    

