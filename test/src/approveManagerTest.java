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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class approveManagerTest extends ElectionDBUtil{
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Owner
 */

    
    public approveManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        login instance = new login();
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
        String expectedResult = "";
        String actualResult = "";
        ApproveReject ap = new ApproveReject();
        String uid = "test1";
        try {
            
            SQL = "INSERT INTO user_voting VALUES ('"+uid+"','0','0','0','0')";
            ap.statement.executeUpdate(SQL);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                ap.statement.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        checkUserVoting instance = new checkUserVoting();
        instance.approveVoter(uid);
        
        
        try {
            statement = connect().createStatement();
            SQL = "Select * from user_voting where userid like ('"+ uid +"')";
            resultSet = statement.executeQuery(SQL);
                resultSet.next();
                System.out.println("get status\n"+"get status\n"+"get status\n"+"get status\n"+"get status\n"+resultSet.getString(5));
                
                actualResult = resultSet.getString(5); 
                    
                    
        }     
                
                   
         catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        assertEquals('1', actualResult);
    

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
}



    

