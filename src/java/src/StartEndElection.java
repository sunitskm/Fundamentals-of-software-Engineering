/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Owner
 */

@ManagedBean
@SessionScoped
public class StartEndElection extends ElectionDBUtil implements Serializable {
    private List<ElectionDetails> ongoingList;
    private List<ElectionDetails> nonOngoingList;
    private CandDetails v;
    private List<CandDetails> list ;
    
    public StartEndElection() { 
        updateLists();
    }
   
    public String end(String eid) {
        System.out.println("election id: " + eid);
        try {
            statement = connect().createStatement();
            SQL = "UPDATE election SET is_ongoing='-1' WHERE id like ('"+ eid +"')";
            statement.executeUpdate(SQL);
            updateLists();
            
            statement = connect().createStatement();
            SQL = "select race from election where id like ('" + eid + "')";
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            String race = resultSet.getString(1);
            System.out.println("Race is "+race);
            
            statement = connect().createStatement();
            SQL = "select id, votes from candidates where race like ('" + race + "')";
            resultSet = statement.executeQuery(SQL);
           
            list = new ArrayList<>();
            while (resultSet.next()){
                 v = new CandDetails();
                v.setId(resultSet.getInt(1));
                v.setVotes(resultSet.getInt(2));
                list.add(v);
            }
            
            int max = -1;
            int winner = 0;
            for (CandDetails c : list){
                System.out.print(c.getId());
                if (c.getVotes() > max){
                    max = c.getVotes();
                    winner = c.getId();
                }    
            }
            System.out.println(winner);
            
            statement = connect().createStatement();
            SQL = "UPDATE election SET winner_id = ('" + winner + "') where id like ('" + eid + "')";
            statement.executeUpdate(SQL);
            
            return "endElection";
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();               
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return "failure";
    }
    
    public String start(String eid) {
        System.out.println("election id: " + eid);
        try {
            statement = connect().createStatement();
            SQL = "UPDATE election SET is_ongoing='1' WHERE id like ('"+ eid +"')";
            statement.executeUpdate(SQL);
            updateLists();
            return "startElection";
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();               
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return "failure";
    }
    
    private void updateLists() {
        ongoingList = new ArrayList<ElectionDetails>();
        nonOngoingList = new ArrayList<ElectionDetails>();
        try {
            statement = connect().createStatement();
            SQL = "SELECT * FROM election WHERE is_ongoing='1'";
            resultSet = statement.executeQuery(SQL);
            if(!resultSet.isBeforeFirst()) { // No elections in election table
                
            } else {
                while(resultSet.next()) {
                    ElectionDetails electionDetails = new ElectionDetails();
                    electionDetails.setEid(resultSet.getInt(1));
                    electionDetails.setPrecinct(resultSet.getString(2));
                    electionDetails.setRace(resultSet.getString(3));
                    electionDetails.setDate(resultSet.getString(4));
                    electionDetails.setIsOngoing(resultSet.getInt(5));
                    ongoingList.add(electionDetails);
                }
            statement.close();               
            }
            statement = connect().createStatement();
            SQL = "SELECT * FROM election WHERE is_ongoing='0'";
            resultSet = statement.executeQuery(SQL);
            
            if(!resultSet.isBeforeFirst()) { // No elections in election table
                
            } else {
                while(resultSet.next()) {
                    ElectionDetails electionDetails = new ElectionDetails();
                    electionDetails.setEid(resultSet.getInt(1));
                    electionDetails.setPrecinct(resultSet.getString(2));
                    electionDetails.setRace(resultSet.getString(3));
                    electionDetails.setDate(resultSet.getString(4));
                    electionDetails.setIsOngoing(resultSet.getInt(5));
                    nonOngoingList.add(electionDetails);
                }
            statement.close();               
            }
        } catch(ClassNotFoundException | SQLException ex) {
        } finally {
            try {
                disconnect();
            } catch(Exception ex) {
            }
        }     
    }

    /**
     * @return the ongoingList
     */
    public List<ElectionDetails> getOngoingList() {
        return ongoingList;
    }

    /**
     * @param ongoingList the ongoingList to set
     */
    public void setOngoingList(List<ElectionDetails> ongoingList) {
        this.ongoingList = ongoingList;
    }

    /**
     * @return the nonOngoingList
     */
    public List<ElectionDetails> getNonOngoingList() {
        return nonOngoingList;
    }

    /**
     * @param nonOngoingList the nonOngoingList to set
     */
    public void setNonOngoingList(List<ElectionDetails> nonOngoingList) {
        this.nonOngoingList = nonOngoingList;
    }
    
}
