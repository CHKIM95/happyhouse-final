package com.ssafy.happyhouse.model;

public class ClinicDto {

	private String date;
	private String extract;
	private String sido;
	private String gugun;
	private String name;
	private String address;
	private String weekOp;
	private String satOp;
	private String sunOp;
	private String tel;
	
	public ClinicDto() {

	}

	public ClinicDto(String date, String extract, String sido, String gugun, String name, String address, String weekOp,
			String satOp, String sunOp, String tel) {

		this.date = date;
		this.extract = extract;
		this.sido = sido;
		this.gugun = gugun;
		this.name = name;
		this.address = address;
		this.weekOp = weekOp;
		this.satOp = satOp;
		this.sunOp = sunOp;
		this.tel = tel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExtract() {
		return extract;
	}

	public void setExtract(String extract) {
		this.extract = extract;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWeekOp() {
		return weekOp;
	}

	public void setWeekOp(String weekOp) {
		this.weekOp = weekOp;
	}

	public String getSatOp() {
		return satOp;
	}

	public void setSatOp(String satOp) {
		this.satOp = satOp;
	}

	public String getSunOp() {
		return sunOp;
	}

	public void setSunOp(String sunOp) {
		this.sunOp = sunOp;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "ClinicDto [date=" + date + ", extract=" + extract + ", sido=" + sido + ", gugun=" + gugun + ", name="
				+ name + ", address=" + address + ", weekOp=" + weekOp + ", satOp=" + satOp + ", sunOp=" + sunOp
				+ ", tel=" + tel + "]";
	}

	
	
	
}
