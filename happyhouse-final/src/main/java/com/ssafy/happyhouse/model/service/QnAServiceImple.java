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
}
