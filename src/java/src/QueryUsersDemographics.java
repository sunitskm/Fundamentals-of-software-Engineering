/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

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
 * @author Owner
 */

@ManagedBean
@SessionScoped
public class QueryUsersDemographics {
    private String city;
    private String state;
    private String zipcode;
    private static ArrayList<VoterDetails> list = new ArrayList();
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getZipcode() {
        return zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public List getList() {
        return list;
    }
    
    public String queryData() {
        return dbData(city, state, zipcode);
    }
    
    public String dbData(String city, String state, String zipcode) {
        VoterDetails v;
        list = new ArrayList<>();
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
            statement = connection.createStatement();
            
            System.out.println("Determining appropriate SQL statement.\n");
            if(!(city.equals("") || state.equals("") || zipcode.equals(""))) {
                System.out.println("no field was empty.\n");
                SQL = "Select * from userreg where city like ('"+ city +"') and state like ('"+ state +"') and zip like ('"+ zipcode +"')";
            } else if(!(city.equals("") || state.equals(""))) {
                System.out.println("only zipcode field was empty.\n");
                SQL = "Select * from userreg where city like ('"+ city +"') and state like ('"+ state +"')";
            } else if(!(city.equals("") || zipcode.equals(""))) {
                System.out.println("only state field was empty.\n");
                SQL = "Select * from userreg where city like ('"+ city +"') and zip like ('"+ zipcode +"')";
            } else if(!(state.equals("") || zipcode.equals(""))) {
                System.out.println("only city field was empty.\n");
                SQL = "Select * from userreg where state like ('"+ state +"') and zip like ('"+ zipcode +"')";
            } else if(!(city.equals(""))) {
                System.out.println("only city field was not empty.\n");
                SQL = "Select * from userreg where city like ('"+ city +"')";    
            } else if(!(state.equals(""))) {
                System.out.println("only state field was not empty.\n");
                SQL = "Select * from userreg where state like ('"+ state +"')";
            } else if(!(zipcode.equals(""))) {
                System.out.println("only zipcode field was not empty.\n");
                SQL = "Select * from userreg where zip like ('"+ zipcode +"')";
            } else { 
                System.out.println("all fields were empty.\n");
                return "demoSearchResult"; 
            }
            
            System.out.println("Determined appropriate SQL statement.\n");
            System.out.println("SQL statement: " + SQL + "\n");

            resultSet = statement.executeQuery(SQL);
            
            while(resultSet.next()) {
                v = new VoterDetails();
                v.setUid(resultSet.getString(1));
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
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }       
        return "demoSearchResult";
    }    
}
