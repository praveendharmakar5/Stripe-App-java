package com.stripe.StripeApp.model;

public class PaymentRequest extends CustomerModel{

	public enum Currency{
		INR,USD;
	}
	
	   private String description;
	    private int amount;
	    private Currency currency;
	    private String stripeEmail;
	    private Token token;
	    private String setup_future_usage;
	    private String type ; 
	    
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		
		public void setCurrency(Currency currency) {
			this.currency = currency;
		}
		public String getStripeEmail() {
			return stripeEmail;
		}
		public void setStripeEmail(String stripeEmail) {
			this.stripeEmail = stripeEmail;
		}
		public Token getToken() {
			return token;
		}
		public void setToken(Token stripeToken) {
			this.token = stripeToken;
		}
		public String getSetup_future_usage() {
			return setup_future_usage;
		}
		public void setSetup_future_usage(String setup_future_usage) {
			this.setup_future_usage = setup_future_usage;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
		
		
		

	    
	    
}
