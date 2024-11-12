import React from 'react';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';

const BasicMenu = () => {
    const loginState = useSelector(state => state.loginSlice);

    return (
        <nav id="navbar" className="flex bg-gradient-to-r from-blue-600 to-purple-600 shadow-lg">
            <div className="w-4/5">
                <ul className="flex p-4 space-x-8 text-white font-semibold">
                    <li className="text-lg hover:text-yellow-300 transition duration-200">
                        <Link to={'/'}>메인</Link>
                    </li>
                    <li className="text-lg hover:text-yellow-300 transition duration-200">
                        <Link to={'/about'}>About</Link>
                    </li>

                    {loginState.email && (
                        <>
                            <li className="text-lg hover:text-yellow-300 transition duration-200">
                                <Link to={'/todo/'}>게시판</Link>
                            </li>
                            <li className="text-lg hover:text-yellow-300 transition duration-200">
                                <Link to={'/products/'}>상품</Link>
                            </li>
                        </>
                    )}
                </ul>
            </div>

            <div className="w-1/5 flex justify-end items-center bg-gradient-to-r from-orange-400 to-red-400 p-4">
                {!loginState.email ? (
                    <Link to={'/member/login/'} className="text-white text-sm px-4 py-2 rounded-lg bg-green-500 hover:bg-green-600 transition duration-200">
                        Login
                    </Link>
                ) : (
                    <Link to={'/member/logout/'} className="text-white text-sm px-4 py-2 rounded-lg bg-red-500 hover:bg-red-600 transition duration-200">
                        Logout
                    </Link>
                )}
            </div>
        </nav>
    );
};

export default BasicMenu;
