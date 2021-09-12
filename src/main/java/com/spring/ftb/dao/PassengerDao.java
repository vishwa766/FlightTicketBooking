package com.spring.ftb.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.ftb.bean.PassengerBean;
import com.spring.ftb.bean.ReservationBean;
import com.spring.ftb.bean.ScheduleBean;

@Repository
public class PassengerDao {

	public static int i = 0;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, Date jdate) {
		ArrayList<ScheduleBean> al = new ArrayList<ScheduleBean>();
		jdbcTemplate.query(
				"select sc.scheduleid,sc.flightid,sc.routeid,sc.travelduration,sc.availabledays,sc.departuretime \r\n"
						+ "from frs_tbl_schedule sc \r\n"
						+ "inner join frs_tbl_reservation re on sc.scheduleid=re.scheduleid \r\n"
						+ "inner join frs_tbl_route ro on sc.routeid=ro.routeid \r\n" + "where ro.source='" + source
						+ "' and ro.destination='" + destination + "' and re.journeydate='" + jdate + "'",
				new RowMapper<ScheduleBean>() {

					@Override
					public ScheduleBean mapRow(ResultSet rs, int arg1) throws SQLException {
						ScheduleBean sb = new ScheduleBean();
						sb.setScheduleID(rs.getString(1));
						sb.setFlightID(rs.getString(2));
						sb.setRouteID(rs.getString(3));
						sb.setTravelDuration(rs.getString(4));
						sb.setAvailableDays(rs.getInt(5));
						sb.setDepartureTime(rs.getString(6));
						al.add(sb);
						return sb;
					}

				});
		return al;
	}

	public String reserveTicket(ReservationBean rb, PassengerBean pb) {
		jdbcTemplate.query(
				"select fare from frs_tbl_route ro\r\n" + "inner join frs_tbl_schedule sc on sc.routeid=ro.routeid \r\n"
						+ "where sc.scheduleid='" + rb.getScheduleID() + "'",
				new RowMapper<ReservationBean>() {

					@Override
					public ReservationBean mapRow(ResultSet rs, int arg1) throws SQLException {
						rb.setTotalFare(rs.getInt(1));
						return rb;
					}

				});
		if ((rb != null) && (pb != null)) {
			java.sql.Date bDate = new java.sql.Date((rb.getBookingdate()).getTime());
			java.sql.Date jDate = new java.sql.Date((rb.getJourneyDate()).getTime());
			jdbcTemplate.execute("insert into frs_tbl_reservation values(?,?,?,?,?,?,?,?,?)",
					new PreparedStatementCallback<Object>() {

						@Override
						public Object doInPreparedStatement(PreparedStatement ps)
								throws SQLException, DataAccessException {
							ps.setString(1, rb.getReservationID());
							ps.setString(2, rb.getUserID());
							ps.setString(3, rb.getScheduleID());
							ps.setString(4, rb.getReservationType());
							ps.setDate(5, bDate);
							ps.setDate(6, jDate);
							ps.setInt(7, rb.getNoOfSeats());
							ps.setInt(8, rb.getTotalFare());
							ps.setInt(9, rb.getBookingStatus());
							return ps.executeUpdate();

						}
					});
			jdbcTemplate.execute("insert into frs_tbl_passenger values(?,?,?,?,?)",
					new PreparedStatementCallback<Object>() {

						@Override
						public Object doInPreparedStatement(PreparedStatement pt)
								throws SQLException, DataAccessException {
							pt.setString(1, pb.getReservationID());
							pt.setString(2, pb.getName());
							pt.setString(3, pb.getGender());
							pt.setInt(4, pb.getAge());
							pt.setInt(5, pb.getSeatNo());
							return pt.executeUpdate();

						}
					});
			return "SUCCESS";
		} else {
			return "ERROR";
		}

	}

	public boolean cancelTicket(String reservationID) {
		jdbcTemplate.execute("delete from frs_tbl_passenger where Reservationid=?",
				new PreparedStatementCallback<Object>() {

					@Override
					public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ps.setString(1, reservationID);
						return i = ps.executeUpdate();

					}
				});
		jdbcTemplate.execute("delete from frs_tbl_reservation where Reservationid=?",
				new PreparedStatementCallback<Object>() {

					@Override
					public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ps.setString(1, reservationID);
						return i = ps.executeUpdate();

					}
				});

		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Map<ReservationBean, PassengerBean> viewTicket(String reservationID) {
		ReservationBean rb = new ReservationBean();
		jdbcTemplate.query("select * from frs_tbl_reservation where Reservationid='" + reservationID + "'",
				new RowMapper<ReservationBean>() {

					@Override
					public ReservationBean mapRow(ResultSet rs, int arg1) throws SQLException {
						rb.setReservationID(rs.getString(1));
						rb.setUserID(rs.getString(2));
						rb.setScheduleID(rs.getString(3));
						rb.setReservationType(rs.getString(4));
						rb.setBookingdate(rs.getDate(5));
						rb.setJourneyDate(rs.getDate(6));
						rb.setNoOfSeats(rs.getInt(7));
						rb.setTotalFare(rs.getInt(8));
						rb.setBookingStatus(rs.getInt(9));
						return rb;
					}

				});
		PassengerBean pb = new PassengerBean();
		jdbcTemplate.query("select * from frs_tbl_passenger where Reservationid='" + reservationID + "'",
				new RowMapper<PassengerBean>() {

					@Override
					public PassengerBean mapRow(ResultSet re, int arg1) throws SQLException {
						pb.setReservationID(re.getString(1));
						pb.setName(re.getString(2));
						pb.setGender(re.getString(3));
						pb.setAge(re.getInt(4));
						pb.setSeatNo(re.getInt(5));
						return pb;
					}

				});
		Map<ReservationBean, PassengerBean> map = new HashMap<ReservationBean, PassengerBean>();
		map.put(rb, pb);
		return map;
	}
}
