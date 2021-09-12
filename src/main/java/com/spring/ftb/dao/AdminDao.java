package com.spring.ftb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.ftb.bean.FlightBean;
import com.spring.ftb.bean.PassengerBean;
import com.spring.ftb.bean.RouteBean;
import com.spring.ftb.bean.ScheduleBean;

@Repository
public class AdminDao {
	public static int i = 0;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String addFlight(FlightBean fb) {
		if (fb != null) {
			jdbcTemplate.execute("insert into frs_tbl_flight values(?,?,?,?)", new PreparedStatementCallback<Object>() {

				@Override
				public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, fb.getFlightID());
					ps.setString(2, fb.getFlightName());
					ps.setInt(3, fb.getSeatingCapacity());
					ps.setInt(4, fb.getReservationCapacity());
					return ps.executeUpdate();

				}
			});
			return "SUCCESS";
		} else if (fb == null) {
			return "FAIL";
		} else {
			return "ERROR";
		}
	}

	public ArrayList<FlightBean> viewAllFlight() {
		ArrayList<FlightBean> al = new ArrayList<FlightBean>();
		jdbcTemplate.query("select * from frs_tbl_flight", new RowMapper<FlightBean>() {

			@Override
			public FlightBean mapRow(ResultSet rs, int arg1) throws SQLException {
				FlightBean fb = new FlightBean();
				fb.setFlightID(rs.getString(1));
				fb.setFlightName(rs.getString(2));
				fb.setSeatingCapacity(rs.getInt(3));
				fb.setReservationCapacity(rs.getInt(4));
				al.add(fb);
				return fb;
			}

		});
		return al;
	}

	public boolean modifyFlight(FlightBean fb) {
		jdbcTemplate.execute(
				"update frs_tbl_flight set FlightName=?,Seatingcapacity=?,Reservationcapacity=? where FlightId=?",
				new PreparedStatementCallback<Object>() {
					@Override
					public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ps.setString(1, fb.getFlightName());
						ps.setInt(2, fb.getSeatingCapacity());
						ps.setInt(3, fb.getReservationCapacity());
						ps.setString(4, fb.getFlightID());
						return i = ps.executeUpdate();

					}
				});
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

	public int removeFlight(String flightID) {
		jdbcTemplate.execute("delete from frs_tbl_flight where FlightId=?", new PreparedStatementCallback<Object>() {

			@Override
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, flightID);
				return i = ps.executeUpdate();

			}
		});
		return i;
	}

	public FlightBean viewFlightById(String flightID) {
		FlightBean fb = new FlightBean();
		jdbcTemplate.query("select * from frs_tbl_flight where FlightId='" + flightID + "'",
				new RowMapper<FlightBean>() {

					@Override
					public FlightBean mapRow(ResultSet rs, int arg1) throws SQLException {
						fb.setFlightID(rs.getString(1));
						fb.setFlightName(rs.getString(2));
						fb.setSeatingCapacity(rs.getInt(3));
						fb.setReservationCapacity(rs.getInt(4));
						return fb;
					}

				});
		return fb;

	}

	public String addSchedule(ScheduleBean sb) {
		if (sb != null) {
			jdbcTemplate.execute("insert into frs_tbl_schedule values(?,?,?,?,?,?)",
					new PreparedStatementCallback<Object>() {

						@Override
						public Object doInPreparedStatement(PreparedStatement ps)
								throws SQLException, DataAccessException {
							ps.setString(1, sb.getScheduleID());
							ps.setString(2, sb.getFlightID());
							ps.setString(3, sb.getRouteID());
							ps.setString(4, sb.getTravelDuration());
							ps.setInt(5, sb.getAvailableDays());
							ps.setString(6, sb.getDepartureTime());
							return ps.executeUpdate();

						}
					});
			return "SUCCESS";
		} else if (sb == null) {
			return "FAIL";
		} else {
			return "ERROR";
		}
	}

	public ArrayList<ScheduleBean> viewAllSchedule() {
		ArrayList<ScheduleBean> al = new ArrayList<ScheduleBean>();
		jdbcTemplate.query("select * from frs_tbl_schedule", new RowMapper<ScheduleBean>() {

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

	public boolean modifySchedule(ScheduleBean sb) {
		jdbcTemplate.execute(
				"update frs_tbl_schedule set Flightid=?,Routeid=?,TravelDuration=?,AvailableDays=?,DepartureTime=? where Scheduleid=?",
				new PreparedStatementCallback<Object>() {
					@Override
					public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ps.setString(1, sb.getFlightID());
						ps.setString(2, sb.getRouteID());
						ps.setString(3, sb.getTravelDuration());
						ps.setInt(4, sb.getAvailableDays());
						ps.setString(5, sb.getDepartureTime());
						ps.setString(6, sb.getScheduleID());
						return i = ps.executeUpdate();

					}
				});
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

	public int removeSchedule(String scheduleID) {
		jdbcTemplate.execute("delete from frs_tbl_schedule where scheduleid=?",
				new PreparedStatementCallback<Object>() {

					@Override
					public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ps.setString(1, scheduleID);
						return i = ps.executeUpdate();

					}
				});
		return i;
	}

	public ScheduleBean viewScheduleById(String scheduleID) {
		ScheduleBean sb = new ScheduleBean();
		jdbcTemplate.query("select * from frs_tbl_schedule where Scheduleid='" + scheduleID + "'",
				new RowMapper<ScheduleBean>() {

					@Override
					public ScheduleBean mapRow(ResultSet rs, int arg1) throws SQLException {
						sb.setScheduleID(rs.getString(1));
						sb.setFlightID(rs.getString(2));
						sb.setRouteID(rs.getString(3));
						sb.setTravelDuration(rs.getString(4));
						sb.setAvailableDays(rs.getInt(5));
						sb.setDepartureTime(rs.getString(6));
						return sb;
					}

				});
		return sb;

	}

	public String addRoute(RouteBean rb) {
		if (rb != null) {
			jdbcTemplate.execute("insert into frs_tbl_route values(?,?,?,?,?)",
					new PreparedStatementCallback<Object>() {

						@Override
						public Object doInPreparedStatement(PreparedStatement ps)
								throws SQLException, DataAccessException {
							ps.setString(1, rb.getRouteID());
							ps.setString(2, rb.getSource());
							ps.setString(3, rb.getDestination());
							ps.setInt(4, rb.getDistance());
							ps.setInt(5, rb.getFare());
							return ps.executeUpdate();

						}
					});
			return "SUCCESS";
		} else if (rb == null) {
			return "FAIL";
		} else {
			return "ERROR";
		}
	}

	public ArrayList<RouteBean> viewAllRoute() {
		ArrayList<RouteBean> al = new ArrayList<RouteBean>();
		jdbcTemplate.query("select * from frs_tbl_route", new RowMapper<RouteBean>() {

			@Override
			public RouteBean mapRow(ResultSet rs, int arg1) throws SQLException {
				RouteBean rb = new RouteBean();
				rb.setRouteID(rs.getString(1));
				rb.setSource(rs.getString(2));
				rb.setDestination(rs.getString(3));
				rb.setDistance(rs.getInt(4));
				rb.setFare(rs.getInt(5));
				al.add(rb);
				return rb;
			}

		});
		return al;
	}

	public boolean modifyRoute(RouteBean rb) {
		jdbcTemplate.execute("update frs_tbl_route set Source=?,Destination=?,Distance=?,Fare=? where Routeid=?",
				new PreparedStatementCallback<Object>() {
					@Override
					public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ps.setString(1, rb.getSource());
						ps.setString(2, rb.getDestination());
						ps.setInt(3, rb.getDistance());
						ps.setInt(4, rb.getFare());
						ps.setString(5, rb.getRouteID());
						return i = ps.executeUpdate();

					}
				});
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

	public int removeRoute(String routeID) {
		jdbcTemplate.execute("delete from frs_tbl_route where Routeid=?", new PreparedStatementCallback<Object>() {

			@Override
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, routeID);
				return i = ps.executeUpdate();

			}
		});
		return i;
	}

	public RouteBean viewRouteById(String routeID) {
		RouteBean rb = new RouteBean();
		jdbcTemplate.query("select * from frs_tbl_route where Routeid='" + routeID + "'", new RowMapper<RouteBean>() {

			@Override
			public RouteBean mapRow(ResultSet rs, int arg1) throws SQLException {
				rb.setRouteID(rs.getString(1));
				rb.setSource(rs.getString(2));
				rb.setDestination(rs.getString(3));
				rb.setDistance(rs.getInt(4));
				rb.setFare(rs.getInt(5));
				return rb;
			}

		});
		return rb;

	}

	public ArrayList<PassengerBean> viewPassengerByFlight(String scheduleID) {
		ArrayList<PassengerBean> al = new ArrayList<PassengerBean>();
		PassengerBean pb = new PassengerBean();
		jdbcTemplate.query("select pa.reservationid,pa.name,pa.gender,pa.age,pa.seatno from frs_tbl_passenger pa\r\n"
				+ "inner join frs_tbl_reservation re on pa.reservationid=re.reservationid \r\n"
				+ "inner join frs_tbl_schedule sc on sc.scheduleid=re.scheduleid \r\n"
				+ "inner join frs_tbl_flight fl on sc.flightid=fl.flightid\r\n" + "where sc.scheduleid='" + scheduleID
				+ "' order by fl.flightid", new RowMapper<PassengerBean>() {

					@Override
					public PassengerBean mapRow(ResultSet re, int arg1) throws SQLException {
						pb.setReservationID(re.getString(1));
						pb.setName(re.getString(2));
						pb.setGender(re.getString(3));
						pb.setAge(re.getInt(4));
						pb.setSeatNo(re.getInt(5));
						al.add(pb);
						return pb;
					}

				});
		return al;
	}
}