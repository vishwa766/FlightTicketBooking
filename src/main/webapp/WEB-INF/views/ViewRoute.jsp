<%@page import="com.spring.ftb.bean.RouteBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<body>
<%ArrayList<RouteBean> st=(ArrayList<RouteBean>)request.getAttribute("routes");
%>
<table border="4">
<tr><th>RouteID</th><th>Source</th><th>Destination</th><th>Distance</th><th>Fare</th></tr>
<%for(RouteBean rb:st){
	%>
	<tr><td><%=rb.getRouteID() %></td><td><%=rb.getSource() %></td><td><%=rb.getDestination() %></td><td><%=rb.getDistance() %></td><td><%=rb.getFare() %></td></tr>
<%
}
%>
</table>
</body>
</html>