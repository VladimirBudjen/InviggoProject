import Nav from "react-bootstrap/Nav";
import {useNavigate} from "react-router-dom";

const LogOut = ()=>{
    let navigate = useNavigate()
    const onClick = (e)=>{e.preventDefault(); localStorage.clear();navigate("/")}
    return <Nav.Link  className={'nav-btn'} onClick={onClick}>Sign out</Nav.Link>
}
export default LogOut
