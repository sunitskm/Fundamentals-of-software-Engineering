/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Owner
 */

@ManagedBean
@SessionScoped
public class SecurityQuestionsBean extends ElectionDBUtil{
    private String q1;
    private String q2;
    private String answerQ1;
    private String answerQ2;
    private String correctAnswerQ1;
    private String correctAnswerQ2;
    
    /**
     * @return the Q1
     */
    public String getQ1() {
        return q1;
    }

    /**
     * @param Q1 the Q1 to set
     */
    public void setQ1(String q1) {
        this.q1 = q1;
    }

    /**
     * @return the Q2
     */
    public String getQ2() {
        return q2;
    }

    /**
     * @param Q2 the Q2 to set
     */
    public void setQ2(String q2) {
        this.q2 = q2;
    }

    /**
     * @return the correctAnswerQ1
     */
    public String getCorrectAnswerQ1() {
        return correctAnswerQ1;
    }

    /**
     * @param correctAnswerQ1 the correctAnswerQ1 to set
     */
    public void setCorrectAnswerQ1(String correctAnswerQ1) {
        this.correctAnswerQ1 = correctAnswerQ1;
    }

    /**
     * @return the correctAnswerQ2
     */
    public String getCorrectAnswerQ2() {
        return correctAnswerQ2;
    }

    /**
     * @param correctAnswerQ2 the correctAnswerQ2 to set
     */
    public void setCorrectAnswerQ2(String correctAnswerQ2) {
        this.correctAnswerQ2 = correctAnswerQ2;
    }
    
    /**
     * @return the answerQ1
     */
    public String getAnswerQ1() {
        return answerQ1;
    }

    /**
     * @param answerQ1 the answerQ1 to set
     */
    public void setAnswerQ1(String answerQ1) {
        this.answerQ1 = answerQ1;
    }

    /**
     * @return the answerQ2
     */
    public String getAnswerQ2() {
        return answerQ2;
    }

    /**
     * @param answerQ2 the answerQ2 to set
     */
    public void setAnswerQ2(String answerQ2) {
        this.answerQ2 = answerQ2;
    }
    
    
   
    public String getQuestion(String uid, int question) {
        try {
            statement = connect().createStatement();
            SQL = "Select * from users_security_questions where userid like ('" + uid + "')";
            resultSet = statement.executeQuery(SQL);
            
            System.out.println("resultSet.isBeforeFirst() returned: " + resultSet.isBeforeFirst() + "\n");
            if(!resultSet.isBeforeFirst()) {
                return "No question found for this user.";
            } else {
                resultSet.next();
                setCorrectAnswerQ1(resultSet.getString(4));
                setCorrectAnswerQ2(resultSet.getString(5));
                return resultSet.getString(question);
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "Failed to get questions for uid: " + uid + ". A connection error may have occrued.";     
    }
    
    public String verifyAnswers() {
        if(answerQ1.equals(correctAnswerQ1) && answerQ2.equals(correctAnswerQ2)) {
            return "changePassword";
        } else {
            return "verifyIdentityFailure";
        }
    }
    
    public String updateSecurityQuestions(String uid) {
       try {
           statement = connect().createStatement();
           //SQL = "UPDATE users_security_questions SET security_q1='"+q1+"', security_q2='"+q2+"', security_a1='"+correctAnswerQ1+"', security_a2='"+correctAnswerQ2+"' WHERE userid like ('"+ uid +"') ";
           SQL = "INSERT INTO users_security_questions VALUES ('"+uid+"','"+q1+"','"+q2+"','"+correctAnswerQ1+"','"+correctAnswerQ2+"')";
           System.out.println(SQL + "\n");
           statement.executeUpdate(SQL);

       } catch(Exception ex) {
           ex.printStackTrace();
       }
       
       return "changePassword";
    }
    
}
