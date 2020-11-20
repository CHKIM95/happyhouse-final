
package com.ssafy.happyhouse.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.service.QnAService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html

@CrossOrigin(origins = { "*" }, maxAge = 6000)

@RestController

@RequestMapping("/qna")

@Api(value = "SSAFY", description = "SSAFY Resouces Management 2019")
public class QnAController {

	public static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnAService qnaService;

	/**
	 * 모든 QnA게시물의 정보를 반환한다
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "모든 QnA게시물의 정보를 반환한다.", response = List.class)
	@RequestMapping(value = "/showQnA", method = RequestMethod.GET)
	public ResponseEntity<List<QnADto>> showQnA() throws Exception {
		logger.debug("retrieveQnA - 호출");
		//TODO 목록에 값이 없을때
		List<QnADto> qnas = qnaService.showQnA();
		// TODO null이면??
//		if (qnas.isEmpty()) {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
		return new ResponseEntity<List<QnADto>>(qnas, HttpStatus.OK);
	}

	/**
	 * Qna게시물을 등록한다
	 * @param qna
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "QnA게시물을 등록한다", response = List.class)
	@RequestMapping(value = "/insertQnA", method = RequestMethod.POST)
	public ResponseEntity<String> insertQnA(@RequestBody QnADto qna) throws Exception {
		logger.info("insertQnA - 호출 " + new Date());
		qnaService.insertQnA(qna);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	/**
	 * QnA게시물을 수정한다
	 * @param qna
	 * @param no
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "QnA게시물을 수정한다", response = List.class)
	@RequestMapping(value = "/updateQnA/{no}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateQnA(@RequestBody QnADto qna, @PathVariable int no) throws Exception {
		logger.info("updateQnA - 호출 " + new Date());
		qna.setNo(no);
		qnaService.updateQnA(qna);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	/**
	 * QnA게시물의 상세 내용을 본다
	 * @param no
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "QnA게시물의 상세 내용을 본다", response = QnADto.class)
	@RequestMapping(value = "/detailQnA/{no}", method = RequestMethod.GET)
	public ResponseEntity<QnADto> detailQnA(@PathVariable int no) throws Exception {
		logger.info("detailQnA - 호출 " + new Date());
		System.out.println(no);
		QnADto qna = qnaService.detailQnA(no);
		//TODO if qnas.getNo == -1 return "NO_CONTENT" 
		if (qna == null || qna.getNo() == -1) {
			return new ResponseEntity<QnADto>(qna,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<QnADto>(qna, HttpStatus.OK);
	}

	/**
	 * QnA게시물을 삭제한다
	 * @param no
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "QnA게시물을 삭제하고 성공여부를 String으로 반환한다.", response = QnADto.class)
	@RequestMapping(value = "/deleteQnA/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteQnA(@PathVariable int no) throws Exception {
		logger.info("deleteQnA - 호출 " + new Date());
		if(qnaService.deleteQnA(no)<1) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
