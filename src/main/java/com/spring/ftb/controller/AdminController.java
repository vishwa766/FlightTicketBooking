package com.spring.ftb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ftb.bean.FlightBean;
import com.spring.ftb.bean.RouteBean;
import com.spring.ftb.bean.ScheduleBean;
import com.spring.ftb.service.AdminService;

@RequestMapping("admin")
@RestController
public class AdminController {
	@Autowired
	private AdminService adserv;

	@RequestMapping("/home")
	public ModelAndView meth1() {
		return new ModelAndView("Home");
	}

	@RequestMapping("/adminlogin")
	public ModelAndView meth2() {
		return new ModelAndView("AdminLogin");
	}

	@RequestMapping("/adminloginvalid")
	public ModelAndView meth3(@RequestParam("Userid") String usr, @RequestParam("password") String pss) {
		if (usr.equals("vishwa01") && pss.equals("vishwa01")) {
			return new ModelAndView("AdminHome");
		} else {
			return new ModelAndView("LoginInvalid");
		}

	}

	@RequestMapping("/addflight")
	public ModelAndView meth4() {
		return new ModelAndView("AddFlight");
	}

	@RequestMapping(value = "/flightadded", method = RequestMethod.POST)
	public ModelAndView meth5(@ModelAttribute("fb") FlightBean fb) {
		if (adserv.addFlight(fb) == "SUCCESS") {
			return new ModelAndView("FlightAdded");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/selectallflight")
	public ModelAndView meth6(Model m) {
		m.addAttribute("flights", adserv.viewAllFlight());
		return new ModelAndView("ViewFlight");
	}

	@RequestMapping("/modifyflight")
	public ModelAndView meth7() {
		return new ModelAndView("ModifyFlight");
	}

	@RequestMapping(value = "/flightmodified", method = RequestMethod.POST)
	public ModelAndView meth8(@ModelAttribute("fb") FlightBean fb) {
		if (adserv.modifyFlight(fb) == true) {
			return new ModelAndView("FlightModified");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/removeflight")
	public ModelAndView meth9() {
		return new ModelAndView("RemoveFlight");
	}

	@RequestMapping(value = "/flightremoved", method = RequestMethod.POST)
	public ModelAndView meth10(@RequestParam("flightID") String flightid) {
		if (adserv.removeFlight(flightid) == 1) {
			return new ModelAndView("FlightRemoved");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/viewflightbyid")
	public ModelAndView meth11() {
		return new ModelAndView("ViewFlightById");
	}

	@RequestMapping(value = "/flightviewedbyid", method = RequestMethod.POST)
	public ModelAndView meth12(@RequestParam("flightID") String flightid, Model m) {
		m.addAttribute("flight", adserv.viewFlightById(flightid));
		return new ModelAndView("FlightViewedById");
	}

//-------------------Schedule------------------------------------------------------	

	@RequestMapping("/addschedule")
	public ModelAndView meth13() {
		return new ModelAndView("AddSchedule");
	}

	@RequestMapping(value = "/scheduleadded", method = RequestMethod.POST)
	public ModelAndView meth14(@ModelAttribute("sb") ScheduleBean sb) {
		if (adserv.addSchedule(sb) == "SUCCESS") {
			return new ModelAndView("ScheduleAdded");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/selectallschedule")
	public ModelAndView meth15(Model m) {
		m.addAttribute("schedules", adserv.viewAllSchedule());
		return new ModelAndView("ViewSchedule");
	}

	@RequestMapping("/modifyschedule")
	public ModelAndView meth16() {
		return new ModelAndView("ModifySchedule");
	}

	@RequestMapping(value = "/schedulemodified", method = RequestMethod.POST)
	public ModelAndView meth17(@ModelAttribute("sb") ScheduleBean sb) {
		if (adserv.modifySchedule(sb) == true) {
			return new ModelAndView("ScheduleModified");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/removeschedule")
	public ModelAndView meth18() {
		return new ModelAndView("RemoveSchedule");
	}

	@RequestMapping(value = "/scheduleremoved", method = RequestMethod.POST)
	public ModelAndView meth19(@RequestParam("scheduleID") String scheduleid) {
		if (adserv.removeSchedule(scheduleid) == 1) {
			return new ModelAndView("ScheduleRemoved");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/viewschedulebyid")
	public ModelAndView meth20() {
		return new ModelAndView("ViewScheduleById");
	}

	@RequestMapping(value = "/scheduleviewedbyid", method = RequestMethod.POST)
	public ModelAndView meth21(@RequestParam("scheduleID") String scheduleid, Model m) {
		m.addAttribute("schedule", adserv.viewScheduleById(scheduleid));
		return new ModelAndView("ScheduleViewedById");
	}

	// --------------------------ROUTE------------------------------------------

	@RequestMapping("/addroute")
	public ModelAndView meth22() {
		return new ModelAndView("AddRoute");
	}

	@RequestMapping(value = "/routeadded", method = RequestMethod.POST)
	public ModelAndView meth23(@ModelAttribute("rb") RouteBean rb) {
		if (adserv.addRoute(rb) == "SUCCESS") {
			return new ModelAndView("RouteAdded");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/selectallroute")
	public ModelAndView meth24(Model m) {
		m.addAttribute("routes", adserv.viewAllRoute());
		return new ModelAndView("ViewRoute");
	}

	@RequestMapping("/modifyroute")
	public ModelAndView meth25() {
		return new ModelAndView("ModifyRoute");
	}

	@RequestMapping(value = "/routemodified", method = RequestMethod.POST)
	public ModelAndView meth26(@ModelAttribute("rb") RouteBean rb) {
		if (adserv.modifyRoute(rb) == true) {
			return new ModelAndView("RouteModified");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/removeroute")
	public ModelAndView meth27() {
		return new ModelAndView("RemoveRoute");
	}

	@RequestMapping(value = "/routeremoved", method = RequestMethod.POST)
	public ModelAndView meth28(@RequestParam("routeID") String routeid) {
		if (adserv.removeRoute(routeid) == 1) {
			return new ModelAndView("RouteRemoved");
		} else {
			return new ModelAndView("InvalidPage");
		}
	}

	@RequestMapping("/viewroutebyid")
	public ModelAndView meth29() {
		return new ModelAndView("ViewRouteById");
	}

	@RequestMapping(value = "/routeviewedbyid", method = RequestMethod.POST)
	public ModelAndView meth30(@RequestParam("routeID") String routeid, Model m) {
		m.addAttribute("route", adserv.viewRouteById(routeid));
		return new ModelAndView("RouteViewedById");
	}

	@RequestMapping("/viewpassengerbyflight")
	public ModelAndView meth31() {
		return new ModelAndView("ViewPassengerByFlight");
	}

	@RequestMapping(value = "/passengerbyflightviewed", method = RequestMethod.POST)
	public ModelAndView meth32(@RequestParam("scheduleID") String scheduleID, Model m) {
		m.addAttribute("passengers", adserv.viewPassengerByFlight(scheduleID));
		return new ModelAndView("PassengerViewedByFlight");
	}
}
