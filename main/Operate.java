import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Operate extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    						throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String acode = request.getParameter("acode");
        String rnum = request.getParameter("rnum");

        String statementString =
		"INSERT INTO Operate(acode, rnum) " +
        "VALUES( '" + acode + "','" + rnum + "')";
        System.out.println(statementString);
        Connection conn = ConnectionManager.getInstance().getConnection();
        
		try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(statementString);
            stmt.close();
            out.println("Insertion Successful!");
        }
        catch(SQLException e) { out.println(e); }
        ConnectionManager.getInstance().returnConnection(conn);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {  return "Operate"; }
}
