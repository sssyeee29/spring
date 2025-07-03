<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>팔도마켓</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="/resources/dist/css/main.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <style>
    /* nav-bar를 flex 컨테이너로 만들어서 자식들을 가로 배치 */
    .nav-bar {
      display: flex;
      align-items: center;
      padding: 10px 20px;
      background-color: #fff; /* 필요에 따라 배경색 지정 */
    }

    /* 로고 영역과 메뉴 사이에 충분한 여백 주기 */
    .nav-logo {
      margin-right: 40px; /* 여기서 간격 조절 */
    }

    /* 기본 메뉴 스타일 - 가로 배치, 메뉴간 간격 */
    .nav-menu {
      display: flex;
      gap: 20px;
      /* 기본 폰트 스타일 조절 가능 */
    }

    /* 오른쪽 메뉴만 오른쪽 끝으로 밀기 */
    .nav-menu.right {
      margin-left: auto;
    }

    /* 링크 기본 스타일 */
    .nav-menu a {
      text-decoration: none;
      color: inherit;
      font-weight: 600;
    }

    .nav-menu a:hover {
      color: #007bff;
    }
  </style>
</head>
<body>
   <nav class="nav-bar">
        <div class="nav-logo">
          <a href="/" style="text-decoration: none; color: inherit;">
            <img src="/resources/images/KakaoTalk_20250518_163345571.png" alt="팔도마켓" class="logo-img" /> 팔도마켓
          </a>
        </div>
        <div class="nav-menu">
            <a href="/">홈</a>
            <a href="/board/list">게시판</a>
            <a href="/board/register">글쓰기</a>
            <a href="#">마이페이지</a>
        </div>
        
         <!-- 로그인/로그아웃 영역 (오른쪽 끝 정렬) -->
        <div class="nav-menu right">

            <sec:authorize access="isAnonymous()">
                <a href="/customLogin">로그인</a>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <a href="/logout">로그아웃</a>
                <span style="margin-left:10px;">
                    <sec:authentication property="principal.username" /> 
                </span>
            </sec:authorize>

        </div>
    </nav>
</body>
</html>