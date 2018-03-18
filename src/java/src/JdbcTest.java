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
import java.sql.*;
public class JdbcTest {
    public static void main(String[]args){
    try{
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management","demo","demo");
        Statement myStat = myConn.createStatement();
        ResultSet myRe = myStat.executeQuery("select * from user01");
        while(myRe.next()){
            System.out.println(myRe.getString("password"));
        }
    }
    catch (Exception exc){
        exc.printStackTrace();
    }
    }
}
