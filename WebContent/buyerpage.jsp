<%
String id = (String) session.getAttribute("user");
String username=(String)session.getAttribute("uName");

			if (id == null)
			{
				response.sendRedirect("index.jsp");
			}
%>

<html>
<body>
	<h3>DashBoard-For-Buyer</h3>
	<hr>
	<h2>Welcome : <%=username%></h2>
	<hr>
	<pre>
		<a href="SubjectPageServlet">Explore-Store</a>
		<a href="ProfileUpdate.jsp">Update Profile</a>
		<a href="BookSearch.jsp">Search-Book</a>
		<a href="DisplayCart">View-Cart</a>
		<a href="">Trace-Order</a>
		<a href="Logout">Logout</a>
	</pre>		
	<hr>
</body>
</html>