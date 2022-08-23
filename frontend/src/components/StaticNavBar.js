import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container'
import  '../css/StaticNavBar.css'
import {useNavigate} from "react-router-dom";
import LogOut from "./LogOut";


function StaticNavBar() {
    let navigate = useNavigate();

    return (
        <Navbar collapseOnSelect fixed="top" expand="lg" bg="light" variant="light">
            <Container fluid>
                <Navbar.Brand onClick={()=>{navigate("/")}} className={'nav-brand'} ><span>Inviggo online shop</span></Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="me-auto">
                    </Nav>
                    <Nav>
                        <Nav.Link onClick={()=>{navigate("/sign/up")}} className={'nav-btn'}>Sign up</Nav.Link>
                        <Nav.Link onClick={()=>{navigate("/sign/in")}} className={'nav-btn'}>Sign in</Nav.Link>
                        <LogOut/>
                    </Nav>
                </Navbar.Collapse>

            </Container>

        </Navbar>
    );
}

export default StaticNavBar;
