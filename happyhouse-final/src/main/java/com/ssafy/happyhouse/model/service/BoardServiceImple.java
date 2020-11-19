package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class BoardServiceImple implements BoardService {

	@Autowired
	SqlSession sqlSession;

	@Override
	public void insertBoard(BoardDto boardDto) throws Exception {
		if (boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		sqlSession.getMapper(BoardMapper.class).insertBoard(boardDto);
	}

	@Override
	public List<BoardDto> boards(Map<String, String> map) throws SQLException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(BoardMapper.class).boards(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(BoardMapper.class).getTotalCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public void updateBoard(BoardDto boardDto) throws SQLException {
		sqlSession.getMapper(BoardMapper.class).updateBoard(boardDto);

	}

	@Override
	public void deleteBoard(int no) throws SQLException {
		sqlSession.getMapper(BoardMapper.class).deleteBoard(no);
	}

	@Override
	public BoardDto detailBoard(int no) throws SQLException {
		return sqlSession.getMapper(BoardMapper.class).detailBoard(no);
	}

	@Override
	public List<BoardDto> retrieveBoard() {
		return sqlSession.getMapper(BoardMapper.class).retrieveBoard();
	}

}
