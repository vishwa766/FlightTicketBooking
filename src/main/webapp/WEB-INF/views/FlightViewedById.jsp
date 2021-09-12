<%@page import="com.spring.ftb.bean.FlightBean"%>
<html>
<body>
<%FlightBean fb=(FlightBean)request.getAttribute("flight");
%>
<table border="4">
<tr><th>FlightID</th><th>FlightName</th><th>Seating Capacity</th><th>Reservation Capacity</th></tr>
<tr><td><%=fb.getFlightID() %></td><td><%=fb.getFlightName() %></td><td><%=fb.getSeatingCapacity() %></td><td><%=fb.getReservationCapacity() %></td></tr>
</table>
</body>
</html>