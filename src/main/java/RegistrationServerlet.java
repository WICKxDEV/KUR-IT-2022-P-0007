import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * Servlet implementation class RegistrationServerlet
 */
@WebServlet("/register")
public class RegistrationServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("name");
		String upwd = request.getParameter("pass");
		String uemail = request.getParameter("email");
		String umobile = request.getParameter("contact");
		jakarta.servlet.RequestDispatcher dispatcher = null;
		Connection con = null;
	
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3307/sign?useSSL=false", "root", "");
	        PreparedStatement pst = con.prepareStatement("INSERT INTO users(uname, upwd, uemail, umobile) VALUES (?, ?, ?, ?)");
	        pst.setString(1, uname);
	        pst.setString(2, upwd);
	        pst.setString(3, uemail);
	        pst.setString(4, umobile);
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if(rowCount > 0) {
				request.setAttribute("status","success");
			}else {
				request.setAttribute("status","failed");
			}
			
			dispatcher.forward(request, response);
			
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}