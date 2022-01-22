package com.stripe.StripeApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.StripeApp.model.PaymentRequest;
import com.stripe.StripeApp.service.PaymentService;
import com.stripe.exception.StripeException;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService service;

	@PostMapping
	public ResponseEntity<String> completePayment(@RequestBody PaymentRequest request) throws StripeException {
		String chargeId= service.charge(request);
		return chargeId!=null? new ResponseEntity<String>(chargeId,HttpStatus.OK):
			new ResponseEntity<String>("Please check the credit card details entered",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public String handleError(StripeException ex) {
		return ex.getMessage();
	}
	
	@PostMapping("/createPayment")
	public ResponseEntity<String> createPayment(@RequestBody PaymentRequest request){
		String paymentId = service.createPayment(request);
		return paymentId!=null? new ResponseEntity<String>(paymentId,HttpStatus.OK):
			new ResponseEntity<String>("Please check the credit card details entered",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/capturePayment")
	public ResponseEntity<String> capturePayment(@RequestParam(name = "paymentId")String paymentId,
			@RequestParam(name = "amount")Long amount){
		String response = service.capturePayment(paymentId, amount);
		return response!=null? new ResponseEntity<String>(response,HttpStatus.OK):
			new ResponseEntity<String>("Please check the credit card details entered",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/confirmPayment")
	public ResponseEntity<String> confirmPayment(@RequestParam(name = "paymentId")String paymentId){
		String response = service.confirmPayment(paymentId);
		return response!=null? new ResponseEntity<String>(response,HttpStatus.OK):
			new ResponseEntity<String>("Please check the credit card details entered",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/cancelPayment")
	public ResponseEntity<String> cancelPayment(@RequestParam(name = "paymentId")String paymentId){
		String response = service.cancelPayment(paymentId);
		return response!=null? new ResponseEntity<String>(response,HttpStatus.OK):
			new ResponseEntity<String>("Please check the credit card details entered",HttpStatus.BAD_REQUEST);
	}
	
//	@PostMapping("/create-checkout-session")
//	public ResponseEntity<String> createPaymenttest(){
//		 SessionCreateParams params =
//			        SessionCreateParams.builder()
//			          .setMode(SessionCreateParams.Mode.PAYMENT)
//			          .setSuccessUrl("https://example.com/success")
//			          .setCancelUrl("https://example.com/cancel")
//			          .addLineItem(
//			          SessionCreateParams.LineItem.builder()
//			            .setQuantity(1L)
//			            .setPriceData(
//			              SessionCreateParams.LineItem.PriceData.builder()
//			                .setCurrency("usd")
//			                .setUnitAmount(2000L)
//			                .setProductData(
//			                  SessionCreateParams.LineItem.builder()
//			                    .setName("T-shirt")
//			                    .build())
//			                .build())
//			            .build())
//			          .build();
//
//			      Session session = Session.create(params);
//			      
//		
//	}
}
