import React from 'react';
import { API_SERVER_HOST } from '../../api/todoApi';

const host = API_SERVER_HOST;

function CartItemComponent({ cino, pname, price, pno, qty, imageFile, changeCart, email }) {
  const handleClickQty = (amount) => {
    changeCart({ email: email, cino: cino, pno: pno, qty: qty + amount });
  };

  return (
    <li key={cino} className="border border-gray-200 rounded-lg shadow-lg p-4 m-4 bg-white flex flex-col md:flex-row items-center">
      <div className="w-full md:w-1/3 p-2 flex justify-center">
        <img className="w-32 h-32 object-cover rounded-lg" src={`${host}/api/products/view/s_${imageFile}`} alt={`${pname} 이미지`} />
      </div>
      <div className="w-full md:w-2/3 p-4">
        <div className="text-xl font-semibold text-gray-800">{pname}</div>
        <div className="text-sm text-gray-500">Cart Item No: {cino} | Pno: {pno}</div>
        <div className="text-lg font-medium text-gray-800 mt-2">Price: {price} 원</div>

        <div className="flex items-center mt-4">
          <span className="text-gray-700 mr-2">Qty:</span>
          <button
            className="m-1 p-2 text-lg bg-blue-500 text-white rounded-full hover:bg-blue-600"
            onClick={() => handleClickQty(1)}
          >
            +
          </button>
          <span className="px-4 text-lg font-medium text-gray-800">{qty}</span>
          <button
            className="m-1 p-2 text-lg bg-blue-500 text-white rounded-full hover:bg-blue-600"
            onClick={() => handleClickQty(-1)}
          >
            -
          </button>
        </div>

        <div className="flex justify-between items-center mt-4">
          <span className="font-bold text-gray-900 text-xl">{qty * price} 원</span>
          <button
            className="text-white bg-red-500 hover:bg-red-600 font-semibold py-2 px-4 rounded-lg"
            onClick={() => handleClickQty(-1 * qty)}
          >
            Remove
          </button>
        </div>
      </div>
    </li>
  );
}

export default CartItemComponent;
