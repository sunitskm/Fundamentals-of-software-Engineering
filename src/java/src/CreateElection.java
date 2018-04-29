/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Lenovo
 */
@Named("CreateElection")
@SessionScoped
public class CreateElection extends ElectionDBUtil implements Serializable  {
    private List<String> raceOptions;
    private List<String> electionOptions;
    private String electionRace;
    private String electionType;
    private String electionState;
    private Date electionStartDate;
    private Date electionEndDate;
    private String district;
    private int stateElectionId;
    private int stateCandId;
    private String stateCandFirstName;
    private String stateCandLastName;
    private String stateCandEmailId;
    private  List<CandDetails> list ;
    private List<Integer> listCandId;
    private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();;
    ListCand candidates;

    
    public CreateElection() {
         System.out.println("Inside constructor of add candidates");
        raceOptions = new ArrayList<>();
        raceOptions.add("Governor");
        raceOptions.add("Senate"); 
        
        electionOptions = new ArrayList<>();
        electionOptions.add("State"); 
        electionOptions.add("Presidential");
        electionOptions.add("House of Representatives");
    }

    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Integer, Boolean> checked) {
        this.checked = checked;
    }
    
    public List<String> getRaceOptions() {
        return raceOptions;
    }

    public void setRaceOptions(List<String> raceOptions) {
        this.raceOptions = raceOptions;
    }

    public String getElectionRace() {
        return electionRace;
    }

    public void setElectionRace(String electionRace) {
        this.electionRace = electionRace;
    }

    public String getElectionType() {
        return electionType;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public List<String> getElectionOptions() {
        return electionOptions;
    }

    public void setElectionOptions(List<String> electionOptions) {
        this.electionOptions = electionOptions;
    }
    

    public String getElectionState() {
        return electionState;
    }

    public void setElectionState(String electionState) {
        this.electionState = electionState;
    }

    public Date getElectionStartDate() {
        return electionStartDate;
    }

    public void setElectionStartDate(Date electionStartDate) {
        this.electionStartDate = electionStartDate;
    }

    public Date getElectionEndDate() {
        return electionEndDate;
    }

    public void setElectionEndDate(Date electionEndDate) {
        this.electionEndDate = electionEndDate;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<CandDetails> getList() {
         candidates = new ListCand();
        list = new ArrayList<CandDetails>();
      list = candidates.listCandStateElection(electionRace, electionState);
      System.out.println("Size of list is "+ list.size());
      return list;
    }

    public void setList(List<CandDetails> list) {
        this.list = list;
    }

    public ListCand getCandidates() {
        return candidates;
    }

    public void setCandidates(ListCand candidates) {
        this.candidates = candidates;
    }
    
    public String create(){
        System.out.println("Inside create" + this.electionType);
        if(electionType.equals("Presidential")){
            return "create_federal";
        }
        else if(electionType.equals("State")) {
            System.out.println("Inside State Elections");
            return "create_state";
        }
        else{
            System.out.println("Inside house rep");
            return "create_house_rep";
        }
    }
    public String createStateSelectCand(){
        System.out.println("State and Race are" + electionState + "  " + electionRace);
        return "listStateCandElection";
    }
    public String createStateElection(){
        java.sql.Date sqlStartDate;
        java.sql.Date sqlEndDate;
        sqlStartDate = new java.sql.Date(electionStartDate.getTime());
        sqlEndDate = new java.sql.Date(electionEndDate.getTime());
        for(Map.Entry<Integer, Boolean> entry: checked.entrySet()) {
        System.out.println(entry.getKey() + " : " + entry.getValue());
        if(entry.getValue()==true){
           // listCandId.add(entry.getKey());
           System.out.println("Key is " + entry.getKey());
        }
        }
        //Adding Election
         statement =null;
         connection = null;  
         try{
             System.out.println("Inside try");
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
             
                 System.out.println("Data Source is not null");
                 
                 if(connection!=null){
                 
                 SQL = " insert into election (Election_Type, Race, State, District, Start_Date, End_Date, Winner_Id)"
        + " values (?, ?, ?, ?, ?, ?, ?)";
                  

                 PreparedStatement preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setString (1, electionType);
                  preparedStmt.setString (2, electionRace);
                  preparedStmt.setString (3, electionState);
                  preparedStmt.setString (4, "Nil");
                  preparedStmt.setDate (5, sqlStartDate);
                  preparedStmt.setDate (6, sqlEndDate);
                  preparedStmt.setInt(7, 0);
                 System.out.println("Connection is not null");
                 preparedStmt.execute();      
                 System.out.println("Data Added Successfully");
                 }
             
         }
         catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
         addCandidatesToElection();
         return "admindashboard";
  

        
    }
    
    public void addCandidatesToElection(){
        int elecId = 0;
        Iterator it = checked.entrySet().iterator();
        System.out.println("Inside add candidates to election");
        listCandId = new ArrayList<Integer>();
        while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        System.out.println(pair.getKey() + " = " + pair.getValue());
        System.out.println(pair.getKey() + " = " + pair.getValue());
        boolean value = (Boolean)pair.getValue();
        int key = (Integer)pair.getKey();
        if(value==true){   
            listCandId.add(key);
        }
        it.remove(); // avoids a ConcurrentModificationException
        
    }
        for(int i=0;i<listCandId.size();i++){
            System.out.println("The candidate id is " + listCandId.get(i));
        }
        //Updating candidate In Election to Yes
        statement =null;
         connection = null;  
         try{
             System.out.println("Inside try");
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
             
                 System.out.println("Data Source is not null");
                 for(int i = 0;i<listCandId.size();i++){
                 if(connection!=null){
                 int temp = (int)listCandId.get(i);
                 
                 SQL = " UPDATE cand SET inElection=? WHERE candId = ?";
                  System.out.println("Updating " + temp);
                  PreparedStatement preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setString(1,"YES");
                  preparedStmt.setInt(2,temp);
                   preparedStmt.executeUpdate();      

                 }
                 }
             
         }
         catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
         
         //Getting election Id
        
         
     
       
        try{
            statement = connect().createStatement(); 
        SQL = "Select max(Election_Id) from election";
      
        resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
               elecId = resultSet.getInt(1);
             } 
            System.out.println("Election Id" + elecId);
           
      
        }
        catch(Exception e){
           
        }
        //Adding candidates to election table
        for(int i = 0;i < listCandId.size();i++){
        statement =null;
         connection = null;  
         try{
            statement = connect().createStatement(); 
        SQL = "Select first_name from CAND where candId = ('"+listCandId.get(i)+"')";
      
        resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
              stateCandFirstName = resultSet.getString(1);
             } 
            System.out.println("First Name" + stateCandFirstName);
           
      
        }
        catch(Exception e){
           
        }
         
         statement =null;
         connection = null;  
         try{
            statement = connect().createStatement(); 
        SQL = "Select last_name from CAND where candId = ('"+listCandId.get(i)+"')";
      
        resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
              stateCandLastName = resultSet.getString(1);
             } 
            System.out.println("Last Name" + stateCandLastName);
           
      
        }
        catch(Exception e){
           
        }
         
          statement =null;
         connection = null;  
         try{
            statement = connect().createStatement(); 
        SQL = "Select email_id from CAND where candId = ('"+listCandId.get(i)+"')";
      
        resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
              stateCandEmailId = resultSet.getString(1);
             } 
            System.out.println("Email Id" + stateCandEmailId);
           
      
        }
        catch(Exception e){
           
        }
         
         
         try{
             System.out.println("Inside try");
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_management?useSSL=false","demo","demo");
             
                 System.out.println("Data Source is not null");
                 
                 if(connection!=null){
                 
                 SQL = " insert into election_state (Election_Id, State, Race, Candidate_Id, First_Name, Last_Name, Email_Id)"
        + " values (?, ?, ?, ?, ?, ?, ?)";
                  

                 PreparedStatement preparedStmt = connection.prepareStatement(SQL);  
                  preparedStmt.setInt (1, elecId);
                  preparedStmt.setString (2, electionState);
                  preparedStmt.setString (3, electionRace);
                  preparedStmt.setInt (4, listCandId.get(i));
                  preparedStmt.setString (5, stateCandFirstName);
                  preparedStmt.setString (6, stateCandLastName);
                  preparedStmt.setString (7, stateCandEmailId);
                 System.out.println("Connection is not null");
                 preparedStmt.execute();      
                 System.out.println("Data Added Successfully");
                 }
             
         }
         catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    connection.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
        
        
        
    }
    
}
}
