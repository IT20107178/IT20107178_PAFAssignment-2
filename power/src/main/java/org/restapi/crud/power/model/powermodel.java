package org.restapi.crud.power.model;

public class powermodel {
	
	private int powerId;
	private String cusname;
	private String pAccNo;
	private int psUnit;
	private String pDate;
	private double pAmout;
	
	public powermodel() {
		
	}

	public powermodel(int powerId, String cusname, String pAccNo, int psUnit, String pDate, double pAmout) {
		super();
		this.powerId = powerId;
		this.cusname = cusname;
		this.pAccNo = pAccNo;
		this.psUnit = psUnit;
		this.pDate = pDate;
		this.pAmout = pAmout;
	}

	public int getPowerId() {
		return powerId;
	}

	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public String getpAccNo() {
		return pAccNo;
	}

	public void setpAccNo(String pAccNo) {
		this.pAccNo = pAccNo;
	}

	public int getPsUnit() {
		return psUnit;
	}

	public void setPsUnit(int psUnit) {
		this.psUnit = psUnit;
	}

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

	public double getpAmout() {
		return pAmout;
	}

	public void setpAmout(double pAmout) {
		this.pAmout = pAmout;
	}
	
	

}
