<%@page import="com.spring.ftb.bean.ReservationBean"%>
<%@page import="com.spring.ftb.bean.PassengerBean"%>
<%@page import="java.util.*"%>

<html>
<body>
<%Map<ReservationBean,PassengerBean> map=(HashMap<ReservationBean,PassengerBean>)request.getAttribute("ticket");
ReservationBean rb;
PassengerBean pb;
for(Map.Entry m:map.entrySet()){  
   rb=(ReservationBean)m.getKey();
   pb=(PassengerBean)m.getValue(); 
 %>
<table border="4">
<tr><th>ReservationID</th><th>UserID</th><th>ScheduleID</th><th>ReservationType</th><th>Booking Date</th><th>Journey Date</th><th>No of Seats</th><th>Total Fare</th><th>Booking Status</th></tr>
<tr><td><%=rb.getReservationID() %></td><td><%=rb.getUserID() %></td><td><%=rb.getScheduleID() %></td><td><%=rb.getReservationType() %></td><td><%=rb.getBookingdate() %></td><td><%=rb.getJourneyDate() %></td><td><%=rb.getNoOfSeats() %></td><td><%=rb.getTotalFare() %></td><td><%=rb.getBookingStatus() %></td></tr>
</table>
<table border="4">
<tr><th>ReservationID</th><th>Passenger Name</th><th>Gender</th><th>Age</th><th>Seat No</th></tr>
<tr><td><%=pb.getReservationID() %></td><td><%=pb.getName() %></td><td><%=pb.getGender() %></td><td><%=pb.getAge() %></td><td><%=pb.getSeatNo() %></td></tr>
</table>
 <%
  }  
%>

<button onclick="window.print()">Print Ticket</button>
</body>
</html>