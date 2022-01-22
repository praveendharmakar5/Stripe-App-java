import React, {PureComponent} from 'react';
import { Row, Col } from 'react-bootstrap';
import Cardholder from './Cardholder';
import image9 from './image9.jpg';
import image11 from './image11.jpg';


export default class Products extends PureComponent{
	render(){
		return(
			<div>
				<Row>
					<Col md={4}>
		  				<Cardholder name="Art 1" description="Description of Art 1" image={image9}/>
		  			</Col>
		  			<Col md={4}>
		  				<Cardholder name="Art 2" description="Description of Art 2" image={image11}/>
		  			</Col>
		  		</Row>
			</div>
		);
	}
}

