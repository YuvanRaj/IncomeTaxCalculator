var myfirstApp = angular.module("MyFirstApp", [ 'ngGrid',
		'gridshore.c3js.chart' ]);

// Controller Configuration
myfirstApp
		.controller(
				'myFirstAppController',
				[
						'$scope',
						'$http',
						function($scope, $http) {

							$scope.change = function() {		
								if($scope.requestParam.basic > $scope.requestParam.totalFixedAmt)
									$scope.basicFixedErrorMsg = "Basic Amount should not be greater than Total Fixed Amount";
								else
									$scope.basicFixedErrorMsg = "";
								if($scope.requestParam.hra > $scope.requestParam.totalFixedAmt)
									$scope.hraFixedErrorMsg = "basicFixedErrorMsg Amount should not be greater than Total Fixed Amount";
								else
									$scope.hraFixedErrorMsg = "";
								
								if($scope.requestParam.interestRate > $scope.requestParam.totalFixedAmt)
									$scope.interestFixedErrorMsg = "Home Loan Interest Amount should not be greater than Total Fixed Amount";
								else
									$scope.interestFixedErrorMsg = "";
								
								if($scope.requestParam.c80 > $scope.requestParam.totalFixedAmt)
									$scope.c80FixedErrorMsg = "80'c Amount should not be greater than Total Fixed Amount";
								else
									$scope.c80FixedErrorMsg = "";
								
								if($scope.requestParam.d80 > $scope.requestParam.totalFixedAmt)
									$scope.d80FixedErrorMsg = "80'D Amount should not be greater than Total Fixed Amount";
								else
									$scope.d80FixedErrorMsg = "";
								
								if($scope.requestParam.nps > $scope.requestParam.totalFixedAmt)
									$scope.npsFixedErrorMsg = "NPS Amount should not be greater than Total Fixed Amount";
								else
									$scope.npsFixedErrorMsg = "";
								if($scope.requestParam.educationLoan > $scope.requestParam.totalFixedAmt)
									$scope.eduFixedErrorMsg = "Edu Loan Amount should not be greater than Total Fixed Amount";
								else
									$scope.eduFixedErrorMsg = "";
								
								// Maximum Value Validation								
								if($scope.requestParam.interestRate > 200000)
									$scope.interestMaxErrorMsg ="Home Loan Interest Amount should not be greater than 200000";
								else
									$scope.interestMaxErrorMsg ="";
									
								if($scope.requestParam.c80 > 150000)
									$scope.c80MaxErrorMsg = "80'C Amount should not be greater than 150000";
								else
									$scope.c80MaxErrorMsg = "";
									
								if($scope.requestParam.nps > 50000)
									$scope.npsMaxErrorMsg = "NPS Amount should not be greater than 150000";
								else
									$scope.npsMaxErrorMsg = "";
								
								if($scope.requestParam.educationLoan > 50000)
									$scope.eduMaxErrorMsg = "Edu Loan Amount should not be greater than 200000";
								else
									$scope.eduMaxErrorMsg = "";
								
								// Nagative Value Validation
								if($scope.requestParam.totalFixedAmt < 0)
									$scope.fixedNegativeMsg = "Please Enter Positive Numbers";
								else
									$scope.fixedNegativeMsg = "";
								if($scope.requestParam.basic < 0)
									$scope.basicNegativeMsg = "Please Enter Positive Numbers";
								else
									$scope.basicNegativeMsg = "";
								if($scope.requestParam.hra < 0)
									$scope.hraNegativeMsg = "Please Enter Positive Numbers";
								else
									$scope.hraNegativeMsg = "";
								
								if($scope.requestParam.interestRate < 0)
									$scope.interestNegativeMsg = "Please Enter Positive Numbers";
								else
									$scope.interestNegativeMsg = "";
								
								if($scope.requestParam.c80 < 0)
									$scope.c80NegativeMsg = "Please Enter Positive Numbers";
								else
									$scope.c80NegativeMsg = "";
								
								if($scope.requestParam.d80 < 0)
									$scope.d80NegativeMsg = "Please Enter Positive Numbers";
								else
									$scope.d80NegativeMsg = "";
								
								if($scope.requestParam.nps < 0)
									$scope.npsNegativeMsg = "Please Enter Positive Numbers";
								else
									$scope.npsNegativeMsg = "";
									
								if($scope.requestParam.educationLoan < 0)
									$scope.eduNegativeMsg = "Please Enter Positive Numbers";
								else
									$scope.eduNegativeMsg = "";
							};

							$scope.getIncomeTax = function(requestParam) {
								$http(
										{
											method : 'POST',
											url : "http://localhost:8080/IncometaxCalculator/services/incometax/getIncomeTaxInfo",
											data : requestParam,
											headers : {
												'Content-Type' : 'application/json'
											}
										})
										.success(
												function(data, status, headers,
														config) {
													// $scope.constantEmi =
													// data.gridResponse[0].constantEmi;
													// $scope.loanDetailList =
													// data.gridResponse;
													// $scope.datapoints=
													// data.chartInfo;
													/*
													 * var year; var keepGoing =
													 * true;
													 * angular.forEach(data.gridResponse,
													 * function(value, key) {
													 * if(keepGoing){
													 * if(value.interestDecreaseYear >
													 * 0) { console.log('value
													 * '+value.interestDecreaseYear);
													 * year =
													 * value.interestDecreaseYear;
													 * keepGoing = false; } }
													 * }); var infoValue = 'Most
													 * of the EMI payment goes
													 * towards interest till '+
													 * year + ' years. This
													 * shifts towards principal
													 * after ' + year + '
													 * years.';
													 * $scope.information =
													 * infoValue;
													 */
													$scope.netTaxableIncome = data.netTaxableIncome;
													$scope.taxPerYear = data.taxPerYear;
													$scope.taxPerMonth = data.taxPerMonth;
													$scope.hraIntellengceMsg = data.hraIntellengceMsg;
													$scope.c80IntellengceMsg = data.c80IntellengceMsg;
													$scope.educationLoanIntellengceMsg = data.educationLoanIntellengceMsg;
													$scope.npsIntellengceMsg = data.npsIntellengceMsg;
													$scope.housingLoanIntellengceMsg = data.housingLoanIntellengceMsg;
												}).error(
												function(data, status, headers,
														config) {
													$scope.status = status;
												});
							};

						} ]);