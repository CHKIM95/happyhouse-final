package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
@Api(value = "SSAFY", description = "Happy House 2020")
public class MemberController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MemberService memberService;
	
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@ApiOperation(value = "로그인 정보 조회 후 성공/실패 여부를 반환한다", response = List.class)
	@PostMapping("/confirm/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto, HttpServletResponse response, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if(loginUser != null) {
				String token = jwtService.create(loginUser);
				logger.trace("로그인 토큰정보 : {}", token);
				
				resultMap.put("auth-token", token);
				resultMap.put("user-id", loginUser.getUserid());
				resultMap.put("user-name", loginUser.getUsername());
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


	
	@ApiOperation(value = "회원 정보를 반환한다", response = List.class)
	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원 정보 등록 후 성공/실패 여부를 반환한다", response = List.class)
	@PostMapping("/join")
	public ResponseEntity<String> joinUser(@RequestBody MemberDto member) throws SQLException {
		int resultCnt = memberService.join(member);
		if(resultCnt > 0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 정보 수정 후 성공/실패 여부를 반환한다", response = List.class)
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody MemberDto member) throws SQLException {
		int resultCnt = memberService.updateMember(member);
		if(resultCnt > 0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 정보 삭제 후 성공/실패 여부를 반환한다", response = List.class)
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser(MemberDto member) throws SQLException {
		int resultCnt = memberService.deleteMember(member);
		if(resultCnt > 0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
}
