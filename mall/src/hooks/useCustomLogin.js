import { useDispatch, useSelector } from "react-redux";
import { Navigate, useNavigate } from "react-router-dom"
import { loginPostAsync, logout } from "../slices/loginSlice";



 export const useCustomLogin= ()=>{
    const navigate=useNavigate();
    const dispatch=useDispatch()
    const loginState=useSelector(state=>state.loginSlice)

    const isLogin=loginState.email ? true:false //로그인 여부

    const doLogin=async (loginParam) =>{
        const action=await dispatch(loginPostAsync(loginParam))
        return action.payload
    }


    const doLogout=()=>{
        dispatch(logout())
    }


    const moveToPath=(path)=>{  //---- 페이지이동
        navigate({pathname : path},{replace:true})
    }

    const moveToLogin=()=>{   // -----로그인페이지 이동
        navigate({pathname: '/member/login'},{replace:true})
    }

    const moveToLoginReturn=()=>{     //--------------- 로그인 페이지 이동 컴포넌트
        return <Navigate replace to="/member/login"/>
    }


    return {loginState,isLogin,doLogin,moveToLogin,moveToPath,moveToLoginReturn,doLogout}
}

export default useCustomLogin;