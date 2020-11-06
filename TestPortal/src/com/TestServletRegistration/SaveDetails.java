package com.TestServletRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveDetails")

public class SaveDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String first_name= request.getParameter("First Name");
        String second_name=request.getParameter("Last Name");
        String father_name=request.getParameter("Fathers Name");
        String email=request.getParameter("email");
        String mobile_number=request.getParameter("Mobile Number");
        String dob=request.getParameter("dob");
        String gender=request.getParameter("gender");
        int age=Integer.parseInt(request.getParameter("age"));
        String state=request.getParameter("stt");
        String address=request.getParameter("address");
        String collegename=request.getParameter("college");
        String course=request.getParameter("sttt");
        String subject=request.getParameter("course");
        String year=request.getParameter("year");
        
        PrintWriter out= response.getWriter();
        out.println("<html>\r\n" + 
        		"<head>\r\n" + 
        		"     <title>Registered</title>\r\n" + 
        		"     <link rel=\"stylesheet\" href=\"loginpage_css.css\">\r\n" + 
        		"	 <link href=\"https://fonts.googleapis.com/css?family=Abel\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
        		"</head>\r\n" + 
        		"<body>\r\n" + 
        		"  <header><center><h1>You are registered</h1></center></header><br/><br/><br/>\r\n" + 
        		" <center> <p> You can login now with your email id as your username and your password is sent to your email address</p> </center>\r\n" + 
        		"<center><a href=\"http://localhost:8080/TestServlet/index.html\">back to homepage</a></center>\r\n" + 
        		"	\r\n" + 
        		"</body>\r\n" + 
        		"</html>");   
        int n=8;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"; 
        StringBuilder sb = new StringBuilder(n); 
        for (int i = 0; i < n; i++) { 
        int index = (int)(AlphaNumericString.length() * Math.random()); 
        sb.append(AlphaNumericString .charAt(index)); 
        } 
        String password=sb.toString();
        PreparedStatement pst = null;
        PreparedStatement pst2=null;
		  try 
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
		pst=con.prepareStatement("insert into student_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		pst.setString(1,first_name);
		pst.setString(2,second_name);
		pst.setString(3,father_name);
		pst.setString(4,email);
		pst.setString(5,mobile_number);
		pst.setString(6,dob);
		pst.setInt(7, age);
		pst.setString(8, state);
		pst.setString(9, address);
		pst.setString(10, gender);
		pst.setString(11, collegename);
		pst.setString(12, course);
		pst.setString(13, subject);
		pst.setString(14, year);
		pst.executeUpdate(); 
		pst.close();
		pst2=con.prepareStatement("insert into studentLogin values(?,?)");
		pst2.setString(1, email);
		pst2.setString(2, password);
		pst2.executeUpdate();
		pst2.close();
		con.close();
		}
		catch(Exception e)
		  {
			  e.printStackTrace();		  
		}
	   
       
        
	}

}
