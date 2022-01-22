import React from "react";
import headphones from "./../images/headphones.jpg";
import applewatch from "./../images/applewatch.jpg";
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
	  <Cardholder name="Art 1" description="Description of Art 1" image={image9}/>
        
			
      </div>
      <div >TOTAL : $500</div>
      <StripeButton price="500" paymenttype="direct"/>
    </div>
  );
};

export default Checkout;
