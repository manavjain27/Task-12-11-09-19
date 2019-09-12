<%@page import="java.sql.*" %>

<%
String id = (String) session.getAttribute("user");

			if (id == null)
			{
				response.sendRedirect("index.jsp");
			}
%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Book Search</h2>
<hr>

<form method="post" action="SearchBook">
	Book List :<select name="subject">
<%
ServletContext context=getServletContext();
String driver=context.getInitParameter("driver");
String url=context.getInitParameter("url");
String dbusername=context.getInitParameter("dbusername");
String dbpassword=context.getInitParameter("dbpassword");

Class.forName(driver);
Connection con=DriverManager.getConnection(url,dbusername,dbpassword);
String sql="SELECT distinct subject from books";
PreparedStatement ps=con.prepareStatement(sql);
ResultSet rs=ps.executeQuery();
while(rs.next())
{
	String s=rs.getString(1);
%>

<option><%=s %></option>

<%
}
con.close();
%>
</select>
		
			

			<input type="submit" value="Search Book"/>		
			<hr>
			<a href="buyerpage.jsp">Home</a>
</form>

</body>
</html>