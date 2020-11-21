package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto member) throws Exception;
	public String getServerInfo();
	public int deleteMember(MemberDto member) throws SQLException;
	public int updateMember(MemberDto member)throws SQLException;
	public int join(MemberDto memberDto) throws SQLException;
}
