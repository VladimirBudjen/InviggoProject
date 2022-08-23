import StaticNavBar from "./StaticNavBar";
import {Routes, Route} from "react-router-dom";
import SignIn from "./SignIn";
import SignUp from "./SignUp";
import HomePage from "./HomePage";

function App() {
    return (
            <nav>
                <StaticNavBar/>
                <div >
                <Routes>
                    <Route path="/sign/in" exact element={<SignIn/>}/>
                    <Route path="/" exact element={<HomePage/>}/>
                    <Route path="/sign/up" exact element={<SignUp/>}/>
                </Routes>
                </div>
            </nav>
    );
}

export default App;
