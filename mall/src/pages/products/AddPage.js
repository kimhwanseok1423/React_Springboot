import React from 'react';
import AddComponent from '../../components/products/AddComponent';

function AddPage(props) {
    return (
        <div className="p-6 w-full bg-gray-100 rounded-lg shadow-md">
            <h1 className="text-3xl font-bold text-center text-green-700 mb-6">
                Products Add Page
            </h1>
            <AddComponent />
        </div>
    );
}

export default AddPage;