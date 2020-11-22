package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.SidoGugunDongDto;
import com.ssafy.happyhouse.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<SidoGugunDongDto> getSido() throws SQLException {
		
		return sqlSession.getMapper(HouseMapper.class).getSido();
	}

	@Override
	public List<SidoGugunDongDto> getGugunInSido(String sido) throws SQLException {
		return sqlSession.getMapper(HouseMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunDongDto> getDongInGugun(String gugun) throws SQLException {
		return sqlSession.getMapper(HouseMapper.class).getDongInGugun(gugun);
	}

}
