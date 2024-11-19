현재 작업중 8/31 ~


## 목차
- [개요](#개요)
- [사용기술](#사용기술)
- [느낀 점](#느낀-점)
- [구현 기능](#구현기능)
- [ERD](#erd)
- [영상](#영상)

## 개요
API 서버와 프론트엔드 연계를 통해 데이터 흐름과 인증 방식(JWT, 카카오 로그인)을 깊이 이해할 수 있었습니다.
QueryDSL과 JPA를 활용해 복잡한 데이터 처리를 경험했고, React로 화면에 데이터를 효율적으로 렌더링하는 방법을 배웠습니다.
이번 프로젝트를 통해 백엔드와 프론트엔드 협업 프로세스에 대한 자신감을 얻을 수 있었습니다.



## 사용기술 
   Java , Spring Boot , Jpa ,  querydsl , React ,node.js ,MariaDB

## 느낀 점 
 생각보다 API서버에서의 절차가 굉장히 복잡했으나 React로 갈떄에는 DB값만 받아와서 화면에 뿌려
 주면 끝이라 좋앗던것같다?



- React-Router, Axios, 페이징 처리 , Querydsl 설정
  
- React 에서 프론트 작업후 axios로 DB값 받아오기
  - 결과 모달창 처리
  - 상품목록 컴포넌트 처리
  - 상품등록 수정 삭제 완료
  - 모든과정은 페이지를 만들고 -> 라우터에 가서 URL path 설정후 페이지링크 ->  ProdutApi 에서 async , axios를 활용하여 DB데이터 호출(인텔리제이 스프링부트 활용)
      -> product component 에서 페이지 상세적으로 꾸미기는 절차를 걸치도록 설정함
- 로그인 처리 - Redux Toolkit, JWT
- 소셜 로그인 처리 (카카오 accessToken 작업중)
- 
- 장바구니 구현
- React Query, Recoil
