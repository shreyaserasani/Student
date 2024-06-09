//Import necessary Libraries
import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class RegisterServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  

//Retrieve the fields
String fname=request.getParameter("fname");  
String lname=request.getParameter("lname");  
String email=request.getParameter("email"); 
String gender=request.getParameter("gender"); 
String psw=request.getParameter("psw");

try{  
Class.forName("com.mysql.cj.jdbc.Driver");  
//Create Connection
Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/shreyas?characterEncoding=latin1&useConfigs=maxPerformance","root","shreyas");  
  
//Create Statement for inserting details to table
PreparedStatement ps=con.prepareStatement(  
"insert into student(FirstName,LastName,Email,Gender,Password) values(?,?,?,?,?)");  
  
ps.setString(1,fname);  
ps.setString(2,lname);  
ps.setString(3,email);
ps.setString(4,gender);
ps.setString(5,psw);
          
int i=ps.executeUpdate();  
if(i>0)  
out.print("You are successfully registered.... Please login to continue");  
request.getRequestDispatcher("index.html").include(request, response);
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
}  
}  