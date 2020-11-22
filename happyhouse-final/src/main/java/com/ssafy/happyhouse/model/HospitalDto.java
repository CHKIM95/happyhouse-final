package com.ssafy.happyhouse.model;

public class HospitalDto {

	private String date;
	private String sido;
	private String gugun;
	private String name;
	private String address;
	private String type;
	private String tel;
	
	public HospitalDto() {
		
	}

	public HospitalDto(String date, String sido, String gugun, String name, String address, String type, String tel) {
		super();
		this.date = date;
		this.sido = sido;
		this.gugun = gugun;
		this.name = name;
		this.address = address;
		this.type = type;
		this.tel = tel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "HospitalDto [date=" + date + ", sido=" + sido + ", gugun=" + gugun + ", name=" + name + ", address="
				+ address + ", type=" + type + ", tel=" + tel + "]";
	}


	
}
