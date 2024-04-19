import React from "react";

import "../css/Footer.css";

function Footer(props) {


    return (
        <div className="Footer-layout-out">
            <div className="Footer-layout-in">
                <div className="Footer-name1">
                    Budnamu
                </div>
                <div className="Footer-name2">
                    Brewery
                </div>
                <div className="Footer-address">
                    00로 0000, South Korea 000 00
                </div>
                <div className="Footer-tel">
                    <span className="Footer-text1">TEL : </span>000-000-000
                </div>
                <div className="Footer-email">
                    <span className="Footer-text1">Email : </span>0000@0000.00
                </div>
                <div className="Footer-hr">
                </div>
            </div>        
        </div>
    );
}

export default Footer;