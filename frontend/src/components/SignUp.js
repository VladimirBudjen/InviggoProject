import {useState} from "react";
import axios from "../api/axios"
import { useNavigate } from "react-router-dom";


const SignUp = () => {

    const [username, setUserName] = useState("")
    const [password, setPassword] = useState("")
    const [phone, setPhone] = useState("")

    const navigate = useNavigate();

    const signIn = () => {
        axios.post("/signup", {
            username: username,
            password: password,
            phone:phone
        }).then(r => {
            alert(r.data)
            navigate("/sign/in")
        }).catch(e=>alert(e?.response?.data))
    }

    return (
        <section className="vh-100">
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div className="card shadow-2-strong" style={{borderRadius: "1rem"}}>
                            <div className="card-body p-5 text-center">
                                <h3 className="mb-5">Sign up</h3>
                                <div className="form-outline mb-4">
                                    <input onChange={(e) => setUserName(e.target.value)}
                                           value={username}
                                           type="text" id="username" className="form-control form-control-lg"/>
                                    <label className="form-label" htmlFor="username">Username</label>
                                </div>
                                <div className="form-outline mb-4">
                                    <input type="password" id="password"
                                           onChange={(e) => setPassword(e.target.value)}
                                           value={password}
                                           className="form-control form-control-lg"/>
                                    <label className="form-label" htmlFor="password">Password</label>
                                </div>
                                <div className="form-outline mb-4">
                                    <input onChange={(e) => setPhone(e.target.value)}
                                           value={phone}
                                           type="text" id="phone" className="form-control form-control-lg"/>
                                    <label className="form-label" htmlFor="phone">Phone number</label>
                                </div>
                                <hr/>
                                <button className="btn btn-primary btn-lg btn-block" type="submit"
                                        onClick={signIn}>Submit
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default SignUp
