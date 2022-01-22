package com.stripe.StripeApp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stripe.StripeApp.model.CardModel;
import com.stripe.StripeApp.model.CustomerModel;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.Token;

@Service
public class CustomerService {
	
	public String createCustomer(CustomerModel request) {
		Map<String, Object> params = new HashMap();
		params.put("name",request.getName());
		params.put("phone", request.getPhone());
		params.put("email", request.getEmail());
		params.put("description",request.getDescription());
		params.put("balance",request.getBalance());
		//params.put("currency",PaymentRequest.Currency.USD.name());

		try {
			Customer customer = Customer.create(params);
			System.out.println(customer);
			return customer.getId();
		} catch (StripeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getCustomer(String customerId) {
		try {
			Customer customer = Customer.retrieve(customerId);
			System.out.println(customer);
			return customer.getId();
		} catch (StripeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteCustomer(String customerId) {
		Customer customer;
		try {
			customer = Customer.retrieve(customerId);
			Customer deletedCustomer = customer.delete();
			System.out.println(deletedCustomer);
			return customer.getId();
		} catch (StripeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String addCardDetails(CardModel cardetails , String customerId) {
		Customer customer ;
		Card card;
		try {
		 customer = Customer.retrieve(customerId); //add customer id here : it will start with cus_
		Map<String, Object> cardParam = new HashMap<String, Object>(); //add card details
		cardParam.put("number", cardetails.getNumber());
		cardParam.put("exp_month", cardetails.getExpmonth());
		cardParam.put("exp_year", cardetails.getExpyear());
		cardParam.put("cvc", cardetails.getCvc());

		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam); // create a token

		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId()); //add token as source
			card = (Card)customer.getSources().create(source);
			String cardDetails = card.toJson();
			System.out.println("Card Details : " + cardDetails);
			customer = Customer.retrieve(customerId);
			System.out.println("After adding card, customer details : " + customer);
		} catch (StripeException e) {
			e.printStackTrace();
		} 
		return null;

		
	}

}
