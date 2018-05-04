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
public class queryelections implements Serializable{
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
        return list;
    }
    
    public String queryData(){
        
        try{
        int zip = Integer.parseInt(zipcode);
         return dbData(zip);
        }
        catch(Exception ex){
            return "incorrectZip";
        }
       
    }
    public String dbData(int zipcode){
        ElectionDetails v;
        CandDetails c; 
        list = new ArrayList<>();
        try{
            //System.out.println("Attermpting connection to database");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca","root","");
            statement = connection.createStatement();      
            
            SQL = "select precinct_name from prec_zip where zip_code like " + zipcode;
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            String precinct = resultSet.getString(1);
            
            SQL = "select * from election where (race like 'United States President' or precinct like ('" + precinct + "')) and is_ongoing like 1";
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
    return "listupcomingelections";
    }
}
    
    
    

