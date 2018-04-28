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
                v.setRace(resultSet.getString(1));
                v.setCanFirstName(resultSet.getString(2));
                v.setCanLastName(resultSet.getString(3));
                v.setVotes(resultSet.getInt(5));
                
                list.add(v);
    } 
            return list;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
}
