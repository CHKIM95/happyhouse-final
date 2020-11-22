package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.mapper.SurroundingMapper;

@Service
public class SurroundingServiceImpl implements SurroundingService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ClinicDto> searchClinics(String gugun) throws Exception {
		return sqlSession.getMapper(SurroundingMapper.class).searchClinics(gugun);
	}

	@Override
	public List<HospitalDto> searchHospitals(String gugun) throws Exception {	
		return sqlSession.getMapper(SurroundingMapper.class).searchHospitals(gugun);
	}

}
