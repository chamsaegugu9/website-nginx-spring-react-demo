import React, { useCallback, useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import "../css/DisplayBoard.css"

function DisplayBoard(props) {
    const container = useRef([[],[],[]]);
    const [index, setIndex] = useState(0);
    const [setting, setSetting] = useState(0);
    const [intervallist, setIntervalList] = useState([]);
    const data = props.props;
    const pattern = data.pattern.split('');
    const width = data.width;
    const speed = data.speed || 100;
    const start = data.start || 0;
    
    const setRef = (i,j) => (e) => {
        container.current[i][j] = e;
    };
    const setting1 = ()=>{
        setSetting((b)=>{return 1});
    }

    const setting2 = ()=>{
        setSetting((b)=>{return 2});        
    }
    useEffect(()=>{
        if(setting == 1) {
            const offsetWidth = container.current[0].reduce((p,c)=>{
                return p + c.offsetWidth;
            }, 0);
            if(offsetWidth != 0) {
                setIndex((i)=>{return Math.ceil(width/offsetWidth) + 1});
            }
        }
        else if(setting == 2) {
            setSetting((b)=>{return 3});
            const tmp = container.current[1][0];
            
            const ew = tmp.offsetWidth;

            const rp = /-?[\d.]+/;
            for(let i=0;i<index;i++) {
                let j = (i-1) < 0 ? index-1 : (i-1);
                let tmp1 = container.current[1][i];
                let tmp2 = container.current[1][j];

                if(i==0) {
                    tmp1.style.left = `${start}px`;
                }
                else {
                    let tmp2p = Number(tmp2.style.left.match(rp)[0]) + Number(ew);
                    tmp1.style.left = `${tmp2p}px`;
                }

                // let speed = 500;
                let lastTime = null;
                let ani = ()=>{
                    if(!lastTime) {
                        lastTime = new Date().getTime();
                    }
                    const currentTime = new Date().getTime();
                    const deltaTime = currentTime - lastTime;
                    const tmp1p = Number(tmp1.style.left.match(rp)[0]);
                    
                    if(deltaTime > (1000 / 144)) {
                        tmp1.style.left = `${(tmp1p - (speed * deltaTime / 1000))}px`;
                        lastTime = currentTime;
                    }

                    requestAnimationFrame(ani);

                    if(tmp2.style.left != '') {
                        
                        if(Number(ew) + tmp1p <= 0) {
                            const p = Number(tmp2.style.left.match(rp)[0]) + Number(ew);
                            tmp1.style.left = `${p}px`;
                        }
                    }
                }
                ani();
                setIntervalList((v)=>{return [...v, ani]});
                // let intervalid = setInterval(()=>{
                //     let element1 = tmp1;
                //     let element2 = tmp2;

                //     let tmp1p = Number(tmp1.style.left.match(rp)[0]);
                //     tmp1.style.left = `${(tmp1p - 1.0)}px`;

                // },33);
                // setIntervalList((v)=>{return [...v, intervalid]});
            }

        }
        return ()=>{
            
        };
    },[setting]);
    return (
        <div className="DisplayBoard-layout-out">
            <div className="DisplayBoard-layout-in">
                {
                    index == 0 ?
                    pattern.map((e,i)=>{
                        if(e=='b') {
                            return <div className="DisplayBoard-text-b" key={`${i} ${e}`} ref={setRef(0,i)}>
                                {data.data[i]}
                            </div>
                        }
                        else if(e=='w') {
                            return <div className="DisplayBoard-text-w" key={`${i} ${e}`} ref={setRef(0,i)}>
                                {data.data[i]}
                            </div>
                        }
                        else if(e=='i') {
                            return <img className="DisplayBoard-img" key={`${i} ${e}`} ref={setRef(0,i)} 
                            src={`http://localhost/api/beer/img/${data.data[i]}`} 
                            />
                        }
                        else if(e=='e') {
                            if(setting == 0) {
                                setting1();
                            }
                        }
                    }) :
                    (
                        Array.from({length:index}).map((_,i)=>{
                            return (
                                <div  key={index + " " + i}
                                className="DisplayBoard-wrap"
                                ref={setRef(1,i)}
                                >
                                    {
                                        pattern.map((e,i1)=>{
                                            if(e=='b') {
                                                return <div className="DisplayBoard-text-b" key={`${i1} ${e}`}>
                                                    {data.data[i1]}
                                                </div>
                                            }
                                            else if(e=='w') {
                                                return <div className="DisplayBoard-text-w" key={`${i1} ${e}`}>
                                                    {data.data[i1]}
                                                </div>
                                            }
                                            else if(e=='i') {
                                                return <img className="DisplayBoard-img" key={`${i1} ${e}`} 
                                                src={`http://localhost/api/beer/img/${data.data[i1]}`} />
                                            }
                                            if((i + 1)==index && setting == 1) {
                                                setting2();
                                            }
                                        })
                                    }
                                </div>
                            )
                        })
                    )
                }
            </div>    
        </div>
    );
}

export default React.memo(DisplayBoard);