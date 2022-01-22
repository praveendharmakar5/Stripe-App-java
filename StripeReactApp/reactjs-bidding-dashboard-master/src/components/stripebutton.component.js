import React from "react";
import StripeCheckout from "react-stripe-checkout";
import axios from "axios";

const StripeButton = ({ price }) => {
  const publishableKey = "pk_test_GiSWrVvNWrr0wBHsaKe7DcJH";
  const stripePrice = price * 100;

  const onToken = (token) => {
    console.log(token);
    axios
      .post("http://localhost:8083/payment/createPayment", {
        amount: stripePrice,
        type:"direct",
        token,
      })
      .then((response) => {
        console.log(response);
        alert("payment success");
      })
      .catch((error) => {
        console.log(error);
        alert("Payment failed");
      });
  };

  return (
    <StripeCheckout
      amount={stripePrice}
      label="Pay Now"
      name="Praveen"
      image="https://svgshare.com/i/CUz.svg"
      description={`Your total is ${price}`}
      panelLabel="Pay Now"
      token={onToken}
      stripeKey={publishableKey}
      currency="USD"
    />
  );
};

export default StripeButton;
