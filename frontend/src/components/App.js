import StaticNavBar from "./StaticNavBar";
import {Routes, Route} from "react-router-dom";
import SignIn from "./SignIn";
import SignUp from "./SignUp";
import HomePage from "./HomePage";
import UpdateAdvert from "./UpdateAdvert";
import NewAdvert from "./NewAdvert";
import AdvertView from "./AdvertView";

function App() {
    return (
            <nav>
                <StaticNavBar/>
                <div >
                <Routes>
                    <Route path="/sign/in" exact element={<SignIn/>}/>
                    <Route path="/" exact element={<HomePage/>}/>
                    <Route path="/sign/up" exact element={<SignUp/>}/>
                    <Route path="/update/:code" exact element={<UpdateAdvert/>}/>
                    <Route path="/new" exact element={<NewAdvert/>}/>
                    <Route path="/advert/:code" exact element={<AdvertView/>}/>
                </Routes>
                </div>
            </nav>
    );
}

export default App;
