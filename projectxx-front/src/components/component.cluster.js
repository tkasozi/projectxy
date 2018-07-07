import React from 'react';
import './cluster.css';

class Node extends React.Component {
    render() {
        return (
            <svg width="250" height="125">
                <defs>
                    <clipPath id="circleView">
                        <circle cx="125" cy="62.5" r="62.5" fill="#FFFFFF" /> 
                    </clipPath>
                </defs>
                <image clipPath="url(#circleView)" width="250" height="125" xlinkHref={this.props.src}  />
            </svg>
        );
    }
}
class Cluster extends React.Component {

    constructor(props) {
        super(props);
        this.createLine = this.createLine.bind(this);
    }

    componentDidMount() {
        //getBoundingClientRect()
        for(let el of document.getElementById("cluster").children){
            //console.log(el.getBoundingClientRect());
        }
        let elm1 = document.getElementById("cluster").children[0].getBoundingClientRect(), elm2 = document.getElementById("cluster").children[1].getBoundingClientRect();
        console.log(elm1 , elm2, Math.sin(180));

        //this.createLine(elm1.x, elm2.x, elm1.y, elm2.y);
    }

    createLine(x1, x2, y1, y2) {

        let dis, xMid, yMid, slopeInRadian, slopeInDegrees, lineDiv;

        dis = Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
        xMid = (x1 + x2) / 2;
        yMid = (y1 + y2) / 2;

        slopeInRadian = Math.atan2((y1 - y2), (x1 - x2));
        slopeInDegrees = (slopeInRadian * 180) / Math.PI;

        lineDiv = document.getElementById("line");
        lineDiv.style.position = "absolute";

        lineDiv.style.width = dis +"px";
        lineDiv.style.top = yMid;
        lineDiv.style.left = xMid ;//- (dis / 2);
        lineDiv.style.transform = "rotate(" + slopeInDegrees + "deg)";
        lineDiv.style.backgroundColor = "black";
        
        //lineDiv.style.height = "2px";
        lineDiv.style.border = "solid 1px #000"
        console.log("after: ", lineDiv.getBoundingClientRect(), lineDiv, dis);

    }
    render() {
        return ( 
             <div id = "cluster" >
                <Node src="https://lh3.googleusercontent.com/-ws0FTRT3p1Q/VTriZON-SeI/AAAAAAAACIo/WJPGa14Mnrgs8-gGOUC8LuuwKuUKWoGkwCEwYBhgL/w140-h139-p/b4604ab9-80c2-47ba-8a2f-1742bd211c1c"/>
          
                <Node  src="https://lh3.googleusercontent.com/_ffvtlQR0fwub0VZvrlD2pRISwuUP75-KJWXg3Fl24FRyeK_C41UoFyMrS2oG58NBbmVtdZ5fOMT20eOSGlMuTs7ouSOYiSPxHo6xBcQ3rCI-PflR6HsS52vvN6GLWuDdZJAPeGXtP3_GE0H5M03uOumZeWu2LD1y0w1CmPtkHAZljmvlv7Nv4J13JDE0EnnevoMcKoZxvrycGcExHsCtZCV-BVO3RsteOTP09TyF46uKBq1rnAcwEjypCwzJEXlQc0RSahBn0HLYspKPJmYOiTU1-lMJS0_RknF5EFRBiJ-JowYLkcG8alFCxml8SlGMzdtDWL9Uxwhqxz0gWM3YJ4pe6aicGqTL1_5a1uMx0WfNqaIQyACXAjm4sogyksDJBrXVWFyfFk-yL8J00SxAr5OCPE2oxQIQRNAeQR1hfkutlXg_8Euz5h7TsyCeRRMAiq8ZvGub0jVZUNWBmwHo4L6x0SXGGcAjTgQDthK_SnwWZHhvmIVKp86CDN7aSz25pVqJc2VpfKlM9DcWZ4s3kIXrD9K-c3VX0KrwJiz0ioTliK1ckBuwR01GKfFU1JgsQxdUGNSR8X5Q0fkqJcU2DXNQdVIh2oE3hyRAg8=w383-h680-no"/>
                
             </div>
        )
    }
}

export default Cluster;