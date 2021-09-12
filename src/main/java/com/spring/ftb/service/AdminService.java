package com.spring.ftb.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ftb.bean.FlightBean;
import com.spring.ftb.bean.PassengerBean;
import com.spring.ftb.bean.RouteBean;
import com.spring.ftb.bean.ScheduleBean;
import com.spring.ftb.dao.AdminDao;

@Service
public class AdminService {
	@Autowired
	private AdminDao adao;

	public String addFlight(FlightBean fb) {
		return adao.addFlight(fb);
	}

	public ArrayList<FlightBean> viewAllFlight() {
		return adao.viewAllFlight();
	}

	public boolean modifyFlight(FlightBean fb) {
		return adao.modifyFlight(fb);
	}

	public int removeFlight(String flightID) {
		return adao.removeFlight(flightID);
	}

	public FlightBean viewFlightById(String flightID) {
		return adao.viewFlightById(flightID);
	}

	public String addSchedule(ScheduleBean sb) {
		return adao.addSchedule(sb);
	}

	public ArrayList<ScheduleBean> viewAllSchedule() {
		return adao.viewAllSchedule();
	}

	public boolean modifySchedule(ScheduleBean sb) {
		return adao.modifySchedule(sb);
	}

	public int removeSchedule(String scheduleID) {
		return adao.removeSchedule(scheduleID);
	}

	public ScheduleBean viewScheduleById(String scheduleID) {
		return adao.viewScheduleById(scheduleID);
	}

	public String addRoute(RouteBean rb) {
		return adao.addRoute(rb);
	}

	public ArrayList<RouteBean> viewAllRoute() {
		return adao.viewAllRoute();
	}

	public boolean modifyRoute(RouteBean rb) {
		return adao.modifyRoute(rb);
	}

	public int removeRoute(String routeID) {
		return adao.removeRoute(routeID);
	}

	public RouteBean viewRouteById(String routeID) {
		return adao.viewRouteById(routeID);
	}

	public ArrayList<PassengerBean> viewPassengerByFlight(String scheduleID) {
		return adao.viewPassengerByFlight(scheduleID);
	}
}
