import React from 'react';
import BasicLayout from '../../layouts/BasicLayout';
import { Outlet, useNavigate } from 'react-router-dom';

function IndexPage(props) {
    const navigate = useNavigate();

    return (
        <BasicLayout>
            <div className="flex flex-col items-center">
                <h2 className="text-4xl font-bold text-gray-800 mb-4">
                    Products Menu
                </h2>
                
                <div className="flex space-x-4 mb-6">
                    <button
                        className="text-lg font-semibold bg-blue-600 text-white py-2 px-4 rounded-lg shadow-md hover:bg-blue-700 transition duration-300"
                        onClick={() => navigate('list')}
                    >
                        LIST
                    </button>
                    <button
                        className="text-lg font-semibold bg-green-600 text-white py-2 px-4 rounded-lg shadow-md hover:bg-green-700 transition duration-300"
                        onClick={() => navigate('add')}
                    >
                        ADD
                    </button>
                </div>

                <div className="w-full">
                    <Outlet />
                </div>
            </div>
        </BasicLayout>
    );
}

export default IndexPage;
