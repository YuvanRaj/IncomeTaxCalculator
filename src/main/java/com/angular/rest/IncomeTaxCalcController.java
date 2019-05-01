package com.angular.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.angular.cfc.IncomeTaxResponse;
import com.angular.cfc.RequestParam;

/**
 * Controller used to calculate the housing loan for given tenure, principle,
 * interest rate
 * 
 * @author Yuvaraj
 *
 */
@Path("/incometax")
public class IncomeTaxCalcController {

	@Path("getIncomeTaxInfo")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public IncomeTaxResponse getIncomeTax(RequestParam requestParam) throws Exception {
		IncomeTaxResponse incomeTaxResponse = new IncomeTaxResponse();

		Double totalFixedPay = requestParam.getTotalFixedAmt();
		Double taxDeductionValue = new Double(0.0);

		taxDeductionValue = taxDeductionValue + 50000;
		taxDeductionValue = taxDeductionValue + (requestParam.getHra() != null ? requestParam.getHra() :0.0);
		taxDeductionValue = taxDeductionValue + (requestParam.getInterestRate() != null ? requestParam.getInterestRate() : 0.0);
		taxDeductionValue = taxDeductionValue + 2500;
		taxDeductionValue = taxDeductionValue + (requestParam.getC80() != null ? requestParam.getC80() : 0.0);
		taxDeductionValue = taxDeductionValue + (requestParam.getD80() != null ? requestParam.getD80() : 0.0);
		taxDeductionValue = taxDeductionValue + (requestParam.getNps() != null ? requestParam.getNps() : 0.0);
		taxDeductionValue = taxDeductionValue + (requestParam.getEducationLoan() != null ? requestParam.getEducationLoan() : 0.0);
		
		
		Double netTaxableIncome = totalFixedPay - taxDeductionValue;

		
		populateTaxInfo(netTaxableIncome, incomeTaxResponse);
		
		populateIntellegenceMsg(incomeTaxResponse,requestParam);
		
		//incomeTaxResponse.setHraIntellengceMsg("Utilize the Maximum Benefit of HRA. As per Standard you can Declare 50% of basic pay as HRA");
		
		return incomeTaxResponse;

	}

	private void populateIntellegenceMsg(IncomeTaxResponse incomeTaxResponse, RequestParam requestParam) {
		Double basic = requestParam.getBasic() != null ? requestParam.getBasic() : 0.0;
		Double hra = requestParam.getHra();
		
		Double actualBasic = basic * 0.6;
		
		if(basic != null && hra != null && (actualBasic > hra)){
			Double balance = actualBasic -hra;
			incomeTaxResponse.setHraIntellengceMsg("HRA Max Declaration Limitis  50 % of Fixed Pay. Utilize Balance Amount Rs."+balance +" Under HRA Declarion");
		}
		if(requestParam.getC80() != null && requestParam.getC80() < 150000){
			Double balance = 150000 -requestParam.getC80();
			incomeTaxResponse.setC80IntellengceMsg("80'c Max Declaration Limit is Rs 150000.Utilize Balance Amount Rs."+balance+" nder 80'c Declaration");
		}		
		if(requestParam.getEducationLoan() != null && requestParam.getEducationLoan() < 200000){
			Double balance = 200000 -requestParam.getEducationLoan();
			incomeTaxResponse.setEducationLoanIntellengceMsg("Education Amount Max Declaration Limit is Rs 200000.Utilize Balance Amount Rs."+balance+" Under Education Loan Declaration");
		}
		if(requestParam.getNps() != null && requestParam.getNps() < 50000){
			Double balance = 50000 -requestParam.getNps();
			incomeTaxResponse.setNpsIntellengceMsg("NPS Max Declaration Limit is Rs 50000.Utilize Balance Amount Rs."+balance+" Under NPS Loan Declaration");
		}
		if(requestParam.getInterestRate() != null && requestParam.getInterestRate() < 200000){
			Double balance = 200000 -requestParam.getInterestRate();
			incomeTaxResponse.setHousingLoanIntellengceMsg("Housing Loan Max Declaration Limit is Rs 200000.Utilize Balance Amount Rs."+balance+" Under NPS Loan Declaration");
		}
	}

	public void populateTaxInfo(Double taxableIncome, IncomeTaxResponse incomeTaxResponse) {

		Double slab1Tax = 0.0;
		Double slab2Tax = 12500.0;
		Double slab3Tax = 100000.0;

		Double slab1TaxAmt = 250000.0;
		Double slab2TaxAmt = 250000.0;
		Double slab3TaxAmt = 500000.0;

		Double currentTax = 0.0;
		Double slabTaxSum = 0.0;
		Double totalTax = 0.0;
		incomeTaxResponse.setNetTaxableIncome(taxableIncome);

		if (taxableIncome <= 500000) {
			incomeTaxResponse.setTaxPerMonth(0.0);
			incomeTaxResponse.setTaxPerYear(0.0);
		} else if (taxableIncome > 500000 && taxableIncome <= 1000000) {
			slabTaxSum = slab1Tax + slab2Tax;
			taxableIncome = taxableIncome - (slab1TaxAmt + slab2TaxAmt);
			currentTax = taxableIncome * 0.2;
			totalTax = currentTax + slabTaxSum;
		} else if (taxableIncome > 1000000) {
			slabTaxSum = slab1Tax + slab2Tax + slab3Tax;
			taxableIncome = taxableIncome - (slab1TaxAmt + slab2TaxAmt + slab3TaxAmt);
			currentTax = taxableIncome * 0.3;
			totalTax = currentTax + slabTaxSum;
		}

		// Add Educational CESS 4%
		totalTax = totalTax +(totalTax * 0.04);

		incomeTaxResponse.setTaxPerYear(totalTax);
		incomeTaxResponse.setTaxPerMonth(totalTax / 12);
	}
}