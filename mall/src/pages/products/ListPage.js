import React from 'react';
import ListComponent from '../../components/products/ListComponent';

function ListPage(props) {
    return (
        <div className="p-6 w-full bg-gray-100 rounded-lg shadow-md">
            <h1 className="text-3xl font-bold text-center text-blue-700 mb-6">
                Products List Page
            </h1>
            <ListComponent />
        </div>
    );
}

export default ListPage;
