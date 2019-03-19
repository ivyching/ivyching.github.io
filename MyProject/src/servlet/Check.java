package servlet;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class Check
 */
@WebServlet("/check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection cn;
	private Statement st;
	private ResultSet rs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Check() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String[] todo=b.getInformation(userId, useremail);
		System.out.println(todo[1]);
					
						if(todo[0].equals("查詢成功")) {
						request.setAttribute("label", userId);
						System.out.println("password"+todo[1]);
						request.setAttribute("password",todo[1] );
						RequestDispatcher rd=request.getRequestDispatcher("welcome4.jsp");
						rd.forward(request, response);
						}else {
							RequestDispatcher rd=request.getRequestDispatcher("check.jsp");
							rd.forward(request, response);
						}
					
		 
	}

}
