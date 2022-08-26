import Nav from "react-bootstrap/Nav";

const LogOut = ({className})=>{
    const onClick = (e)=>{e.preventDefault(); localStorage.clear();window.location.href="/";}
    return <Nav.Link  className={className} onClick={onClick}>Sign out</Nav.Link>
}
export default LogOut
