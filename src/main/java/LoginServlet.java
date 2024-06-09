//Import necessary Libraries
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;  
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        //Retrieve username and Password
        String email=request.getParameter("email");
        String password=request.getParameter("psw");
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            //Create Connection
            Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/shreyas?characterEncoding=latin1&useConfigs=maxPerformance","root","shreyas");    
            //Create Statement for database query
            PreparedStatement ps = con.prepareStatement("select * from student where Email=? and Password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            //execute query
            ResultSet rs =ps.executeQuery();
            //check if result exists
            if(rs.next()) {
            	out.print("<h4>Login Successful</h4>");
            }
            else{
                //if not exists, prompt user.
                out.print("<h4>Sorry, username or password is incorrect!</h4>");
                request.getRequestDispatcher("Login.html").include(request, response);
            }
            }catch (Exception e2) {System.out.println(e2);}
        
        out.close();
    }

}