package com.homedecor.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Payment;
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.service.PaymentService;
/************************************************************************************
 *          @author          Prateek Tomar
 *          Description      It is a controller class which provides RESTFUl API's to handle different HTTP requests.
 *          Version          1.0
 *          Created Date     16-AUG-2022
 ************************************************************************************/


@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;


	/************************************************************************************
	 * Method: addPayment
     * Description: To handle a Http POST request
     * 
     * @Object payment               - Payment's object
	 * @returns String               - payment added Successfully
     * Created By                    - Prateek Tomar
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PostMapping("payment")
	public String addPayment(@RequestBody Payment payment) throws PaymentException {
			this.paymentService.addPayment(payment);
		return "payment added successfully";
	}

	/************************************************************************************
	 * Method: getPaymentById
     * Description: To handle a Http GET request
     * 
     * @Param paymentid              - Payment's Id
	 * @returns Optional<Payment>    - foundPayment          - 
     * Created By                    - Prateek Tomar
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("payment/{paymentid}")
	public Optional<Payment> getPaymentById(@PathVariable("paymentid") Integer paymentId) throws PaymentException {
		
		return this.paymentService.getPaymentById(paymentId);
	}

	/************************************************************************************
	 * Method: updatePayment
     * Description: To handle a Http PATCH request
     * 
     * @Object payment               - Payment's object
	 * @returns Payment              - updated payment
     * Created By                    - Prateek Tomar
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PatchMapping("payment")
	public Payment updatePayment(@RequestBody Payment payment) throws PaymentException {
	
		return this.paymentService.updatePayment(payment);
	}

	/************************************************************************************
	 * Method: getAllPayment
     * Description: To handle a Http GET request
     * 
   	 * @returns List<Payment>        - list of all payments
     * Created By                    - Prateek Tomar
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("payment/")
	public List<Payment> getAllPayment() throws PaymentException {
	
		return this.paymentService.getAllPayments();
	}
	
	/************************************************************************************
	 * Method: deletePaymentById
     * Description: To handle a Http DELETE request
     * 
     * @Param paymentid              - Payment's Id
	 * @returns String               - payment deleted Successfully
     * Created By                    - Prateek Tomar
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@DeleteMapping("payment/{paymentid}")
	public String deletePaymentById(@PathVariable("paymentid") Integer paymentId) throws PaymentException {
		this.paymentService.deletePaymentById(paymentId);
		return "payment deleted successfully";
	}

}
