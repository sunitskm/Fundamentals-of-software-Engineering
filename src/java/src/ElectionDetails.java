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
public class ElectionDetails {
    private int eid;
    private String precinct;
    private String race;
    private String date;
    private int isOngoing;
    private int winnerId;
    private String candidates;

    
    public ElectionDetails(){
        System.out.println("Inside Election Details");
    }

    public ElectionDetails(int eid, String precinct, String race, String date, int isOngoing) {
        this.eid = eid;
        this.precinct = precinct;
        this.race = race;
        this.date = date;
        this.isOngoing = isOngoing;
        this.winnerId = 0;
    }
    
     public ElectionDetails(int eid, String precinct, String race, String date) {
        this.eid = eid;
        this.precinct = precinct;
        this.race = race;
        this.date = date;
        this.isOngoing = 0;
        this.winnerId = 0;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getPrecinct() {
        return precinct;
    }

    public void setPrecinct(String precinct) {
        this.precinct = precinct;
    }
    
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsOngoing() {
        return isOngoing;
    }

    public void setIsOngoing(int isOngoing) {
        this.isOngoing = isOngoing;
    }
    
    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }
    
    public String getCandidates() {
        return candidates;
    }

    public void setCandidates(String candidates) {
        this.candidates = candidates;
    }
}
