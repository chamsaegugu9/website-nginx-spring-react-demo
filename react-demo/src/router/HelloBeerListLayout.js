import React, { useEffect, useMemo, useRef, useState } from 'react';
import DisplayListItem from '../component/DisplayListItem';

import "../css/HelloBeerListLayout.css";
import DisplayBoard from '../component/DisplayBoard';

function HelloBeerListLayout(props) {
    const [page, setPage] = useState(0);
    //const [beerData, setBeerData] = useState([]);
    const beerData = useRef([]);

    const displayBoard = [
        {data:["100%","displayBoard_img1.png","Local beer"],pattern:"wibe", width:1280, speed:150, start:100},
        {data:["displayBoard_img2.png","Local","Produce"],pattern:"iwbe", width:1280, speed:100, start: 50},
        {data:["Variety of","displayBoard_img3.png","Wild yeast"],pattern:"wibe", width:1280, speed:200, start: 250},
    ];
    useEffect(()=>{
        fetch("http://localhost/api/beer")
        .then(response=>{
            return response.json();
        })
        .then(result=>{
            let data = result.data.content;
            data.forEach((e, i) => {
                e.p = i;
            });
            //setBeerData((d)=>{return [...d, ...result.data.content]});
            beerData.current.push(...data);
        })
        .then(()=>{
            setPage((p)=>{return 0;});
            setPage((p)=>{return p + 1;});
        })
        .catch(e=>{
            console.log(e);
        });
    },[]);

    const getData = ()=> {
        fetch(`http://localhost/api/beer?page=${page}`)
        .then(response=>{
            return response.json();
        })
        .then(result=>{
            let data = result.data.content;
            if(data.length != 0) {
                data.forEach((e, i) => {
                    e.p = i;
                });
                // setBeerData((d)=>{return [...d, ...data]});
                beerData.current.push(...data);
            }
            return data.length;
        })
        .then(result=>{
            if(result != 0 ) {
                setPage((p)=>{return p + 1});
            }
        })
        .catch(e=>{
            console.log(e);
        });

    }
    const helloGet = ()=> {
        console.log(beerData.current.content);
    }
    return (
        <div className='HelloBeerListLayout-layout-out'>
            <DisplayBoard props={displayBoard[0]}/>
            <DisplayBoard props={displayBoard[1]}/>
            <DisplayBoard props={displayBoard[2]}/>
            <button onClick={()=>{getData()}}>

            </button>
            <div className='HelloBeerListLayout-layout-in'>
                <div className='HelloBeerListLayout-wrap'>
                    <div className='HelloBeerListLayout-text1'>
                        Budnamu Sampler
                    </div>
                    <div className='HelloBeerListLayout-text2'>
                        강릉의 오랜 역사를 간직한 막걸리 양조장에서 쌀, 국화, 솔잎, 오죽등<br/> 강릉의 색채가 짙은 재료를 이용해 만든 맥주 입니다.
                    </div>
                    <img src='http://localhost/api/beer/img/beerList_img1.png'
                    className='HelloBeerListLayout-img1'
                    />
                </div>
                {
                    beerData.current.map((s,i)=>{
                        return <DisplayListItem key={i+ " " + s.id} props={s} />
                    })
                }
            </div>
        </div>
    );
}

export default HelloBeerListLayout;