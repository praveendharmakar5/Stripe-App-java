package com.stripe.StripeApp.model;

import com.google.gson.annotations.SerializedName;

public class ConfirmPaymentRequest {
	
	 @SerializedName("payment_method_id")
	 String paymentMethodId;
	 @SerializedName("payment_intent_id")
	 String paymentIntentId;
	 

	 public String getPaymentMethodId() {
	      return paymentMethodId;
	    }

	    public String getPaymentIntentId() {
	      return paymentIntentId;
	    }

}
