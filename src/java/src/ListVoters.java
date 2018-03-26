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
public class ListVoters extends ElectionDBUtil{
    
    public List<VoterDetails> listVoters(){
        List<VoterDetails> list = new ArrayList<VoterDetails>();
        try{
            statement = connect().createStatement(); 
        SQL = "Select * from USERREG where approved like ('0')";
        VoterDetails v;
        resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                v = new VoterDetails();
                v.setUid(resultSet.getString(1));
                v.setUserFirstName(resultSet.getString(2));
                v.setUserLastName(resultSet.getString(3));
                v.setUserEmailId(resultSet.getString(4));
                v.setUserSSN(resultSet.getString(5));
                v.setUserStreetAddress(resultSet.getString(6));
                v.setUserCity(resultSet.getString(7));
                v.setUserState(resultSet.getString(8));
                v.setUserZip(resultSet.getString(9));   
                list.add(v);
    } 
            return list;
        }
        catch(Exception e){
            return null;
        }
        
    }
}
