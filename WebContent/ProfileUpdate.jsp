<%@page import= "java.sql.*" %>
<%
String id = (String) session.getAttribute("user");
String username=(String)session.getAttribute("uName");
			if (id == null)
			{
				response.sendRedirect("index.jsp");
			}
%>

<%
ServletContext context=getServletContext();
String driver=context.getInitParameter("driver");
String url=context.getInitParameter("url");
String dbusername=context.getInitParameter("dbusername");
String dbpassword=context.getInitParameter("dbpassword");

Class.forName(driver);
Connection con=DriverManager.getConnection(url,dbusername,dbpassword);
String sql="SELECT * FROM users where userid=?";
PreparedStatement ps=con.prepareStatement(sql);
id = (String) session.getAttribute("user");
ps.setString(1,id);

ResultSet rs=ps.executeQuery();
while(rs.next())
{
%>



<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Online Book Store</h3>
	<h4>Profile Update-Form</h4>
	<hr>
	<form action="ProfileUpdate">
	<pre>
		Username	<input type="text" name="username" value="<%=rs.getString(3) %>"/>
		Password	<input type="password" name="password" value="<%=rs.getString(2) %>"/>
		Address		<input type="text" name="address" value="<%=rs.getString(4) %>"/>
		Mobile		<input type="text" name="mobile" value="<%=rs.getString(5) %>"/>
		Email-Id	<input type="text" name="email" value="<%=rs.getString(6) %>"/>
				<input type="submit" value="Update Profile"/>
<%
}
%>
	</pre>
	</form>
	<hr>
	<a href="buyerpage.jsp">Home</a>

</body>
</html>