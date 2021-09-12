<%@page import="com.spring.ftb.bean.RouteBean"%>
<html>
<body>
<%RouteBean rb=(RouteBean)request.getAttribute("route");
%>
<table border="4">
<tr><th>RouteID</th><th>Source</th><th>Destination</th><th>Distance</th><th>Fare</th></tr>
<tr><td><%=rb.getRouteID() %></td><td><%=rb.getSource() %></td><td><%=rb.getDestination() %></td><td><%=rb.getDistance() %></td><td><%=rb.getFare() %></td></tr>
</table>
</body>
</html>