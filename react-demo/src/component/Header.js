import React from "react";
import { Link } from "react-router-dom";
import Navi from "./Navi";
import "../css/Header.css"

function Header(props) {
    
    

    return (
        <div className="header-layout-out">
            <div className="header-layout-in">
                <div className="header-logo-layout">
                    <Link to={"http://localhost"}>
                        <img src="http://localhost/api/beer/img/logo.png" width={"105px"} height={"80px"} className="header-logo"/>
                    </Link>
                </div>
                <div className="header-navi-layout">
                    <Navi />
                </div>
            </div>    
        </div>
    );
}

export default Header;