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
public class queryelections{
    private String zipcode;
    public static ArrayList<ElectionDetails> list = new ArrayList();
    Connection connection;
    Statement statement;
    Statement statement2;
    ResultSet resultSet;
    ResultSet setTwo;
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
        return list;
    }
    
    public String queryData(){
        return dbData(zipcode);
    }
    public String dbData(String zipcode){
        ElectionDetails v;
        CandDetails c; 
        list = new ArrayList<>();
        try{
            //System.out.println("Attermpting connection to database");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca","root","");
            statement = connection.createStatement(); 
            SQL = "select precinct_name from prec_zip where zip_code like " + zipcode;
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            String precinct = resultSet.getString(1);
            
            SQL = "select * from elections where race like 'United States President' or precinct like " + precinct;
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                v = new ElectionDetails();
                v.setEid(resultSet.getString(1));
                v.setPrecinct(resultSet.getString(2));
                v.setRace(resultSet.getString(3));
                v.setDate(resultSet.getString(4));
                v.setIsOngoing(resultSet.getInt(5));  
                
                statement2 = connection.createStatement(); 
                if (v.getRace().equals("United States President")){
                    SQL = "select first_name, last_name from pres_cand where race like '" + v.getRace() + "'";
                    setTwo = statement2.executeQuery(SQL);
                } else{
                    SQL = "select first_name, last_name from congress_cand where race like '" + v.getRace() + "'";
                    setTwo = statement2.executeQuery(SQL);
                }
                
                cands = "";
                while(setTwo.next()){
                    cands += setTwo.getString(1) + " " + setTwo.getString(2) + ", ";
                }
                cands = cands.substring(0, cands.length()-2);
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
    return "listupcomingelections";
    }
}
    
    
    

