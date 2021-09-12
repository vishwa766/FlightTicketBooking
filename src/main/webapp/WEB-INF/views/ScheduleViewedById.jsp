<%@page import="com.spring.ftb.bean.ScheduleBean"%>
<html>
<body>
<%ScheduleBean sb=(ScheduleBean)request.getAttribute("schedule");
%>
<table border="4">
<tr><th>ScheduleID</th><th>FlightID</th><th>RouteID</th><th>Travel Duration</th><th>Available Days</th><th>Departure Time</th></tr>
<tr><td><%=sb.getScheduleID() %></td><td><%=sb.getFlightID() %></td><td><%=sb.getRouteID() %></td><td><%=sb.getTravelDuration() %></td><td><%=sb.getAvailableDays() %></td><td><%=sb.getDepartureTime() %></td></tr>
</table>
</body>
</html>