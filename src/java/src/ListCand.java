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
        SQL = "Select * from CAND";
        CandDetails v;
        resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                v = new CandDetails();
                v.setCanFirstName(resultSet.getString(1));
                v.setCanLastName(resultSet.getString(2));
               
                v.setCanEmailId(resultSet.getString(3));
                v.setCanZip(resultSet.getString(4));   
                
                v.setCanCity(resultSet.getString(5));
                v.setCanState(resultSet.getString(6));
                v.setCanRace(resultSet.getString(7));   
                v.setCanInElection(resultSet.getString(8));   
                
                list.add(v);
    } 
            return list;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
}
