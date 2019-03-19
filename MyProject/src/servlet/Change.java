package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Bean;

/**
 * Servlet implementation class Change
 */
@WebServlet("/change")
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Change() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
 		String userPs01=request.getParameter("userPs01");
 		String useremail=request.getParameter("useremail");
		Bean b=new Bean(userPs, userPs01, useremail);
		String todo=b.changePassword(userId,userPs, userPs01, useremail);
		if(todo.equals("修改密碼成功")) {
			request.setAttribute("label", userId);
			RequestDispatcher rd=request.getRequestDispatcher("welcome3.jsp");
			rd.forward(request, response);

		}else {
			RequestDispatcher rd=request.getRequestDispatcher("change.jsp");
			rd.forward(request, response);
		}
	}

}
