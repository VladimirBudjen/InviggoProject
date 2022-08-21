import {Component} from "react";
import axios from "../api/axios";
import {Link} from "react-router-dom";



export default class SignIn extends Component {
    state = {
        username:"",
        password:""
    }

    signIn = () =>{
        axios.post("/auth/login", {
            username: this.state.username,
            password: this.state.password
        }).then(r => {localStorage.setItem("JWT",r.data) })
    }

    render() {
        return(
        <section className="vh-100">
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div className="card shadow-2-strong" style={{borderRadius: "1rem"}}>
                            <div className="card-body p-5 text-center">
                                <h3 className="mb-5">Sign in</h3>
                                <div className="form-outline mb-4">
                                    <input onChange={(e) => this.setState({ username: e.target.value })}
                                           value={this.state.username}
                                           type="email" id="username" className="form-control form-control-lg"/>
                                    <label className="form-label" htmlFor="username">Username
                                        <br/>
                                    </label>
                                </div>
                                <div className="form-outline mb-4">
                                    <input type="password" id="typePasswordX-2"
                                           onChange={(e) => this.setState({ password: e.target.value })}
                                           value={this.state.password}
                                           className="form-control form-control-lg"/>
                                    <label className="form-label" htmlFor="typePasswordX-2">Password</label>
                                </div>
                                <hr/>
                                <button className="btn btn-primary btn-lg btn-block" type="submit"
                                        onClick={this.signIn}>Submit
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        )
    };
}
