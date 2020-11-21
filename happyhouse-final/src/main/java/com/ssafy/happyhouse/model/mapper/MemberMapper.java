package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;


@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto member) throws SQLException;
	public int deleteMember(MemberDto member) throws SQLException;
	public int updateMember(MemberDto member)throws SQLException;
	public int join(MemberDto member) throws SQLException;

}
