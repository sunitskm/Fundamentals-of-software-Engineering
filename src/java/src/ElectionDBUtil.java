/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.ResultSet;
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
    
}
