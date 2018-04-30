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
public class queryusers{

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
    private String firstName;
    private String lastName;
    private String zipcode;
    private String ssn;
    public static ArrayList<VoterDetails> list = new ArrayList();
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;
    List<String> enterAsOptions;
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public List getList(){
        return list;
    }
    
    public String getssn(){
        return ssn;
    }
    
    public void setssn(String ssn){
        this.ssn = ssn;
    }
    
    public String queryData(){
        String zip = zipcode;
        return dbData(firstName, lastName, ssn, zip);
    }
    
    private String uid;
    public String dbData(String firstName, String lastName, String ssn, String zipcode){
        VoterDetails v;
        list = new ArrayList<>();
        try{
            //System.out.println("Attermpting connection to database");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management","demo","demo");
            statement = connection.createStatement(); 
            SQL = "Select * from userreg where first_name like ('" + firstName +"') and last_name like ('" + lastName + "') and ssn like ('" + ssn + "') and zip like ('" + zipcode + "')";
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                    v = new VoterDetails();
                    v.setUid(resultSet.getString(1));
                    setUid(resultSet.getString(1));
                    v.setUserFirstName(resultSet.getString(2));
                    v.setUserLastName(resultSet.getString(3));
                    v.setUserEmailId(resultSet.getString(4));
                    v.setUserSSN(resultSet.getString(5));
                    v.setUserStreetAddress(resultSet.getString(6));
                    v.setUserCity(resultSet.getString(7));
                    v.setUserState(resultSet.getString(8));
                    v.setUserZip(resultSet.getString(9));  
                    v.setUserApproved(resultSet.getString(11));
                    v.setUserZip(resultSet.getString(9));   
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
    return "listqueryusers";
    }
}
    
    
    

