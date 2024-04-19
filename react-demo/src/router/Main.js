import React from 'react';
import { useSelector } from 'react-redux';
import "../css/Main.css"

function Main(props){

    return (
        <div className='main-layout-out'>
            <div className='main-layout-in'>
                {/* <img src='http://localhost/api/beer/img/main_text1.png' alt='Gangnenug' className='main-text1'/> */}
                {/* <img src='http://localhost/api/beer/img/main_text2.png' alt='Gangnenug' className='main-text2'/> */}
                {/* <img src='http://localhost/api/beer/img/main_text3.png' alt='Gangnenug' className='main-text3'/> */}
                {/* <img src='http://localhost/api/beer/img/main_text4.png' alt='Gangnenug' className='main-text4'/> */}
                <img src='http://localhost/api/beer/img/main_img1.png' alt='not' className='main-img1'/>

                <div className='main-text1'>Gangnenug</div>
                <div className='main-text2'>Budnamu</div>
                <div className='main-text3'>Brewery</div>
                <div className='main-text4'>Budnamu Brewery<br/> Time to showcase Korean beer</div>
            </div>
        </div>
    )
}

export default Main;