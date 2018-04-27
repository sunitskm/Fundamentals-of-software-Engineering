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
                v.setCanId(Integer.parseInt(resultSet.getString(1)));
                v.setCanFirstName(resultSet.getString(2));
                v.setCanLastName(resultSet.getString(3));
               
                v.setCanEmailId(resultSet.getString(4));
                v.setCanZip(resultSet.getString(5));   
                
                v.setCanCity(resultSet.getString(6));
                v.setCanPrecinct(resultSet.getString(7));
                v.setCanState(resultSet.getString(8));
                v.setCanRace(resultSet.getString(9));   
                v.setCanInElection(resultSet.getString(10));   
                
                list.add(v);
                System.out.println("Inside List candidate Hello");
    } 
           
            return list;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public List<CandDetails> listCandStateElection(String race, String state){
        System.out.println("Inside listCandStateelection of list cand" + race + state);
        List<CandDetails> list = new ArrayList<CandDetails>();
        try{
            statement = connect().createStatement(); 
        SQL = "Select * from CAND where state = ('"+state+"')" + 
                " and race = ('"+race+"')" + " and inElection = 'NO'";
        CandDetails v;
        resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                v = new CandDetails();
                v.setCanId(Integer.parseInt(resultSet.getString(1)));
                v.setCanFirstName(resultSet.getString(2));
                v.setCanLastName(resultSet.getString(3));
               
                v.setCanEmailId(resultSet.getString(4));
                v.setCanZip(resultSet.getString(5));   
                
                v.setCanCity(resultSet.getString(6));
                v.setCanPrecinct(resultSet.getString(7));
                v.setCanState(resultSet.getString(8));
                v.setCanRace(resultSet.getString(9));   
                v.setCanInElection(resultSet.getString(10));   
                
                list.add(v);
                System.out.println("Inside List candidate Bye");
             } 
           
            return list;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
}
