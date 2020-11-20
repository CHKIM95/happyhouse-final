package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.QnADto;

public interface QnAMapper {

	public int insertQnA(QnADto qna) throws SQLException;

	public int updateQnA(QnADto qna) throws SQLException;

	public int deleteQnA(int no) throws SQLException;

	public QnADto detailQnA(int no) throws SQLException;

	public List<QnADto> showQnA();

//	public List<QnADto> qnas(Map<String, Object> map) throws SQLException;
//	public int getTotalCount(Map<String, String> map) throws SQLException;
}