<%@page import="com.spring.ftb.bean.FlightBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<body>
<%ArrayList<FlightBean> st=(ArrayList<FlightBean>)request.getAttribute("flights");
%>
<table border="4">
<tr><th>FlightID</th><th>FlightName</th><th>Seating Capacity</th><th>Reservation Capacity</th></tr>
<%for(FlightBean fb:st){
	%>
	<tr><td><%=fb.getFlightID() %></td><td><%=fb.getFlightName() %></td><td><%=fb.getSeatingCapacity() %></td><td><%=fb.getReservationCapacity() %></td></tr>
<%
}
%>
</table>
</body>
</html>