import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getCartItems, postChangeCart } from "../api/cartApi";

export const getCartItemsAsync=createAsyncThunk('getCartItemsAsync',()=>{
    return getCartItems()
})


// extrareducer로 하기위해 씀

export const postChangeAsync=createAsyncThunk('postChangeAsync',(param)=>{

    return postChangeCart(param)
})


const initState=[]

const cartSlice=createSlice({
    name:'cartSlice',
    initialState:initState,

    extraReducers:(builder)=>{
        builder.addCase(getCartItemsAsync.fulfilled,(state,action)=>{

            console.log("etCartItemsAsync.fulfilled")
            console.log(action.payload)
            return action.payload
        })
        .addCase( postChangeAsync.fulfilled,(state,action)=>{

            console.log("postChangeAsync.fulfilled")
            console.log(action)

            return action.payload
        })




    }
})

export default cartSlice.reducer;