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
public class SendEmail  {
    public static void sendApprovedEmail(String email, String name){
        String message = "Congrats!! You have been approved by the admin. You can now vote";
        if (MailGenerator.sendEmail(email, name, "Approved", message))
            System.out.println("Success");
        else 
            System.out.println("Failure");
    }
    
    public static void sendRejectedEmail(String email, String name){
        String message = "Sorry! You have been rejected by the admin. Please enter details again.";
        if (MailGenerator.sendEmail(email, name, "Reject", message))
            System.out.println("Success");
        else 
            System.out.println("Failure");
    }
    
    public static void main(String args[]) {
        //sendEmail.sendApprovedEmail("sunitmishra0110@gmail.com", "Sunit");
        //sendEmail.sendRejectedEmail("sunit-mishra@uiowa.edu", "Sunit");
    }
}
