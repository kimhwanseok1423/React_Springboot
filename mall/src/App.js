import { RouterProvider } from "react-router-dom";
import root from "./router/root";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

//전체 상태관리를 함으로 자기 메모리 공간이 필요한 react query


const queryClient=new QueryClient()


//개발자도구툴 
function App() {
  return (
 <QueryClientProvider client={queryClient}>
 <RouterProvider router={root}/>

 <ReactQueryDevtools initialIsOpen={true}></ReactQueryDevtools> 
 
 </QueryClientProvider>
  );
}

export default App;
