package com.stripe.StripeApp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.StripeApp.model.CardModel;
import com.stripe.StripeApp.model.CustomerModel;
import com.stripe.StripeApp.service.CustomerService;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.Token;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/createCustomer")
	public ResponseEntity<String> createPayment(@RequestBody CustomerModel request){
		String paymentId = customerService.createCustomer(request);
		return paymentId!=null? new ResponseEntity<String>(paymentId,HttpStatus.OK):
			new ResponseEntity<String>("Customer creation request failed ",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getCustomer")
	public ResponseEntity<String> getCustomedetails(@RequestParam String customerId){
		String paymentId = customerService.getCustomer(customerId);
		return paymentId!=null? new ResponseEntity<String>(paymentId,HttpStatus.OK):
			new ResponseEntity<String>("Customer creation request failed ",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<String> deleteCustomer(@RequestParam String customerId){
		String paymentId = customerService.deleteCustomer(customerId);
		return paymentId!=null? new ResponseEntity<String>(paymentId,HttpStatus.OK):
			new ResponseEntity<String>("Customer creation request failed ",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/addCardDetails")
	public ResponseEntity<String> addCardToCustomer(@RequestParam String customerId , @RequestBody CardModel request){
		Customer customer ;
		Card card;
		try {
		 customer = Customer.retrieve(customerId); //add customer id here : it will start with cus_
		Map<String, Object> cardParam = new HashMap<String, Object>(); //add card details
		cardParam.put("number", request.getNumber());
		cardParam.put("exp_month", request.getExpmonth());
		cardParam.put("exp_year", request.getExpyear());
		cardParam.put("cvc", request.getCvc());

		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam); 

		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());

			card = (Card)customer.getSources().create(source);
			String cardDetails = card.toJson();
			System.out.println("Card Details : " + cardDetails);
			customer = Customer.retrieve(customerId);//change the customer id or use to get customer by id
			System.out.println("After adding card, customer details : " + customer);
		} catch (StripeException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	

}
