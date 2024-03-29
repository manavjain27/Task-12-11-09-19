package com.wp.Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SaveBook")
public class SaveBook extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Connection con; PreparedStatement ps;

    @Override
    public void init(){
        String sql="insert into books values(?,?,?,?,?)";
        
        ServletContext context=getServletContext();
		String driver=context.getInitParameter("driver");
		String url=context.getInitParameter("url");
		String dbusername=context.getInitParameter("dbusername");
		String dbpassword=context.getInitParameter("dbpassword");
        try{
        	Class.forName(driver);
			Connection con=DriverManager.getConnection(url,dbusername,dbpassword);
        ps=con.prepareStatement(sql);            
        }catch(Exception e){}
    }
    
    @Override
    public void destroy(){
        try{
            con.close();
        }catch(Exception e){}
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        //reads the data
        String bcode=request.getParameter("bcode");
        String btitle=request.getParameter("btitle");
        String author=request.getParameter("author");
        String subject=request.getParameter("subject");
        String price=request.getParameter("price");
        
        //process the data
        try{
        ps.setInt(1, Integer.parseInt(bcode) );
        ps.setString(2, btitle);
        ps.setString(3, author);
        ps.setString(4, subject);
        ps.setInt(5, Integer.parseInt(price));

        ps.executeUpdate();
        }catch(Exception e){
            out.println(e);
        }
        //provides the response
        
        out.println("<h3>Books Details Stored<h3>");
        out.println("<h4><a href=BookEntry.jsp>Add-More</a></h4>");
        out.println("<h4><a href=adminpage.jsp>AdminPage</a></h4>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}