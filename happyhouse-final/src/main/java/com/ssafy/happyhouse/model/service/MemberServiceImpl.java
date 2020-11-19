package com.ssafy.happyhouse.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public String getServerInfo() {
		return "사용자에게 전달하고 싶은 중요정보";
	}

}
