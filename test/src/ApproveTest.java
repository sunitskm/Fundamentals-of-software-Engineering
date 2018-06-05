/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class ApproveTest extends ElectionDBUtil {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Owner
 */

    
    public ApproveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login instance = new Login();
        instance.setLoginId("admin");
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testApproveUsers() {
        String SQL;
        String result = "";
        System.out.println("getDbLoginId");
        String uid = "testUser";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
            SQL = " insert into userreg (userid, first_name, last_name,email_id,ssn,st_addr,city,state,zip,type,approved)"
        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                 PreparedStatement preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setString (1, "testUser");
                  preparedStmt.setString (2, "test1");
                  preparedStmt.setString (3, "test1");
                  preparedStmt.setString (4, "test1@test.com");
                  preparedStmt.setString (5, "333-75-3333");
                  preparedStmt.setString (6, "Test");
                  preparedStmt.setString (7, "Test");
                  preparedStmt.setString (8, "test");
                  preparedStmt.setString (9, "52246");
                  preparedStmt.setString (10, "User");
                  preparedStmt.setString (11, "0");
                  preparedStmt.execute(); 
                  Statement statement = connection.createStatement();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        ApproveReject instance = new ApproveReject();
        instance.approve("testUser");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
            Statement statement = connection.createStatement();
            
            SQL = "SELECT approved FROM userreg WHERE userid like ('testUser')";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getString(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        assertEquals('1', result);
        
        try {
            statement = connect().createStatement();
            SQL = "DELETE from user_voting where userid like ('"+ uid +"')";
            statement.executeUpdate(SQL);
           
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                disconnect();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Test of setDbLoginId method, of class login.
     */
    @Test
    public void testYRejectUsers() {
         String uid = "testUser";
        String SQL;
        String result = "";
        System.out.println("getDbLoginId");
        String expResult = "castned";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
            SQL = " insert into userreg (userid, first_name, last_name,email_id,ssn,st_addr,city,state,zip,type,approved)"
        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                 PreparedStatement preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setString (1, "testUser1");
                  preparedStmt.setString (2, "test1");
                  preparedStmt.setString (3, "test1");
                  preparedStmt.setString (4, "test1@test.com");
                  preparedStmt.setString (5, "323-75-3333");
                  preparedStmt.setString (6, "Test");
                  preparedStmt.setString (7, "Test");
                  preparedStmt.setString (8, "test");
                  preparedStmt.setString (9, "52246");
                  preparedStmt.setString (10, "User");
                  preparedStmt.setString (11, "0");
                  preparedStmt.execute(); 
                  Statement statement = connection.createStatement();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        ApproveReject instance = new ApproveReject();
        instance.reject("testUser");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
            Statement statement = connection.createStatement();
            
            SQL = "SELECT approved FROM userreg WHERE userid like ('testUser')";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getString(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        assertEquals('0', result);
        try {
            statement = connect().createStatement();
            SQL = "DELETE from user_voting where userid like ('"+ uid +"')";
            statement.executeUpdate(SQL);
           
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                disconnect();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Test of setDbLoginId method, of class login.
     */
    
}


