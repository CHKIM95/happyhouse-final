/*
 * package com.ssafy.happyhouse.controller;
 * 
 * import java.util.Date; import java.util.List; import java.util.Map;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.ssafy.happyhouse.model.BoardDto; import
 * com.ssafy.happyhouse.model.service.QnAService;
 * 
 * import io.swagger.annotations.Api; import
 * io.swagger.annotations.ApiOperation;
 * 
 * //http://localhost:8197/humans/swagger-ui.html
 * 
 * @CrossOrigin(origins = {"*"}, maxAge = 6000)
 * 
 * @RestController
 * 
 * @RequestMapping("/qna")
 * 
 * @Api(value="SSAFY", description="SSAFY Resouces Management 2019") public
 * class QnAController {
 * 
 * public static final Logger logger =
 * LoggerFactory.getLogger(QnAController.class); private static final String
 * SUCCESS = "success"; private static final String FAIL = "fail";
 * 
 * 
 * @Autowired private QnAService qnaService;
 * 
 * 
 * 모든 게시물을 불러온다
 * 
 * @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
 * 
 * @RequestMapping(value = "/boards", method = RequestMethod.GET) public
 * ResponseEntity<List<BoardDto>> retrieveBoard() throws Exception {
 * logger.debug("retrieveBoard - 호출"); return new
 * ResponseEntity<List<BoardDto>>(qnaService.retrieveBoard(), HttpStatus.OK); }
 * 
 * 
 * @ApiOperation(value = "게시물 목록을 본다", response = List.class)
 * 
 * @RequestMapping(value = "/boardstwo", method = RequestMethod.GET) public
 * ResponseEntity<List<BoardDto>> boards(@RequestBody Map<String, String> map)
 * throws Exception {
 * logger.info("1-------------boards-----------------------------"+new Date());
 * List<BoardDto> boards = boardService.boards(map); if (boards.isEmpty()) {
 * return new ResponseEntity(HttpStatus.NO_CONTENT); } return new
 * ResponseEntity<List<BoardDto>>(boards, HttpStatus.OK); }
 * 
 * @ApiOperation(value = "게시물을 등록한다", response = List.class)
 * 
 * @RequestMapping(value = "/insertBoard", method = RequestMethod.POST) public
 * ResponseEntity<String> insertBoard(@RequestBody BoardDto board) throws
 * Exception {
 * logger.info("1-------------boards-----------------------------"+new Date());
 * boardService.insertBoard(board); return new ResponseEntity<String>(SUCCESS,
 * HttpStatus.OK); }
 * 
 * @ApiOperation(value = "게시물을 수정한다", response = List.class)
 * 
 * @RequestMapping(value = "/updateBoard/{no}", method = RequestMethod.PUT)
 * public ResponseEntity<String> updateBoard(@RequestBody BoardDto
 * board, @PathVariable int no) throws Exception {
 * logger.info("1-------------boards-----------------------------"+new Date());
 * board.setNo(no); boardService.updateBoard(board); return new
 * ResponseEntity<String>(SUCCESS, HttpStatus.OK); }
 * 
 * @ApiOperation(value = "게시물의 상세 내용을 본다", response = BoardDto.class)
 * 
 * @RequestMapping(value = "/detailBoard/{no}", method = RequestMethod.GET)
 * public ResponseEntity<BoardDto> detailBoard(@PathVariable int no) throws
 * Exception {
 * logger.info("1-------------board-----------------------------"+new Date());
 * System.out.println(no); BoardDto boards = boardService.detailBoard(no); // if
 * (boards==null || boards.getIsbn()==null || boards.getIsbn().equals("")) { //
 * return new ResponseEntity(HttpStatus.NO_CONTENT); // } return new
 * ResponseEntity<BoardDto>(boards, HttpStatus.OK); }
 * 
 * @ApiOperation(value = "게시물을 삭제한다", response = BoardDto.class)
 * 
 * @RequestMapping(value = "/deleteBoard/{no}", method = RequestMethod.DELETE)
 * public ResponseEntity<String> deleteBoard(@PathVariable int no) throws
 * Exception {
 * logger.info("1-------------board-----------------------------"+new Date());
 * boardService.deleteBoard(no); // if (emp==null || emp.getIsbn()==null ||
 * emp.getIsbn().equals("")) { // return new
 * ResponseEntity(HttpStatus.NO_CONTENT); // } return new
 * ResponseEntity<String>(SUCCESS, HttpStatus.OK); } }
 */