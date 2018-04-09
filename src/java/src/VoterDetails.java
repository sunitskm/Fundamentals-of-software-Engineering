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
public class VoterDetails {
    private String uid;
    private String userFirstName;
    private String userLastName;
    private String userEmailId;
    private String userSSN;
    private String userStreetAddress;
    private String userCity;
    private String userState;
    private String userZip;
    private String userApproved;
    
    public VoterDetails(){
        System.out.println("Inside Voter Details");
    }

    public VoterDetails(String uid, String userFirstName, String userLastName, String userEmailId, String userSSN, String userStreetAddress, String userCity, String userState, String userZip, String userApproved) {
        this.uid = uid;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmailId = userEmailId;
        this.userSSN = userSSN;
        this.userStreetAddress = userStreetAddress;
        this.userCity = userCity;
        this.userState = userState;
        this.userZip = userZip;
        this.userApproved = userApproved;
        
    }
    
     public VoterDetails(String uid, String userFirstName, String userLastName, String userEmailId, String userSSN, String userStreetAddress, String userCity, String userState, String userZip) {
        this.uid = uid;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmailId = userEmailId;
        this.userSSN = userSSN;
        this.userStreetAddress = userStreetAddress;
        this.userCity = userCity;
        this.userState = userState;
        this.userZip = userZip;      
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserSSN() {
        return userSSN;
    }

    public void setUserSSN(String userSSN) {
        this.userSSN = userSSN;
    }

    public String getUserStreetAddress() {
        return userStreetAddress;
    }

    public void setUserStreetAddress(String userStreetAddress) {
        this.userStreetAddress = userStreetAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }
    
     public String getUserApproved() {
        return userApproved;
    }

    public void setUserApproved(String userApproved) {
        this.userApproved = userApproved;
    }
    
}
