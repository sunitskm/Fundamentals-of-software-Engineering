/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lenovo
 */
@SessionScoped
@Named("precinct")
public class SelectPrecinct extends ElectionDBUtil implements Serializable {
   
    private String Precinct;
    private Connection con = null;
    public SelectPrecinct(){
        try{
         
            statement = connect().createStatement(); 
        /*SQL = "Select * from USERREG where approved like ('0')";
        VoterDetails v;
        resultSet = statement.executeQuery(SQL);*/
            
        }
        catch(Exception e){
        }
    }

    public String getPrecinct() {
        return Precinct;
    }

    public void setPrecinct(String Precinct) {
        this.Precinct = Precinct;
    }
    
    
    
   
    /* fill the drop-down list */
    public List<String> getPrecinctList() throws SQLException{
        List<String> list = new ArrayList<String>();
        try{
            statement = connect().createStatement(); 
        SQL = "Select * from ZIP_CODE_DATABASE LIMIT 5";
        CandDetails v;
        resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                
                
                list.add(resultSet.getString(1));
    } 
            return list;
        }
        catch(Exception e){
            return null;
      }

}
}

