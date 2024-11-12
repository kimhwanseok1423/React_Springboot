import React, { useEffect, useState } from 'react';
import useCustomMove from '../../hooks/useCustomMove';
import { getList } from '../../api/productsApi';
import FetchingModal from '../common/FetchingModal';
import { API_SERVER_HOST } from '../../api/todoApi';
import PageComponent from '../common/PageComponent';
import useCustomLogin from '../../hooks/useCustomLogin';

const initState = {
    dtoList: [],
    pageNumList: [],
    pageRequestDTO: null,
    prev: false,
    next: true,
    totalCount: 0,
    prevPage: 0,
    nextPage: 0,
    totalPage: 0,
    current: 0,
};

const host = API_SERVER_HOST;

function ListComponent(props) {
    const { exceptionHandle } = useCustomLogin();
    const { moveToList, moveToRead, page, size, refresh } = useCustomMove();
    const [serverData, setServerData] = useState(initState);
    const [fetching, setFetching] = useState(false);

    useEffect(() => {
        setFetching(true);
        getList({ page, size })
            .then((data) => {
                setFetching(false);
                setServerData(data);
            })
            .catch((err) => exceptionHandle(err));
    }, [page, size, refresh]);

    return (
        <div className="container mx-auto px-4 py-8">
            {fetching ? <FetchingModal /> : null}
            
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                {serverData.dtoList.map((product) => (
                    <div
                        key={product.pno}
                        className="bg-white shadow-lg rounded-lg overflow-hidden hover:shadow-xl transition-shadow border border-gray-200 cursor-pointer"
                        onClick={() => moveToRead(product.pno)}
                    >
                        <div className="p-4 flex flex-col items-center">
                            <div className="font-extrabold text-lg text-gray-800">{product.pno}</div>
                            
                            <div className="my-2">
                                <img
                                    alt="product"
                                    className="rounded-md w-48 h-48 object-cover"
                                    src={`${host}/api/products/view/s_${product.uploadFileNames[0]}`}
                                />
                            </div>

                            <div className="text-center mt-2">
                                <div className="font-semibold text-gray-900">이름: {product.pname}</div>
                                <div className="text-gray-600">가격: {product.price} 원</div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>

            <PageComponent serverData={serverData} movePage={moveToList} />
        </div>
    );
}

export default ListComponent;
