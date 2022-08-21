import StaticNavBar from "./StaticNavBar";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import SignIn from "./SignIn";
import Home from "./Home";

function App() {
  return (
      <div style={{backgroundColor: "#85b2ff"}}>
          <StaticNavBar />
          <BrowserRouter style={{backgroundColor: "#85b2ff"}}>
              <Routes>
                <Route path="/" exact element={<Home/>}/>
                <Route path="/sign/in" exact element={<SignIn/>}/>
              </Routes>
          </BrowserRouter>
      </div>
  );
}

export default App;
