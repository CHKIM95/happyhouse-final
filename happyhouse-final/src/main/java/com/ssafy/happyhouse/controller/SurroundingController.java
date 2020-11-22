package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.service.SurroundingService;

@Controller
@RequestMapping("/surrounding")
public class SurroundingController {

	@Autowired
	private SurroundingService surroundingService;
	
	@RequestMapping(value = "/hospital", method = RequestMethod.GET)
	public String getHospitals(@RequestParam String gugun, Model model) throws Exception {
		model.addAttribute("hospitals", surroundingService.searchHospitals(gugun));
		return "hospitals";
	}
	
	@RequestMapping(value = "/clinic", method = RequestMethod.GET)
	public String getClinics(@RequestParam String gugun, Model model) throws Exception {
		model.addAttribute("clinics", surroundingService.searchClinics(gugun));
		return "clinics";
	}
	
	
}
