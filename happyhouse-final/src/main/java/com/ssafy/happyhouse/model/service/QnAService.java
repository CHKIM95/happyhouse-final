package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
//import java.util.Map;

import com.ssafy.happyhouse.model.QnADto;
//import com.ssafy.happyhouse.util.PageNavigation;

//OCP -> ISP 
public interface QnAService {
	public int insertQnA(QnADto qnaDto) throws SQLException, Exception;

	public int updateQnA(QnADto qnaDto) throws SQLException;

	public int deleteQnA(int no) throws SQLException;

	public QnADto detailQnA(int no) throws SQLException;

	public List<QnADto> showQnA();

//	public List<QnADto> qnas(Map<String, String> map) throws SQLException;
//	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
//	public int getTotalCount(Map<String, String> map) throws SQLException;
}
