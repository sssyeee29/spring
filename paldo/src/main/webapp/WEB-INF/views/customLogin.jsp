<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="ko"> <%-- lang="en"을 lang="ko"로 변경 --%>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>팔도마켓 로그인</title> <%-- 타이틀 한글로 변경 --%>

    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">

    <style>
        /* 메인 페이지와 유사한 배경 그라데이션 및 전체 높이 설정 */
        html {
            height: 100%; /* html 높이 100% 설정 */
        }

        body {
            height: 100vh; /* 뷰포트 높이 100% */
            margin: 0;
            background: linear-gradient(135deg, #e0e7ff, #d0f0f7); /* 메인 페이지 배경 그라데이션 */
            display: flex;
            justify-content: center; /* 가로 중앙 정렬 */
            align-items: center;   /* 세로 중앙 정렬 */
            font-family: 'Noto Sans KR', sans-serif; /* 한국어 폰트 */
            overflow: hidden; /* 스크롤바가 생기지 않도록 방지 */
        }

        /* 기존 Bootstrap container/row/col-md-4 구조를 유지하면서 중앙 정렬에 영향을 줍니다.
           따라서 이를 직접 .login-panel에 적용하는 방식으로 변경합니다. */
        .container, .row, .col-md-4 {
            /* 이들 요소를 플렉스 컨테이너의 자식으로 간주되지 않도록 제거하거나,
               아래 login-panel이 직접 body의 자식이 되도록 HTML 구조를 변경하는 것이 좋습니다.
               지금은 가장 간단하게 HTML 구조를 유지하면서 CSS만 덮어씁니다. */
            width: auto; /* 부트스트랩 기본 너비 재정의 */
            margin: 0;   /* 부트스트랩 기본 마진 재정의 */
            padding: 0;  /* 부트스트랩 기본 패딩 재정의 */
            flex-grow: 0; /* 플렉스 아이템이 되지 않도록 설정 */
            flex-shrink: 0;
            float: none; /* float 제거 */
        }
        .col-md-offset-4 {
            margin-left: auto; /* offset-4 효과 제거 및 중앙 정렬 */
            margin-right: auto;
        }


        /* 로그인 패널 스타일 (메인 페이지 카드 디자인 유사하게) */
        .login-panel {
            background: white;
            border-radius: 16px; /* 더 둥근 모서리 */
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1); /* 더 부드러운 그림자 */
            padding: 40px 30px; /* 내부 패딩 */
            width: 360px; /* 고정 너비 */
            text-align: center;
            border: none; /* 부트스트랩 panel의 기본 테두리 제거 */
            /* transform: translateY()는 제거합니다. 중앙에 위치합니다. */
        }

        .panel-heading {
            background-color: transparent !important; /* 배경색 투명하게 */
            border-bottom: none !important; /* 하단 테두리 제거 */
            padding-bottom: 0 !important; /* 하단 패딩 제거 */
        }

        .panel-title {
            margin-bottom: 30px; /* 제목과 입력 필드 사이 간격 */
            font-weight: 700; /* 굵은 글씨 */
            color: #1a237e; /* 진한 파란색 (메인 페이지와 유사) */
            font-size: 24px; /* 제목 크기 조절 */
        }

        .form-control {
            border-radius: 12px; /* 둥근 입력 필드 */
            border: 1px solid #ccc;
            padding: 12px 15px;
            font-size: 16px;
            margin-bottom: 20px; /* 각 필드 아래 간격 */
            box-shadow: none; /* 부트스트랩 기본 box-shadow 제거 */
        }
        .form-control:focus {
            border-color: #80bdff; /* 포커스 시 색상 조절 */
            outline: 0;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25); /* 포커스 시 그림자 조절 */
        }

        .checkbox label {
            font-size: 14px;
            color: #555;
            text-align: left; /* 텍스트 왼쪽 정렬 */
            display: block; /* label 전체 영역 클릭 가능하도록 */
            margin-bottom: 20px; /* 체크박스 아래 간격 */
        }

        /* 로그인 버튼 스타일 (메인 페이지의 버튼과 유사하게) */
        .btn-success { /* 기존 btn-success 대신 이 클래스 사용 */
            background: #3f51b5; /* 메인 색상 계열의 파란색 */
            color: white;
            border: none;
            border-radius: 12px; /* 둥근 버튼 */
            padding: 12px 0;
            font-weight: 600;
            font-size: 18px;
            width: 100%; /* 너비 100% */
            cursor: pointer;
            transition: background 0.3s ease; /* 호버 효과 */
        }

        .btn-success:hover {
            background: #283593; /* 호버 시 더 진한 파란색 */
        }

        /* 에러/로그아웃 메시지 스타일 */
        .alert {
            font-size: 14px;
            margin-bottom: 15px;
            border-radius: 8px; /* 알림창도 둥글게 */
        }
    </style>

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">팔도마켓 로그인</h3> <%-- 타이틀 한글로 변경 --%>
                    </div>
                    <div class="panel-body">
                        <form role="form" method="post" action="/customLogin"> <%-- action을 /customLogin으로 변경 --%>
                            <fieldset>
                                <%-- 로그인 에러 메시지 표시 --%>
                                <c:if test="${error != null}">
                                    <div class="alert alert-danger" role="alert" style="font-size: 14px; margin-bottom: 15px;">
                                        ${error}
                                    </div>
                                </c:if>
                                <%-- 로그아웃 메시지 표시 --%>
                                <c:if test="${logout != null}">
                                    <div class="alert alert-success" role="alert" style="font-size: 14px; margin-bottom: 15px;">
                                        ${logout}
                                    </div>
                                </c:if>

                                <div class="form-group">
                                    <input class="form-control" placeholder="아이디" name="username" type="text" autofocus> <%-- placeholder 변경 --%>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="비밀번호" name="password" type="password"> <%-- placeholder 변경 --%>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember-me" type="checkbox"> 로그인 유지 <%-- 텍스트 한글로 변경 --%>
                                    </label>
                                </div>
                                <%-- 로그인 버튼: <button type="submit">으로 변경하여 폼 제출 --%>
                                <button type="submit" class="btn btn-lg btn-success btn-block">로그인</button> <%-- button 태그로 변경 --%>
                            </fieldset>
                            
                            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="/resources/vendor/jquery/jquery.min.js"></script>

    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <script src="/resources/dist/js/sb-admin-2.js"></script>
	
	<script type="text/javascript">
		$(".btn-success").on("click", function(e){
			e.preventDefault(); // 기본 폼 제출을 막음
			$("form").submit(); // 폼을 수동으로 제출
		});

        // CommonController에서 넘어오는 logout 파라미터를 처리하는 부분
        // Spring Security의 logoutSuccessUrl과 연동됨
        <c:if test="${param.logout != null}">
            $(document).ready(function(){
                // 이미 CommonController에서 model.addAttribute("logout", "Logout!!"); 처리하므로
                // 별도의 alert은 생략하거나 메시지를 조절할 수 있습니다.
                // alert("로그아웃되었습니다."); 
            });
        </c:if>

        // CommonController에서 넘어오는 error 파라미터를 처리하는 부분
        // Spring Security의 failureUrl과 연동됨
        <c:if test="${param.error != null}">
            $(document).ready(function(){
                // 이미 CommonController에서 model.addAttribute("error", "Login Error Check Your Account"); 처리하므로
                // 별도의 alert은 생략하거나 메시지를 조절할 수 있습니다.
                // alert("로그인 실패: 아이디 또는 비밀번호를 확인해주세요.");
            });
        </c:if>
	</script>
	
</body>
</html>