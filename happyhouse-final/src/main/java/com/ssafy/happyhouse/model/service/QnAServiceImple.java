package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.mapper.QnAMapper;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class QnAServiceImple implements QnAService {

	@Autowired
	SqlSession sqlSession;

	@Override
	public int insertQnA(QnADto qnaDto) throws Exception {
		if (qnaDto.getSubject() == null || qnaDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnAMapper.class).insertQnA(qnaDto);
	}

	@Override
	public int updateQnA(QnADto qnaDto) throws SQLException {
		return sqlSession.getMapper(QnAMapper.class).updateQnA(qnaDto);
	}

	@Override
	public int deleteQnA(int no) throws SQLException {
		return sqlSession.getMapper(QnAMapper.class).deleteQnA(no);
	}

	@Override
	public QnADto detailQnA(int no) throws SQLException {
		return sqlSession.getMapper(QnAMapper.class).detailQnA(no);
	}

	@Override
	public List<QnADto> showQnA() {
		return sqlSession.getMapper(QnAMapper.class).showQnA();
	}
	
//	@Override
//	public List<QnADto> qnas(Map<String, String> map) throws SQLException {
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("key", map.get("key") == null ? "" : map.get("key"));
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt(map.get("pg"));
//		int sizePerPage = Integer.parseInt(map.get("spp"));
//		int start = (currentPage - 1) * sizePerPage;
//		param.put("start", start);
//		param.put("spp", sizePerPage);
//		return sqlSession.getMapper(QnAMapper.class).qnas(param);
//	}

//	@Override
//	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
//		int naviSize = 10;
//		int currentPage = Integer.parseInt(map.get("pg"));
//		int sizePerPage = Integer.parseInt(map.get("spp"));
//		PageNavigation pageNavigation = new PageNavigation();
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
//		int totalCount = sqlSession.getMapper(QnAMapper.class).getTotalCount(map);
//		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//		return pageNavigation;
//	}
}
