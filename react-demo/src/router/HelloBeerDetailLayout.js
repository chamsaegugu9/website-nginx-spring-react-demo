import React, { useEffect, useRef, useState } from 'react';
import { useParams } from 'react-router';

import "../css/HelloBeerDetailLayout.css";

function HelloBeerDetailLayout(props) {
    const beerData = useRef({});
    const [on, setOn] = useState(false);
    const param = useParams("id");
    
    useEffect(()=>{
        fetch(`http://localhost/api/beer/${param.id}`)
        .then((response)=>{
            return response.json();
        })
        .then((result)=>{
            beerData.current = result.data;
        })
        .then(()=>{
            setOn((o)=>{
                return true;
            })
        })
        .catch((e)=>{
            console.log(e);
        })
    },[]);
    useEffect(()=>{
        if(on == true) {
            console.log(beerData);
        }
    },[on])
    return (
        <div className='HelloBeerDetailLayout-layout-out'>
            <div className='HelloBeerDetailLayout-layout-in'>
                <div className='HelloBeerDetailLayout-wrap-name'>
                    {
                        // beerData.current.name?.split(" ").map((e,i)=>{
                        //     return (
                        //         <React.Fragment>
                        //             {e}<br/>
                        //         </React.Fragment>
                        //     );
                        // })
                        "Hello World"?.split(" ").map((e,i)=>{
                            return (
                                <React.Fragment key={`i ${e}`}>
                                    <span className={`HelloDetailLayout-name${i%2==0?1:2}`}>{e}</span><br/>
                                </React.Fragment>
                            );
                        })
                    }
                </div>
                <img src={`http://localhost/api/beer/img/${beerData.current.filepath}`}
                className='HelloBeerDetailLayout-img'
                />
                <div className='HelloBeerDetailLayout-wrap-alchol'>
                    <span className='HelloBeerDetailLayout-alchol1'>
                        ALCHOL
                    </span>
                    <span className='HelloBeerDetailLayout-alchol2'>
                        {`${beerData.current.alchol} %`}
                    </span>
                </div>
                <div className='HelloBeerDetailLayout-wrap-comment'>
                    <span className='HelloBeerDetailLayout-comment'>
                        {/* {beerData.current.comment} */}
                        제품명에 들어간 '미노리'는 강릉시 사천면 미노리에서 따왔다. 미노리에서 수확한 쌀 을 40%이상 사용해 만든 맥주로 한국적인 맥주 중 하나다. 우리나라 전통 술을 빚을 때 고두밥을 짓는 방식을 응용했다. 깔끔하고 담백해 마시기 편안한데다가 귤향과 상큼함 을 느낄 수 있다.
                    </span>
                </div>
                <div className='HelloBeerDetailLayout-wrap-spec'>
                    spec
                </div>
            </div>    
        </div>
    );
}

export default HelloBeerDetailLayout;