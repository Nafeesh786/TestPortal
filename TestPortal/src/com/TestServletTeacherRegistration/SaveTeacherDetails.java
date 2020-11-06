package com.TestServletTeacherRegistration;

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

@WebServlet("/SaveTeacherDetails")
public class SaveTeacherDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("First Name");
        String email=request.getParameter("email");
        String mobile_num=request.getParameter("Mobile Number");
        String gender=request.getParameter("gender");
        String college=request.getParameter("college");
        String course=request.getParameter("sttt");
        String subject=request.getParameter("subject");
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
        		"<center><a  href=\"http://localhost:8080/TestServlet/index.html\">back to homepage</a></center>\r\n" + 
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
		pst=con.prepareStatement("insert into teacher_detail values(?,?,?,?,?,?,?)");
		pst.setString(1,name);
		pst.setString(2,email);
		pst.setString(3,mobile_num);
		pst.setString(4,gender);
		pst.setString(5,college);
		pst.setString(6,course);
		pst.setString(7,subject);
		pst.executeUpdate();
		pst.close();
		pst2=con.prepareStatement("insert into teacherLogin values(?,?)");
		pst2.setString(1, email);
		pst2.setString(2, password);
		pst2.executeUpdate();
		pst2.close();
		con.close();
	}catch(Exception e)
		  {
		  e.printStackTrace();		  
	}
	}
}
