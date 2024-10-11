import React from 'react';

import { useParams } from 'react-router-dom';
import ModifyComponents from '../../components/products/ModifyComponents';

function ModifyPage(props) {
    const {pno}=useParams()
    return (
        <div className='p-4 w-full bg-white'>
        <div className='text-3xl font-extrabold'>
        Products Modify Page


        </div>
        <ModifyComponents pno={pno}/>
        </div>
    );
}

export default ModifyPage;