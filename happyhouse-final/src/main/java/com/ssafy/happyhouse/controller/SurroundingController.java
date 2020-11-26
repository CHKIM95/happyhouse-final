package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/surrounding")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Api(value = "SSAFY", description = "Happy House 2020")
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

	@ApiOperation(value = "해당 시도의 대기 정보를 반환한다", response = List.class)
	@RequestMapping(value = "/air", method = RequestMethod.GET)
	public String getAir(@RequestParam String sidoOrigin) throws Exception {
		Map<String, String> map = new HashMap<String, String>(){{
				put("서울특별시", "서울");
				put("부산광역시", "부산");
				put("대구광역시", "대구");
				put("인천광역시", "인천");
				put("광주광역시", "광주");
				put("대전광역시", "대전");
				put("울산광역시", "울산");
				put("경기도", "경기");
				put("강원도", "강원");
				put("충청북도", "충북");
				put("충청남도", "충남");
				put("전라북도", "전북");
				put("전라남도", "전남");
				put("경상북도", "경북");
				put("경상남도", "경남");
				put("제주특별자치도", "제주");
				put("세종특별자치시", "세종");
		}};
		String sido = map.get(sidoOrigin);
//		String gugun = sidogugunString[1];
//		System.out.println(sido + " " +gugun);
        StringBuilder urlBuilder = new StringBuilder("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=AyUeP8dZ5o7ddKS5TcCbPX7hx1ete53nna0xg%2FSg4Bv0zr2apmYmvKmOB47nL6E%2FJr2%2BxatoCVjx5nuXX938NA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode(sido, "UTF-8")); /*시도 이름 (서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
        urlBuilder.append("&" + URLEncoder.encode("searchCondition","UTF-8") + "=" + URLEncoder.encode("HOUR", "UTF-8")); /*요청 데이터기간 (시간 : HOUR, 하루 : DAILY)*/
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
        System.out.println(sb.toString());	
		return sb.toString();
	}
	@ApiOperation(value = "해당 구의 안심식당 정보를 반환한다", response = List.class)
	@RequestMapping(value = "/food", method = RequestMethod.GET)
	public String getFood(@RequestParam String sidogugun) throws Exception {
		String[] sidogugunString = sidogugun.split(" ");
		String sido = sidogugunString[0];
		String gugun = sidogugunString[1];
		System.out.println(sido + " " +gugun);
		StringBuilder urlBuilder = new StringBuilder("http://211.237.50.150:7080/openapi/"); /*URL*/	
		urlBuilder.append("078c7e0db5aff8dc5203bdec005c20e6b529ee7fc03f5ed55192574d20f7c104/"); /*Service Key*/
		urlBuilder.append("xml/"); /*데이터 형식*/
		urlBuilder.append("Grid_20200713000000000605_1/1/5"); /*API_URL, START_INDEX, END_INDEX*/
		urlBuilder.append("?" + URLEncoder.encode("RELAX_SI_NM","UTF-8") + "=" + URLEncoder.encode(sido, "UTF-8")); /*시도 이름*/
		urlBuilder.append("&" + URLEncoder.encode("RELAX_SIDO_NM","UTF-8") + "=" + URLEncoder.encode(gugun, "UTF-8")); /* 구군이름 */
		System.out.println(urlBuilder.toString());
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
		System.out.println(sb.toString());	
		return sb.toString();
	}

// //CCTV인데 보류..
//	@ApiOperation(value = "모든 QnA게시물의 정보를 반환한다.", response = List.class)
//	@RequestMapping(value = "/cctv", method = RequestMethod.GET)
//	public String asynchronousSearch(@RequestParam String sidogugun) throws Exception {
//
//		String[] sidogugunString = sidogugun.split("");
////		String sido = sidogugun.get("sido");
////		System.out.println(sido);
////		String gugun = sidogugun.get("gugun");
////		System.out.println(gugun);
//		StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_cctv_api"); /* URL */
//		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=서비스키"); /* Service Key */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /* 페이지 번호 */
//		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
//				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* XML/JSON 여부 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("institutionNm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 관리기관명 */
//		urlBuilder.append("&" + URLEncoder.encode("rdnmadr", "UTF-8") + "="
//				+ URLEncoder.encode(sidogugunString[0] + " " + sidogugunString[1], "UTF-8")); /* 소재지도로명주소 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("lnmadr", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 소재지지번주소 */
//		urlBuilder.append("&" + URLEncoder.encode("installationPurpsType", "UTF-8") + "="
//				+ URLEncoder.encode("", "UTF-8")); /* 설치목적구분 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("cctvNumber", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 카메라대수 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("cctvPixel", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 카메라화소수 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("potogrfInfo", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 촬영방면정보 */
//		urlBuilder
//				.append("&" + URLEncoder.encode("cstdyDay", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 보관일수 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("installationYymm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 설치년월 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("phoneNumber", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 관리기관전화번호 */
//		urlBuilder.append("&" + URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 위도 */
//		urlBuilder
//				.append("&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 경도 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("referenceDate", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 데이터기준일자 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("instt_code", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 제공기관코드 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("instt_nm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 제공기관기관명 */
//		URL url = new URL(urlBuilder.toString());
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
//		BufferedReader rd;
//		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		} else {
//			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		}
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = rd.readLine()) != null) {
//			sb.append(line);
//		}
//		rd.close();
//		conn.disconnect();
//		System.out.println(sb.toString());
//		return sb.toString();
//	}
}