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


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.ConnectionPoolDataSource ;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	@Resource(name = "jdbc/election_management")
	private DataSource dataSource;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
                
                System.out.print("sdasdad");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();
                        //System.out.println("saddadasasfsfdsfs="+myConn.);
                        
			String sql;
                    sql = "select * from user01";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);
                        
			while (myRs.next()) {
				String email = myRs.getString("password");
				out.println(email);
				System.out.println(email);
			}
		} catch (SQLException exc) {
			//out.println(exc.getMessage());
                        exc.printStackTrace();
		}
	}

}
