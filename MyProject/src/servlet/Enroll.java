package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Bean;
import db.DBConnection;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/enroll")
public class Enroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection conn=null;
    Statement st=null;
    ResultSet rs=null;
    
    public Enroll() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String userId=request.getParameter("userId");
 		String userPs=request.getParameter("userPs");
 		String useremail=request.getParameter("useremail");
		Bean b=new Bean(userId, userPs, useremail);
		String todo=b.enrollDBInsert();
		if(todo.equals("建立成功")) {
			request.setAttribute("label", userId);
			RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);

		}else {
			RequestDispatcher rd=request.getRequestDispatcher("enroll.jsp");
			rd.forward(request, response);
		}
		
	}

}
