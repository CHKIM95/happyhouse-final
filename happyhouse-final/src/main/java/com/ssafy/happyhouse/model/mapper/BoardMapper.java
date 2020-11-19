package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BoardDto;

public interface BoardMapper {

	public void insertBoard(BoardDto board) throws SQLException;

	public List<BoardDto> boards(Map<String, Object> map) throws SQLException;

	public void updateBoard(BoardDto board) throws SQLException;

	public void deleteBoard(int no) throws SQLException;

	public BoardDto detailBoard(int no) throws SQLException;

	public int getTotalCount(Map<String, String> map) throws SQLException;

	public List<BoardDto> retrieveBoard();

}