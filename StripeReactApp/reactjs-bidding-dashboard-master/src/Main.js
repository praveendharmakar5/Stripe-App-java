import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Products from './Products'
import Art1 from './Art1'
import Checkout from "./Checkout.components";



const Main = () => (
  <main>
    <Switch>
      <Route exact path='/' component={Products}/>
      <Route path='/art1' component={Art1}/>
	  <Route path='/directsale' component={Checkout}/>
    </Switch>
    
  </main>
)

export default Main
