package com.stripe.StripeApp.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.StripeApp.model.PaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class PaymentService {

	@Value("${STRIPE_SECRET_KEY}")
	private String secretKey;
	
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
    
	public String charge(PaymentRequest chargeRequest) throws StripeException {
		 Stripe.apiKey = secretKey;
		 Map<String, Object> chargeParams = new HashMap();
	     chargeParams.put("amount", chargeRequest.getAmount());
	     chargeParams.put("currency", PaymentRequest.Currency.INR);
	     chargeParams.put("source", chargeRequest.getToken().getId());
	     
		Charge charge = Charge.create(chargeParams);
		System.out.println(charge);
		System.out.println("..................");
		System.out.println(charge.toString());
		return charge.getId();
	}
	
	public String createPayment(PaymentRequest paymentRequest) {
		System.out.println("create payment request received ");
		System.out.println("payment type :" + paymentRequest.getType());
		Stripe.apiKey = secretKey;
		PaymentIntent response ;
		try {
			PaymentIntentCreateParams params;
			if(paymentRequest.getType().equals("bid")) {
				params =  PaymentIntentCreateParams.builder()
					    .setAmount((long) paymentRequest.getAmount())
					    .setCurrency(PaymentRequest.Currency.USD.name())
					    .addPaymentMethodType("card")
					    .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.MANUAL)
					    .build();
				response = PaymentIntent.create(params);
				return response.getId();
				
			}else if(paymentRequest.getType().equals("direct")){
				params =  PaymentIntentCreateParams.builder()
					    .setAmount((long) paymentRequest.getAmount())
					    .setCurrency(PaymentRequest.Currency.USD.name())
					    .addPaymentMethodType("card")
//					    .setAutomaticPaymentMethods(
//					    	      PaymentIntentCreateParams.CaptureMethod
//					    	        .builder()
//					    	        .setEnabled(true)
//					    	        .build()
//					    	    )
					    .build();
				 response = PaymentIntent.create(params);
				 System.out.println(response.getId());
				 confirmPayment(response.getId());
				 return response.getId();
			
			}
			else if(paymentRequest.getType().equals("captured")){
				params =  PaymentIntentCreateParams.builder()
					    .setAmount((long) paymentRequest.getAmount())
					    .setCurrency(PaymentRequest.Currency.USD.name())
					    .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.MANUAL)
					    .build();
				 response = PaymentIntent.create(params);
				 System.out.println(response);
				 System.out.println(response.getId());
				 confirmPayment(response.getId());
				 //capturePayment(response.getId() , Long.valueOf(paymentRequest.getAmount()));
				 return response.getId();
			
			}
			
		} catch (StripeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String confirmPayment(String paymentId) {
		
		Stripe.apiKey = secretKey;
		PaymentIntent paymentIntent;
		try {
			paymentIntent = PaymentIntent.retrieve(paymentId);
			Map<String, Object> params = new HashMap();
			params.put("payment_method", "pm_card_visa");
			
			PaymentIntent updatedPaymentIntent = paymentIntent.confirm(params);
			 System.out.println(updatedPaymentIntent);
			 System.out.println("...........................");
			 System.out.println(updatedPaymentIntent.toString());
			return updatedPaymentIntent.getStatus();
			
		} catch (StripeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String capturePayment(String id , Long amount) {
		System.out.println();
		Stripe.apiKey = secretKey;
		PaymentIntent paymentIntent;
		try {
			paymentIntent = PaymentIntent.retrieve(id);
			paymentIntent.setAmountCapturable(amount);
			 PaymentIntent updatedPaymentIntent = paymentIntent.capture();
			 System.out.println(updatedPaymentIntent);
			 System.out.println("...........................");
			 System.out.println(updatedPaymentIntent.toString());
			 return updatedPaymentIntent.getStatus();
		} catch (StripeException e) {
			e.printStackTrace();
		}
		return null;
       
	}
	
	public String cancelPayment(String id) {
		Stripe.apiKey = secretKey;
		PaymentIntent paymentIntent;
		try {
			paymentIntent = PaymentIntent.retrieve(id );
			PaymentIntent updatedPaymentIntent = paymentIntent.cancel();
			System.out.println(updatedPaymentIntent);
			return updatedPaymentIntent.getStatus();
		} catch (StripeException e) {
			e.printStackTrace();
		}

		return null;
		
	}
}
