import React from "react";
import StripeButton from "./stripebutton.component";
import image9 from './image9.jpg';
import Cardholder from './Cardholder';

const Checkout = () => {
  return (
    <div>
      <div >
        <div>
          <span>CHECKOUT</span>
        </div>
      </div>
      <div >
	  <img src={image9} alt="item" />
      </div>
      <div >TOTAL : $500</div>
      <StripeButton price="500" paymenttype="direct"/>
    </div>
  );
};

export default Checkout;
