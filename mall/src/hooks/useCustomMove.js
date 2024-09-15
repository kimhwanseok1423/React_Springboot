import {createSearchParams, useNavigate, useSearchParams} from "react-router-dom";

const getNum=(param,defaultValue)=>{
    if(!param){
        return defaultValue
    }
    return parseInt(param)
}
const useCustomMove=()=>{
    const navigate=useNavigate()

    const [queryParams]=useSearchParams()

    const page=getNum(queryParams.get('page'),1)
    const size=getNum(queryParams.get('size'),10)

    //문자열로만들어야함 page=3&size=10 이런식으로 만들려면 문법필요함

    const queryDefault=createSearchParams({page,size}).toString()

    const moveToList=(pageParam)=>{
        let queryStr= ""

        if(pageParam) {

            const page=getNum(queryParams.get('page'),1)
            const size=getNum(queryParams.get('size'),10)

            queryStr=createSearchParams({page,size}).toString()
        }
else{
    queryStr=queryDefault
}


        navigate({pathname:'../list',search:queryDefault})
    }

const moveToModify=(tno) =>{
    navigate({
        pathname:`../modify/${tno}`,
        search:queryDefault
    })
}


    return {moveToList,moveToModify,page,size}


}

export default useCustomMove;