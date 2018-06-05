/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author Lenovo
 */

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class ListCandTest extends ElectionDBUtil {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Owner
 */

    
    public ListCandTest() {
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
    public void testListCandidates() {
        String SQL;
        String result = "";
        
        List<CandDetails> expectedList = new ArrayList<CandDetails>();
        List<CandDetails> actualList = new ArrayList<CandDetails>();
        ListCand instance = new ListCand();
        expectedList = instance.listCand();
        try {
            statement = connect().createStatement(); 
        SQL = "Select * from candidates";
        CandDetails v;
        resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                v = new CandDetails();
                v.setId(Integer.parseInt(resultSet.getString(1)));
                v.setRace(resultSet.getString(2));
                v.setCanFirstName(resultSet.getString(3));
                v.setCanLastName(resultSet.getString(4));
                v.setState(resultSet.getString(5));
                v.setCity(resultSet.getString(6));
                v.setZip(resultSet.getString(7)); 
                v.setVotes(resultSet.getInt(8));
                actualList.add(v);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        
        assertEquals(expectedList.size(), actualList.size());
    }
    }

    /**
     * Test of setDbLoginId method, of class login.
     */
    
    
    





