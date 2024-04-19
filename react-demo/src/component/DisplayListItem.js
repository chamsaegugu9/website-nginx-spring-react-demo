import React from "react";
import { Link } from "react-router-dom";
import "../css/DisplayListItem.css"

function DisplayListItem(props) {
    const data = props.props;
    const p = data.p % 2 == 1 ? 1 : 2;
    const linkText = "View Detail";
    return (
        <div className="DisplayListItem-layout-out">
            <div className="DisplayListItem-layout-in">
                <img className={`DisplayListItem-img${p}`} src={`http://localhost/api/beer/img/${data.filepath}`}/>
                <div className={`DisplayListItem-name${p}`}>{data.name}</div>
                <div className={`DisplayListItem-comment${p}`}>{data.comment}</div>
                <Link to={`http://localhost/beer/detail/${data.id}`} className={`DisplayListItem-link${p}`}>{linkText}</Link>
            </div>    
        </div>
    );
}

export default React.memo(DisplayListItem);