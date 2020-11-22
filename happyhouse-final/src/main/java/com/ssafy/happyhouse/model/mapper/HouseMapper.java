package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.SidoGugunDongDto;

public interface HouseMapper {

	public List<SidoGugunDongDto> getSido() throws SQLException;
	public List<SidoGugunDongDto> getGugunInSido(String sido) throws SQLException;
	public List<SidoGugunDongDto> getDongInGugun(String gugun) throws SQLException;
}
