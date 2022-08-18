package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.PaymentRepository;
import com.homedecor.app.dto.Payment;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.exception.WishlistException;

/************************************************************************************
 *          @author          Prateek Tomar
 *          Description      It is a service class that provides the services for adding a new payment, 
                             get payment by id,get all payments information, updating into an existing payment, delete admin by id.  
  *         Version          1.0
  *         Created Date     16-AUG-2022
 ************************************************************************************/


@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
        
	/************************************************************************************
	 * Method: addPayment
     * Description: To register a new payment into the database
     * @Object payment                - adding payment by providing mandatory payment details
	 * @returns Boolean               - true, if payment added successfully otherwise throws PaymentException
	 * @throws PaymentException       - It is raised if payment Id is already registered in our database
     * Created By                     - Prateek Tomar
     * Created Date                   - 16-AUG-2022                           
	 
	 ************************************************************************************/

	
	@Override
	public Boolean addPayment(Payment payment) throws PaymentException {
		if (payment == null) {
			throw new PaymentException("Payment not added ! Please fill the mandatory field");
		}
		Optional<Payment> foundPayment = this.paymentRepository.findById(payment.getPaymentId());
		if (foundPayment.isPresent()) {
			throw new PaymentException("This Payment Id already exist");
		}
		else {
			this.paymentRepository.save(payment);
		}
		return true;
	}
	
	/************************************************************************************
	 * Method: getPaymentById
     * Description: To get an admin by AdminId
     * 
     * @param paymentId              - paymentId
	 * @returns Boolean              - isPresent, if payment Id exists in our database otherwise throws PaymentException
	 * @throws PaymentException      - It is raised if payment Id is not present or we provide an invalid payment Id                                                   
     * Created By                    - Prateek Tomar
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@Override
	public Optional<Payment> getPaymentById(Integer paymentId) throws PaymentException {
		Optional<Payment> foundPayment = this.paymentRepository.findById(paymentId);
		if (foundPayment.isEmpty()) {
			throw new PaymentException("Payment ID is not present in record");
		}
		return foundPayment; 
	}
	
	/************************************************************************************
	 * Method: getAllPayments
	 * Description: To fetch every payment from the record.
	 * 
	 * @returns List                 - payments list, if payment is already present in the database otherwise throws PaymentException
	 * @throws PaymenttException     - It is raised if the payment table is empty.
	 * Created By                    - Prateek Tomar 
	 * Created Date                  - 16-AUG-2022
	 ************************************************************************************/
	

	@Override
	public List<Payment> getAllPayments() throws PaymentException {
		List<Payment> paymentList = this.paymentRepository.findAll();
		if (paymentList.isEmpty()) {
			throw new PaymentException("Payment list not present");
		}
		return paymentList;
	}

	/************************************************************************************
	 * Method: deletePaymentById
     * Description: To delete an existing Payment through its Id
     * 
     * @param paymentId              - payment's Id
	 * @returns Boolean              - true, if payment Id is present in our database otherwise throws AdminException
	 * @throws PaymentException      - It is raised if payment Id is not present or we provide an invalid admin Id                                                   
     * Created By                    - Prateek Tomar
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/
	
	@Override
	public Boolean deletePaymentById(Integer paymentId) throws PaymentException {
		Optional<Payment> foundPayment = this.paymentRepository.findById(paymentId);
		if (foundPayment.isEmpty()) {
			throw new PaymentException("Payment ID is not present in record");
		}
		this.paymentRepository.deleteById(paymentId);
		return true;
	}
	
	/************************************************************************************
	 * Method: updatePayment
     * Description: To update a payment record in our database
     * 
     * @Object payment            -  updating an existing payment record 
	 * @returns payment           - payment, if payment details updated successfully otherwise throws PaymentException
	 * @throws PaymentException   - It is raised if we payment Id is not present in records                                                      
     * Created By                 - Prateek Tomar
     * Created Date               - 16-AUG-2022                           
	 
	 ************************************************************************************/



	@Override
	public Payment updatePayment(Payment payment) throws PaymentException {
		Optional<Payment> foundPayment = this.paymentRepository.findById(payment.getPaymentId());
		if (foundPayment.isEmpty()) {
			throw new PaymentException("Payment ID is not present in record");
		}

		return this.paymentRepository.save(payment);
	}
	
	
}
