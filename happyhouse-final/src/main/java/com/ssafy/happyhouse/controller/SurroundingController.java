package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

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

@RestController
@RequestMapping("/surrounding")
public class SurroundingController {

	@Autowired
	private SurroundingService surroundingService;
	
	@RequestMapping(value = "/hospital", method = RequestMethod.GET)
	public List<HospitalDto> getHospitals(@RequestParam String gugun) throws Exception {
		return surroundingService.searchHospitals(gugun);
	}
	
	@RequestMapping(value = "/clinic", method = RequestMethod.GET)
	public List<ClinicDto> getClinics(@RequestParam String gugun) throws Exception {
		return surroundingService.searchClinics(gugun);
	}
	
	
}
