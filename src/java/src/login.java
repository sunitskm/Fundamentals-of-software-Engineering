/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

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
import static src.PasswordHashingDemo.SALT;
import static src.PasswordHashingDemo.generateHash;
/**
 *
 * @author Lenovo
 */
@ManagedBean
@SessionScoped
public class login {
    private String loginId;
    private String loginPassword;
    private String loginEnterAs;
    private String dbLoginId;
    private String dbLoginPassword;
    private String dbLoginEnterAs;
    private static final String SALT = "my-secret-text";
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;
    List<String> enterAsOptions;
    public login(){
        //populate list with data
        enterAsOptions = new ArrayList<>();
        enterAsOptions.add("Admin");
        enterAsOptions.add("Manager");
        enterAsOptions.add("User"); 
    }

    public List<String> getEnterAsOptions() {
        return enterAsOptions;
    }
    
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginEnterAs() {
        return loginEnterAs;
    }

    public void setLoginEnterAs(String loginEnterAs) {
        this.loginEnterAs = loginEnterAs;
    }

    public String getDbLoginId() {
        return dbLoginId;
    }

    public void setDbLoginId(String dbLoginId) {
        this.dbLoginId = dbLoginId;
    }

    public String getDbLoginPassword() {
        return dbLoginPassword;
    }

    public void setDbLoginPassword(String dbLoginPassword) {
        this.dbLoginPassword = dbLoginPassword;
    }

    public String getDbLoginEnterAs() {
        return dbLoginEnterAs;
    }

    public void setDbLoginEnterAs(String dbLoginEnterAs) {
        this.dbLoginEnterAs = dbLoginEnterAs;
    }
    public void dbData(String loginId){
    try{
        //System.out.println("Attermpting connection to database");
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca?useSSL=false","root","");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca","root","");
        statement = connection.createStatement(); 
        SQL = "Select * from USER01 where userid like ('" + loginId +"')";
        resultSet = statement.executeQuery(SQL);
        resultSet.next();
        dbLoginId = resultSet.getString(1).toString();
        dbLoginPassword = resultSet.getString(2).toString();
        dbLoginEnterAs = resultSet.getString(3).toString();
       
    }
    catch(Exception ex){
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
    }
    
    public String checkValidUser(){
        
        dbData(loginId);
        if(loginId.equalsIgnoreCase(dbLoginId))
        {
            System.out.println("User Id is correct" + loginEnterAs);
        //    System.out.println("User Id is correct" + dbLoginEnterAs);
            String saltedPassword = SALT + loginPassword;
		String hashedPassword = generateHash(saltedPassword);
                System.out.println("Hashed Password" + hashedPassword);
                //loginPassword = hashedPassword;
  
            if((loginPassword.equals(dbLoginPassword)) && (loginEnterAs.equals(dbLoginEnterAs)) ){
               System.out.println("Password is correct");
               String validated = validateUserReg();
                if(validated.equals("NONE") && loginEnterAs.equals("User"))
                    return "userRegistration";
                else if(validated.equals("0") && loginEnterAs.equals("User"))
                    return "success_reg";
                else if((validated.equals("1") && loginEnterAs.equals("User")))
                    return "userdashboard";
                else if(( loginEnterAs.equals("Manager")))
                    return "managerdashboard";
                else if(( loginEnterAs.equals("Admin")))
                    return "admindashboard";
                else
                    System.out.println("Ahaha. This is failing mein");
                    return "failure";
            }
                    
            else
            {
               return "failure";
            }
        }
        else
        {
            return "failure";
        }
        
        
    }
    public String logout(){
    return "login";
    }
    public String validateUserReg(){
        String appr = "0";
        try{
            //System.out.println("Attermpting connection to database");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca?useSSL=false","root","");
            statement = connection.createStatement(); 
            SQL = "Select * from USERREG where userid like ('" + loginId +"')";
            resultSet = statement.executeQuery(SQL);
            if(resultSet.next() && loginEnterAs.equals("User"))
                appr = resultSet.getString(11).toString();
            else
                return "NONE";
        } catch(Exception ex){
            ex.printStackTrace();
        //System.out.println("Exception occured in the process");
        }
        if(appr.equals("0"))
            return "0";
        else 
            return "1";
        
    }
     public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
    }
    

