import jwtAxios from "../util/jwtutil";
import { API_SERVER_HOST } from "./todoApi";


const host=`${API_SERVER_HOST}/api/cart`


export const getCartItems=async()=>{
    const res=await jwtAxios.get(`${host}/items`)

    return res.data
}

export const postChangeCart=async(cartItems)=>{
const res=await jwtAxios.post(`${host}/change`,cartItems)
return res.data


}