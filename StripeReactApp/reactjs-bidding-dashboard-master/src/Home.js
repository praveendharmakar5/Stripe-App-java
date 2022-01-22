import React from 'react'
import { Carousel } from 'react-bootstrap'
import { Row, Col } from 'react-bootstrap'
import image9 from './image9.jpg';
import image11 from './image11.jpg';

const Home = () => (
  <div> 
    <Row>
      <Col md={4}>
        <img src={image9} alt="Image"/>
        	<p>Description</p>
      </Col>
      <Col md={4}> 
        <img src={image11} alt="Image"/>
        	<p>Description</p>    
      </Col>
      
    </Row>
  </div>
)

export default Home
