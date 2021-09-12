package com.spring.ftb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ftb.bean.PassengerBean;
import com.spring.ftb.bean.ReservationBean;
import com.spring.ftb.bean.ScheduleBean;
import com.spring.ftb.service.PassengerService;

@RequestMapping("passenger")
@RestController
public class PassengerController {
	@Autowired
	private PassengerService paserv;

	@RequestMapping("/passengerlogin")
	public ModelAndView meth2() {
		return new ModelAndView("PassengerLogin");
	}

	@RequestMapping("/passengerloginvalid")
	public ModelAndView meth3(@RequestParam("Userid") String usr, @RequestParam("password") String pss) {
		if (usr.equals("US01") && pss.equals("US01")) {
			return new ModelAndView("PassengerHome");
		} else {
			return new ModelAndView("LoginInvalid");
		}

	}

	@RequestMapping("/viewschedulebyroute")
	public ModelAndView meth4() {
		return new ModelAndView("ViewScheduleByRoute");
	}

	@RequestMapping(value = "/scheduleviewedbyroute", method = RequestMethod.POST)
	public ModelAndView meth5(@RequestParam("source") String source, @RequestParam("destination") String destination,
			@RequestParam("journeyDate") String jdate, Model m) throws ParseException {
		System.out.println(jdate);
		Date date1 = new Date();
		date1 = new SimpleDateFormat("yyyy-MM-dd").parse(jdate);
		ArrayList<ScheduleBean> sch = paserv.viewScheduleByRoute(source, destination, date1);
		if (sch.size() > 0) {
			m.addAttribute("schedules", sch);
			return new ModelAndView("PassengerSchedule");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/reserveticket")
	public ModelAndView meth6() {
		return new ModelAndView("ReserveTicket");
	}

	@RequestMapping(value = "/ticketreserved", method = RequestMethod.POST)
	public ModelAndView meth7(@RequestParam("reservationID") String reservationID,
			@RequestParam("userID") String userID, @RequestParam("scheduleID") String scheduleID,
			@RequestParam("reservationType") String reservationType, @RequestParam("bookingdate") String bookingdate,
			@RequestParam("journeyDate") String journeyDate, @RequestParam("noOfSeats") int noOfSeats,
			@RequestParam("name") String name, @RequestParam("gender") String gender, @RequestParam("age") int age,
			@RequestParam("seatNo") int seatNo) throws ParseException {
		ReservationBean rb = new ReservationBean();
		rb.setReservationID(reservationID);
		rb.setUserID(userID);
		rb.setScheduleID(scheduleID);
		rb.setReservationType(reservationType);
		Date date1 = new Date();
		date1 = new SimpleDateFormat("yyyy-MM-dd").parse(bookingdate);
		rb.setBookingdate(date1);
		Date date2 = new Date();
		date1 = new SimpleDateFormat("yyyy-MM-dd").parse(journeyDate);
		rb.setJourneyDate(date2);
		rb.setNoOfSeats(noOfSeats);
		rb.setTotalFare(0);
		rb.setBookingStatus(1);
		PassengerBean pb = new PassengerBean();
		pb.setReservationID(reservationID);
		pb.setName(name);
		pb.setGender(gender);
		pb.setAge(age);
		pb.setSeatNo(seatNo);
		if (paserv.reserveTicket(rb, pb) == "SUCCESS") {
			return new ModelAndView("TicketReserved");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/cancelticket")
	public ModelAndView meth8() {
		return new ModelAndView("CancelTicket");
	}

	@RequestMapping(value = "/ticketcanceled", method = RequestMethod.POST)
	public ModelAndView meth9(@RequestParam("reservationID") String reservationID) {
		if (paserv.cancelTicket(reservationID)) {
			return new ModelAndView("TicketCanceled");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/viewticket")
	public ModelAndView meth10() {
		return new ModelAndView("ViewTicket");
	}

	@RequestMapping(value = "/ticketviewed", method = RequestMethod.POST)
	public ModelAndView meth11(@RequestParam("reservationID") String reservationID, Model m) {
		m.addAttribute("ticket", paserv.viewTicket(reservationID));
		return new ModelAndView("TicketViewed");
	}
}
