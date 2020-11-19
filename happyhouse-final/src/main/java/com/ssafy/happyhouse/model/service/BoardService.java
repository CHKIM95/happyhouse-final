package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.util.PageNavigation;


//OCP -> ISP 
public interface BoardService {
	public void insertBoard(BoardDto boardDto) throws SQLException, Exception;
	public List<BoardDto> boards(Map<String, String> map) throws SQLException;
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	public void updateBoard(BoardDto boardDto) throws SQLException;
	public void deleteBoard(int no) throws SQLException;
    public BoardDto detailBoard(int no) throws SQLException;
    //    public int getTotalCount(Map<String, String> map) throws SQLException;
	public List<BoardDto> retrieveBoard();
}
