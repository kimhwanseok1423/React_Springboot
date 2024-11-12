import { useDispatch, useSelector } from "react-redux"
import { getCartItemsAsync, postChangeAsync } from "../slices/cartSlice"


const useCustomCart=()=>{

    const cartItems=useSelector(state=>state.cartSlice)

    const dispatch=useDispatch()

    const refreshCart=()=>{
        dispatch(getCartItemsAsync())
        //카드 아이템 가져올떄
    }
    const changeCart=(param)=>{
        dispatch(postChangeAsync(param))
        // 카트 아이템 변경할때 
    
    }

    return {cartItems,refreshCart,changeCart}
}

export default useCustomCart;