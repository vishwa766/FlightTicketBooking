package com.spring.ftb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ftb.bean.PassengerBean;
import com.spring.ftb.bean.ReservationBean;
import com.spring.ftb.bean.ScheduleBean;
import com.spring.ftb.dao.PassengerDao;

@Service
public class PassengerService {
	@Autowired
	private PassengerDao pdao;

	public ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, Date jdate) {
		System.out.println(jdate);
		java.sql.Date sDate = new java.sql.Date(jdate.getTime());
		return pdao.viewScheduleByRoute(source, destination, sDate);
	}

	public String reserveTicket(ReservationBean rb, PassengerBean pb) {
		return pdao.reserveTicket(rb, pb);
	}

	public boolean cancelTicket(String reservationID) {
		return pdao.cancelTicket(reservationID);
	}

	public Map<ReservationBean, PassengerBean> viewTicket(String reservationID) {
		return pdao.viewTicket(reservationID);
	}
}