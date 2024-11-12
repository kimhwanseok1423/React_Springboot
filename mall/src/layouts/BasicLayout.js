import React from 'react';
import BasicMenu from '../components/menus/BasicMenu';
import CartComponent from '../components/menus/CartComponent';

function BasicLayout({ children }) {
    return (
        <>
            <BasicMenu />
            <div className="bg-gray-100 my-5 w-full flex flex-col md:flex-row md:space-x-6">
                <main className="bg-blue-100 shadow-lg rounded-lg md:w-4/5 lg:w-3/4 p-6">
                    {children}
                </main>
                
                <aside className="bg-gray-200 shadow-md rounded-lg md:w-1/5 lg:w-1/4 p-6 flex flex-col items-center">
                    <h1 className="text-2xl font-semibold text-gray-800 mb-4">Cart</h1>
                    <CartComponent />
                </aside>
            </div>
        </>
    );
}

export default BasicLayout;
