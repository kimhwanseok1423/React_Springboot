import React, { useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import { getAccessToken, getMemberWithAccessToken } from '../../api/kaokaoApi';
import { useDispatch } from 'react-redux';
import { login } from '../../slices/loginSlice';
import useCustomLogin from '../../hooks/useCustomLogin';
function KakaoRedirectPage(props) {

    const [searchParams]=useSearchParams()
const{moveToPath}=useCustomLogin()
    const authCode=searchParams.get('code')
const dispath=useDispatch()
    useEffect(()=>{
        getAccessToken(authCode).then(accessToken=>{
        
            getMemberWithAccessToken(accessToken).then(result=>{

                console.log("--------------------")
                console.log(result)
    dispath(login(result))

    if(result && result.social){
        moveToPath('/member/modify')
    }else{
        moveToPath('/')
    }
            })
    
            
        })
    },[authCode])


    //authCode가 바뀌면 getAccessToken을 할꺼다 
    return (
        <div>
            <div>Kakao Login Redirect</div>
            <div>{authCode}</div>
        </div>
    );
}

export default KakaoRedirectPage;