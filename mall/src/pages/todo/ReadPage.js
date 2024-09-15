import React from 'react';
import { createSearchParams, useNavigate, useParams, useSearchParams } from 'react-router-dom';
import ReadComponent from '../../todo/ReadComponent';

function ReadPage(props) {

    const {tno}=useParams()
const navigate =useNavigate();

const [queryParams]= useSearchParams()

const page=queryParams.get('page') ? parseInt(queryParams.get('page')) : 1
const size=queryParams.get('size') ? parseInt(queryParams.get('size')) : 10

const queryStr=createSearchParams({page:page,size:size}).toString();






const moveToModify=(tno)=>{
    navigate({pathname:`/todo/modify/${tno}`,
        search:queryStr
    })}


    const moveToList=()=>{
        navigate({pathname:`/todo/list`,
            search:queryStr
    })
}
    console.log(tno) 


    return (
        <div className="font-extrabold w-full bg-white mt-6">
<div className="text-2xl "> 
    Todo Read Page Component {tno} </div>
<ReadComponent tno={tno}></ReadComponent>
</div>

    );
}

export default ReadPage ;