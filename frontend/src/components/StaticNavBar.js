import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container'
import  '../css/StaticNavBar.css'

function StaticNavBar() {
    return (
        <Navbar collapseOnSelect fixed="top" expand="lg" bg="light" variant="light">
            <Container fluid>
                <Navbar.Brand href="/" className={'nav-brand'} ><span>Inviggo online shop</span></Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="me-auto">
                    </Nav>
                    <Nav>
                        <Nav.Link href="/sign/up" className={'nav-btn'}>Sign up</Nav.Link>
                        <Nav.Link href="/sign/in" className={'nav-btn'}>Sign in</Nav.Link>
                        <Nav.Link href="/" className={'nav-btn'} onClick={(e)=>{e.preventDefault();localStorage.clear()}}>Sign out</Nav.Link>
                    </Nav>
                </Navbar.Collapse>

            </Container>

        </Navbar>
    );
}

export default StaticNavBar;
