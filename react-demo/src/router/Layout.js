import React, { useEffect } from 'react';
import { Outlet, Link } from 'react-router-dom';

import '../css/Layout.css';
import Header from "../component/Header";
import Footer from "../component/Footer";

function Layout(props) {


  useEffect(() => {

  }, []);

  return (
    <div className="layout">
      <Header></Header>
      <Outlet />
      <Footer></Footer>
    </div>
  );
}

export default Layout;
