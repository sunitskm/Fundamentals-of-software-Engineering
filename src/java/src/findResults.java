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
public class findResults implements Serializable{
    public static ArrayList<ElectionDetails> list = new ArrayList();
    Connection connection;
    Statement statement;
    Statement statement2;
    ResultSet resultSet;
    ResultSet setTwo;
    private String zipcode;
    String SQL;
    List<String> enterAsOptions;
    String cands;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public List getList(){
        dbData();
        return list;
    }
    
    public String queryData(){
        return dbData();
    }
    public String dbData(){
        ElectionDetails v;
        CandDetails c; 
        list = new ArrayList<>();
        try{
            //System.out.println("Attermpting connection to database");
            Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca","root","");
            statement = connection.createStatement();      
            
            SQL = "select * from election where winner_id not like 0 and is_ongoing like 0";
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                v = new ElectionDetails();
                v.setEid(resultSet.getInt(1));
                v.setPrecinct(resultSet.getString(2));
                v.setRace(resultSet.getString(3));
                v.setDate(resultSet.getString(4));
                v.setIsOngoing(resultSet.getInt(5));
                v.setWinnerId(resultSet.getInt(6));
                
                statement2 = connection.createStatement(); 
                SQL = "select first_name, last_name from candidates where id like ('" + v.getWinnerId() + "')";
                setTwo = statement2.executeQuery(SQL);
                
                cands = "";
                while(setTwo.next()){
                    cands += setTwo.getString(1) + " " + setTwo.getString(2);
                }
                v.setCandidates(cands);
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
    return "results";
    }
}
    
    
    

