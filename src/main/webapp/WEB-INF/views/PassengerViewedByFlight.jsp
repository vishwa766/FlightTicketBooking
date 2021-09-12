<%@page import="com.spring.ftb.bean.PassengerBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<body>
<%ArrayList<PassengerBean> st=(ArrayList<PassengerBean>)request.getAttribute("passengers");
%>
<table border="4">
<tr><th>ReservationID</th><th>Passenger Name</th><th>Gender</th><th>Age</th><th>Seat No</th></tr>
<%for(PassengerBean pb:st){
	%>
<tr><td><%=pb.getReservationID() %></td><td><%=pb.getName() %></td><td><%=pb.getGender() %></td><td><%=pb.getAge() %></td><td><%=pb.getSeatNo() %></td></tr>
<%
}
%>
</table>
</body>
</html>