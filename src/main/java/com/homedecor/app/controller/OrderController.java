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

import com.homedecor.app.dto.OrderByCustomer;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.OrderException;
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.service.OrderService;

/************************************************************************************
 *          @author          Prince Verma
 *          Description      It is a controller class which provides RESTFUl API's to handle different HTTP requests.
 *          Version          1.0
 *          Created Date     16-AUG-2022
 ************************************************************************************/

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	/************************************************************************************
	 * Method: addOrder
     * Description: To handle a Http POST request
     * 
     * @Object orderByCustomer       - orderByCustomer's object
	 * @returns Boolean              - true
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PostMapping("order")
	public Boolean addOrder(@RequestBody OrderByCustomer orderByCustomer) throws OrderException {
			this.orderService.addOrder(orderByCustomer);
		
		return true;
	}
	
	/************************************************************************************
	 * Method: getOrderById
     * Description: To handle a Http GET request
     * 
     * @OParam orderId                       - orderByCustomer's Id
	 * @returns Optional<OrderByCustomer>    - foundOrder
     * Created By                            - Prince Verma
     * Created Date                          - 16-AUG-2022                           
	 
	 ************************************************************************************/

	
	@GetMapping("order/{orderId}")
	public Optional<OrderByCustomer> getOrderById(@PathVariable ("orderId") Integer orderId) throws OrderException  {
		Optional<OrderByCustomer> foundOrder = this.orderService.getOrderById(orderId);
		 return foundOrder;
	}
	
	/************************************************************************************
	 * Method: updateOrder
     * Description: To handle a Http PATCH request
     * 
     * @Object orderByCustomer       - orderByCustomer's object
	 * @returns OrderByCustomer      - foundOrder
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	
	@PatchMapping("order")
	public OrderByCustomer updateOrder(@RequestBody OrderByCustomer order) throws OrderException {
		OrderByCustomer foundOrder = this.orderService.updateOrder(order);
		 return foundOrder;
	}
	
	/************************************************************************************
	 * Method: getAllOrders
     * Description: To handle a Http GET request
     * 
	 * @returns List<OrderByCustomer>    - foundOrders
     * Created By                        - Prince Verma
     * Created Date                      - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("orders")
	public List<OrderByCustomer> getAllOrders() throws OrderException {
		List<OrderByCustomer> foundOrders = this.orderService.getAllOrders();
		 return foundOrders;
		
	}
	
	/************************************************************************************
	 * Method: deleteOrderById
     * Description: To handle a Http DELETE request
     * 
     * @Param orderId                - order's Id
	 * @returns Boolean              - true
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@DeleteMapping("order/{orderId}")
	public Boolean deleteOrderById(@PathVariable("orderId") Integer orderId) throws OrderException {
			this.orderService.deleteOrderById(orderId);
		return true;
	}
	
	/************************************************************************************
	 * Method: placeOrder
     * Description: To handle a Http PATCH request
     * 
     * @Param customerId             - Customer's Id
     * @Param orderId                - Order's Id
	 * @returns String               - Order Placed Successfully
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PatchMapping("order/placeOrder/{customerId}/{orderId}")
	public String placeOrder(@PathVariable("customerId") Integer customerId,@PathVariable("orderId") Integer orderId)
			throws OrderException, PaymentException, CartException, CustomerException {
				this.orderService.placeOrder(customerId,orderId);
		return "Order Placed Successfully";
	}

}
