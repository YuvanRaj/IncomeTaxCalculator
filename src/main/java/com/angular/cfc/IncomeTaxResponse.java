package com.angular.cfc;

public class IncomeTaxResponse {
	private Double taxPerMonth;
	private Double taxPerYear;
	private Double netTaxableIncome;

	private String hraIntellengceMsg;
	private String c80IntellengceMsg;
	private String educationLoanIntellengceMsg;
	private String npsIntellengceMsg;
	private String housingLoanIntellengceMsg;

	public String getHraIntellengceMsg() {
		return hraIntellengceMsg;
	}

	public void setHraIntellengceMsg(String hraIntellengceMsg) {
		this.hraIntellengceMsg = hraIntellengceMsg;
	}

	public String getC80IntellengceMsg() {
		return c80IntellengceMsg;
	}

	public void setC80IntellengceMsg(String c80IntellengceMsg) {
		this.c80IntellengceMsg = c80IntellengceMsg;
	}

	public String getEducationLoanIntellengceMsg() {
		return educationLoanIntellengceMsg;
	}

	public void setEducationLoanIntellengceMsg(String educationLoanIntellengceMsg) {
		this.educationLoanIntellengceMsg = educationLoanIntellengceMsg;
	}

	public String getNpsIntellengceMsg() {
		return npsIntellengceMsg;
	}

	public void setNpsIntellengceMsg(String npsIntellengceMsg) {
		this.npsIntellengceMsg = npsIntellengceMsg;
	}

	public String getHousingLoanIntellengceMsg() {
		return housingLoanIntellengceMsg;
	}

	public void setHousingLoanIntellengceMsg(String housingLoanIntellengceMsg) {
		this.housingLoanIntellengceMsg = housingLoanIntellengceMsg;
	}

	public Double getTaxPerYear() {
		return taxPerYear;
	}

	public void setTaxPerYear(Double taxPerYear) {
		this.taxPerYear = taxPerYear;
	}

	public Double getNetTaxableIncome() {
		return netTaxableIncome;
	}

	public void setNetTaxableIncome(Double netTaxableIncome) {
		this.netTaxableIncome = netTaxableIncome;
	}

	public Double getTaxPerMonth() {
		return taxPerMonth;
	}

	public void setTaxPerMonth(Double taxPerMonth) {
		this.taxPerMonth = taxPerMonth;
	}

}
