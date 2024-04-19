import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setlogin, setlogout } from "../store/LoginSlice";
import "../css/Navi.css";
import { Link, useLocation } from "react-router-dom";

function Navi(props) {
    const navi_text1 = ["ABOUT", "SHOP", "PROJECT"];
    const navi_text2 = ["LOGIN", "LOGOUT"];
    const navi_text3 = ["http://localhost/about", "http://localhost/beer/list", "http://localhost/project"];
    const navi_text3_ = ["about", "beer", "project"];

    const loginCheck = useSelector((state)=>{return state.login});
    const dispatch = useDispatch();

    const location = useLocation();

    useEffect(()=> {
        let check = fetch("http://localhost/api/login",{
            method: "GET",
        })
        .then(response=>{
            return response.json();
        })
        .then(result=>{
            if(result.httpStatus == "BAD_REQUEST") {
                dispatch(setlogout());
            }
            else if(result.httpStatus == "OK") {
                dispatch(setlogin(result.data));
            }
            else {
                dispatch(setlogout());
            }
        })
        .catch(error=>{
            console.log(error);
        });
        console.log(check);
    },[])

    return (
        <React.Fragment>
            <div className="navi-layout-out">
                <div className="navi-layout-in1">
                    {
                        navi_text1.map((s, i)=>{
                            return (
                                <Link to={navi_text3[i]} key={s} className={"navi-link-text1 b_s" + (location.pathname.split("/")[1] === navi_text3_[i] ? " navi-active1": "")}>
                                    {s}
                                </Link>
                            )
                        })
                    }
                </div>
                <div className="navi-layout-in2">
                    {
                        loginCheck ? (<Link className="navi-link-text2 b-s">{navi_text2[0]}</Link>) : (<Link className="navi-link-text2 b-s">{navi_text2[1]}</Link>)
                    }
                </div>
            </div>
        </React.Fragment>
    );
}

export default Navi;