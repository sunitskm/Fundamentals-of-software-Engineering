/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ListCand extends ElectionDBUtil{
    public List<CandDetails> listCand(){
        List<CandDetails> list = new ArrayList<CandDetails>();
        try{
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
                list.add(v);
                System.out.println("Inside List candidate Hello");
    } 
           
            return list;
        }
        catch(Exception e){
            return null;
        }
        
    }
}
