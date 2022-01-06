import React from "react";
import "../index.css";
import App from "../App";
import Login from "./Login";
import SignUp from "./SignUp";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

class AppRouter extends React.Component {
    render() {
        return (
            <div>
            <Router>
                <div>
                <Routes>
                    <Route exact={true} path={"/login"} element={<Login/>}/>
                    <Route exact={true} path={"/"} element={<App/>}/>
                    <Route exact={true} path={"/signup"} element={<SignUp/>}/>
                </Routes>
                </div>
            </Router>
            </div>
        );
    }
}

export default AppRouter;