package com.TestServletStudentLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String username=request.getParameter("username");
	 String password=request.getParameter("password");
	 PrintWriter out=response.getWriter();
	 PreparedStatement st=null;
	 ResultSet rs=null;
	 try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
			st=con.prepareStatement("select email,password from studentlogin where email=? and password=?");
			st.setString(1, username);
			st.setString(2, password);
			rs=st.executeQuery();
			if(rs.next())
			{
			  out.println("Start Test");
			}
			else {
				out.print("wrong username/pass");
			}
			st.close();
			con.close();
	     }
	 catch(Exception e) {
		 e.printStackTrace();
	 }
	 
	}
}
