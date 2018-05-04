/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Lenovo
 */

public class ElectionDBUtil {
    private static ElectionDBUtil instance;  
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;
public Connection connect() throws SQLException, ClassNotFoundException{
Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","root","b2xpdmVyMDU=");
        //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
        return connection;
}
public void disconnect(){
    try{
        connection.close();
        statement.close();
        resultSet.close();
    }
    catch (Exception e){
        e.printStackTrace();
    }
}
    
}
