현재 작업완료후 READ ME 수정중 


## 목차
- [개요](#개요)
- [사용기술](#사용기술)
- [느낀 점](#느낀-점)
- [구현 기능](#구현기능)
- [ERD](#erd)
- [영상](#영상)

## 개요
이 프로젝트는 Spring Boot 기반의 API 서버와 React 프론트엔드를 연계하여<br>  CRUD와 페이징 처리, 인증 및 권한 관리를 구현한 웹 애플리케이션입니다. <br>
JWT 인증을 활용한 사용자 로그인 및 카카오 로그인 연동을 통해 OAuth 인증 과정을 경험했습니다.<br>
또한, Axios를 사용해 비동기 데이터 처리를 구현하고 React를 통해 사용자에게<br> 데이터를 직관적으로 표시하며 프론트엔드 기술 역량을 강화하고자 했습니다. <br> 

## 사용기술 
   Java , Spring Boot , Jpa ,  querydsl , React ,node.js ,MariaDB

## 느낀 점 
API 서버와 프론트엔드 연계를 통해 데이터 흐름과 인증 방식(JWT, 카카오 로그인)을 깊이 이해할 수 있었습니다. <br>
QueryDSL과 JPA를 활용해 복잡한 데이터 처리를 경험했고, React로 화면에 데이터를 효율적으로 렌더링하는 방법을 배웠습니다. <br>
이번 프로젝트를 통해 백엔드와 프론트엔드 협업 프로세스에 대한 자신감을 얻을 수 있었습니다. <br>



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
