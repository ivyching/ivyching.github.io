package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;

/**
 * Servlet implementation class Reuser
 */
@WebServlet("/reuser")
public class Reuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn=null;
    Statement st=null;
    ResultSet rs=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reuser() {
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
		//ajax檢查帳號是否重新註冊
		
		String path=request.getContextPath();

		String enuserId=request.getParameter("enuserId");
		int idcount=0;
		try {
			conn=DBConnection.getDB().ConnectionDB();
			st=conn.createStatement();
			String sql="select count(account) from user where account='" +enuserId+"' ";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				idcount=rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.getDB().close(rs, st, conn);
			response.getWriter().println(idcount);
		}
	}

}
