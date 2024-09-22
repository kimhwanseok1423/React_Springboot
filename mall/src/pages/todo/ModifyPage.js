import React from 'react';
import ModifyComponent from '../../todo/ModifyComponent';
import { useParams } from 'react-router-dom';

function ModifyPage() {

    const {tno}=useParams()
    return (
        <div className=' text-3xl font-extrabold'>
            Todo Modify Page

            <div>
                <ModifyComponent tno={tno}></ModifyComponent>
            </div>
        </div>
    );
}

export default ModifyPage;