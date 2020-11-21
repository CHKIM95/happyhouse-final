package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

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
	public MemberDto login(MemberDto member) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).login(member);
	}

	@Override
	public String getServerInfo() {
		return "사용자에게 전달하고 싶은 중요정보";
	}

	@Override
	public int deleteMember(MemberDto member) throws SQLException {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(member);
	}

	@Override
	public int updateMember(MemberDto member) throws SQLException {
		return sqlSession.getMapper(MemberMapper.class).updateMember(member);
	}

	@Override
	public int join(MemberDto member) throws SQLException {
		return sqlSession.getMapper(MemberMapper.class).join(member);
	}

}
