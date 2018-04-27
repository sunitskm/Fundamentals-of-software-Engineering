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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import static src.PasswordHashingDemo.generateHash;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@RequestScoped
public class register {
    private String registerUserId;
    private String registerPassword;
    private String registerConfirmPassword;
    private String registerRegisterAs;
    private String dbRegisterUserId;
    private static final String SALT = "my-secret-text";
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;
    
    public register(){
        
        
    }
    public String getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(String registerUserId) {
        this.registerUserId = registerUserId;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    public String getRegisterConfirmPassword() {
        return registerConfirmPassword;
    }

    public void setRegisterConfirmPassword(String registerConfirmPassword) {
        this.registerConfirmPassword = registerConfirmPassword;
    }

    public String getRegisterRegisterAs() {
        return registerRegisterAs;
    }

    public void setRegisterRegisterAs(String registerRegisterAs) {
        this.registerRegisterAs = registerRegisterAs;
    }

    public String getDbRegisterUserId() {
        return dbRegisterUserId;
    }

    public void setDbRegisterUserId(String dbRegisterUserId) {
        this.dbRegisterUserId = dbRegisterUserId;
    }
   
     public String add(){
         if(registerPassword.equals(registerConfirmPassword)){
         System.out.println("Inside Add");
         int i=0;
         statement =null;
         connection = null;
         try{
             System.out.println("Inside try");
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sca?useSSL=false","root","");
             
                 System.out.println("Data Source is not null");
                 
                 if(connection!=null){
                     String saltedPassword = SALT + registerPassword;
                     String hashedPassword = generateHash(saltedPassword);
                     System.out.println (hashedPassword);
                 SQL = " insert into user01 (userid, password, type)"
        + " values (?, ?, ?)";

                 PreparedStatement preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setString (1, registerUserId);
                  preparedStmt.setString (2, hashedPassword);
                  preparedStmt.setString (3, registerRegisterAs);
                 System.out.println("Connection is not null" + registerUserId + registerPassword + registerRegisterAs);
                 System.out.println("Connection is not null");
                 preparedStmt.execute();      
                 i=1;
                 System.out.println("Data Added Successfully");
                 }
             
         }
         catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
        if(i>0){
            return "success";
        }
        else{
            return "unsuccess";
        }
            
        
    }
        else{
            return "unsuccess";
    }
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
    

