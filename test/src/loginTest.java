/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Owner
 */
public class loginTest {
    
    public loginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login instance = new Login();
        instance.setLoginId("castned");
        
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

    /**
     * Test of getEnterAsOptions method, of class login.
     */
    @Test
    public void testGetEnterAsOptions() {
        System.out.println("getEnterAsOptions");
        Login instance = new Login();
        List<String> expResult = new ArrayList<>();//null;
        expResult.add("Admin");
        expResult.add("Manager");
        expResult.add("User");
        List<String> result = instance.getEnterAsOptions();
        assertEquals(expResult, result);
        //instance = setUpClass.instance;
    }

    /**
     * Test of getLoginId method, of class login.
     */
    @Test
    public void testGetLoginId() {
        System.out.println("getLoginId");
        Login instance = new Login();
        instance.setLoginId("castned");
        String expResult = "castned";
        String result = instance.getLoginId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoginId method, of class login.
     */
    @Test
    public void testSetLoginId() {
        System.out.println("setLoginId");
        String loginId = "castned";
        Login instance = new Login();
        instance.setLoginId(loginId);
        assertEquals(instance.getLoginId(),loginId);
    }

    /**
     * Test of getLoginPassword method, of class login.
     */
    @Test
    public void testGetLoginPassword() {
        System.out.println("getLoginPassword");
        Login instance = new Login();
        instance.setLoginPassword("password");
        String expResult = "password";
        String result = instance.getLoginPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoginPassword method, of class login.
     */
    @Test
    public void testSetLoginPassword() {
        System.out.println("setLoginPassword");
        String loginPassword = "password";
        Login instance = new Login();
        instance.setLoginPassword(loginPassword);
        assertEquals(loginPassword,instance.getLoginPassword());
    }

    /**
     * Test of getLoginEnterAs method, of class login.
     */
    @Test
    public void testGetLoginEnterAs() {
        System.out.println("getLoginEnterAs");
        Login instance = new Login();
        String expResult = null;
        String result = instance.getLoginEnterAs();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDbLoginId method, of class login.
     */
    @Test
    public void testGetDbLoginId() {
        String result = "";
        System.out.println("getDbLoginId");
        String expResult = "castned";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM user01 WHERE userid like ('castned')";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getString(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setDbLoginId method, of class login.
     */
    @Test
    public void testSetDbLoginId() {
        System.out.println("setDbLoginId");
        String dbLoginId = "newId";
        String uid = "test3";
        String result = "";
        String expResult = "newId";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            Statement statement = connection.createStatement();
            String SQLSetDbLogin = "UPDATE user01 SET userid='"+ dbLoginId +"' WHERE userid like ('"+ uid +"')";
            statement.executeUpdate(SQLSetDbLogin);
            String SQL = "SELECT * FROM user01 WHERE userid like ('castned')";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getString(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getDbLoginPassword method, of class login.
     */
    @Test
    public void testGetDbLoginPassword() {
        String result = "";
        System.out.println("getDbLoginPassword");
        String expResult = "59e5543f7934674901af816b21a410da8636fd2b"; // hashed password
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            Statement statement = connection.createStatement();
            String SQL = "SELECT password FROM user01 WHERE userid like ('castned')";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getString(1); 
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setDbLoginPassword method, of class login.
     */
    @Test
    public void testSetDbLoginPassword() {
        System.out.println("setDbLoginPassword");
        String dbNewPassword = "newpassword";
        String uid = "test3";
        String result = "";
        String expResult = "9a9c851571b064ba3063c59b4c978449ce2e9219"; // hash for password = newpassword
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            Statement statement = connection.createStatement();
            String SQLSetDbLogin = "UPDATE user01 SET password='"+ dbNewPassword +"' WHERE userid like ('"+ uid +"')";
            statement.executeUpdate(SQLSetDbLogin);
            String SQL = "SELECT password FROM user01 WHERE userid like ('castned')";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getString(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getDbLoginEnterAs method, of class login.
     */
    @Test
    public void testGetDbLoginEnterAs() {
        System.out.println("getDbLoginEnterAs");
        String result = "";
        String expResult = "User";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            Statement statement = connection.createStatement();
            String SQL = "SELECT type FROM user01 WHERE userid like ('castned')";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getString(1); 
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setDbLoginEnterAs method, of class login.
     */
    @Test
    public void testSetDbLoginEnterAs() {
        System.out.println("setDbLoginEnterAs");
        String dbNewLoginEnterAs = "Admin";
        String uid = "test3";
        String result = "";
        String expResult = "Admin";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            Statement statement = connection.createStatement();
            String SQLSetDbLogin = "UPDATE user01 SET type='"+ dbNewLoginEnterAs +"' WHERE userid like ('"+ uid +"')";
            statement.executeUpdate(SQLSetDbLogin);
            String SQL = "SELECT type FROM user01 WHERE userid like ('test3')";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getString(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of dbData method, of class login.
     */
    @Test
    public void testDbData() {
        System.out.println("dbData");
        String loginId = "";
        Login instance = new Login();
        instance.dbData(loginId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkValidUser method, of class login.
     */
    @Test
    public void testCheckValidUser() {
        System.out.println("checkValidUser");
        Login instance = new Login();
        String expResult = "userdashboard";
        String result = instance.checkValidUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of logout method, of class login.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        Login instance = new Login();
        String expResult = "login";
        String result = instance.logout();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateUserReg method, of class login.
     */
    @Test
    public void testValidateUserReg() {
        System.out.println("validateUserReg");
        String result = "";
        String expResult = "1"; // user castned is already approved
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
            Statement statement = connection.createStatement();
            String SQL = "SELECT approved FROM userreg WHERE userid like ('castned')";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getString(1); 
        } catch(Exception ex) {
            ex.printStackTrace();
        }      
        assertEquals(expResult, result);
    }

    /**
     * Test of generateHash method, of class login.
     */
    @Test
    public void testGenerateHash() {
        System.out.println("generateHash");
        String SALT = "my-secret-text";
        String input = SALT + "castned"; // password for castned is castned
        String expResult = "59e5543f7934674901af816b21a410da8636fd2b";
        String result = Login.generateHash(input);
        assertEquals(expResult, result);
    }
    
}
