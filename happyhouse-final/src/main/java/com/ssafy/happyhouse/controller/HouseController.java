package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.SidoGugunDongDto;
import com.ssafy.happyhouse.model.service.HouseService;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/house")
@RestController
public class HouseController {

	@Autowired
	private HouseService houseService;
	
	@RequestMapping(value = "/sido", method = RequestMethod.GET)
	public List<SidoGugunDongDto> getSido() throws Exception {
		return houseService.getSido();
	}
	
	@RequestMapping(value = "/gugun", method = RequestMethod.GET)
	public List<SidoGugunDongDto> getGugunInSido(@RequestParam String sido) throws Exception {
		return houseService.getGugunInSido(sido);
	}
	
	@RequestMapping(value = "/dong", method = RequestMethod.GET)
	public List<SidoGugunDongDto> getDongInGugun(@RequestParam String gugun) throws Exception {
		return houseService.getDongInGugun(gugun);
	}
	
	@RequestMapping(value = "/searchHouse", method = RequestMethod.GET)
	public String asynchronousSearch(@RequestParam Map<String, String> searchWords) throws Exception{
		
		String houseType = searchWords.get("houseType");
		System.out.println(houseType);
		String dealType = searchWords.get("dealType");
		String gugun = searchWords.get("gugun");
		
		StringBuilder urlBuilder = new StringBuilder();
		if(houseType.equals("apartment") && dealType.equals("buy")) {
			urlBuilder.append("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade");/*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=xqaZgA8j3VAcmQ5jAJNUuBLXnZsTO3nUZBxwIwld2ePYvsg11q3N5PHksiPBSS9SbtuSuidUnHXcL6cqbCY0IA%3D%3D"); /*Service Key*/
		}
		else if(houseType.equals("apartment") && dealType.equals("rent")) {
			urlBuilder.append("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent");/*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=xqaZgA8j3VAcmQ5jAJNUuBLXnZsTO3nUZBxwIwld2ePYvsg11q3N5PHksiPBSS9SbtuSuidUnHXcL6cqbCY0IA%3D%3D"); /*Service Key*/
		}
		else if(houseType.equals("multiGeneration") && dealType.equals("buy")) {
			urlBuilder.append("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade");/*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=xqaZgA8j3VAcmQ5jAJNUuBLXnZsTO3nUZBxwIwld2ePYvsg11q3N5PHksiPBSS9SbtuSuidUnHXcL6cqbCY0IA%3D%3D"); /*Service Key*/
		}
		else if(houseType.equals("multiGeneration") && dealType.equals("rent")) {
			urlBuilder.append("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHRent");/*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=xqaZgA8j3VAcmQ5jAJNUuBLXnZsTO3nUZBxwIwld2ePYvsg11q3N5PHksiPBSS9SbtuSuidUnHXcL6cqbCY0IA%3D%3D"); /*Service Key*/
		}
		urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode(gugun, "UTF-8")); /*각 지역별 코드*/
		urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode("201512", "UTF-8")); /*월 단위 신고자료*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	
	}
}
