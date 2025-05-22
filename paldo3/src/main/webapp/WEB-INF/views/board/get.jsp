<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ include file="../includes/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/product-detail.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

<head>
  <meta charset="UTF-8">
  <title>팔도마켓 | 메인</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/resources/dist/css/main.css">
  <style>
    .logo-img {
      height: 38px;
      vertical-align: middle;
      margin-right: 10px;
      border-radius: 7px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.07);
    }
    #playButton {
      position: absolute;
      bottom: 10px;
      left: 50%;
      transform: translateX(-50%);
      padding: 10px 20px;
      font-size: 16px;
      background-color: #111;
      color: white;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      opacity: 0.85;
    }
  </style>
</head>

<div class="container py-4">
  <div class="row mb-4 align-items-left">
    <div class="col-lg-12 d-flex align-items-center justify-content-center">
      <c:choose>
        <c:when test="${product.brand eq '애플'}">
          <img src="/resources/images/apple_logo_icon_168588.webp" alt="Apple" style="height: 76px;">
        </c:when>
        <c:when test="${product.brand eq '삼성'}">
          <img src="/resources/images/Samsung_Logo.svg.webp" alt="Samsung" style="height: 62px;">
        </c:when>
        <c:when test="${product.brand eq '소니'}">
          <img src="/resources/images/sonylogo.png" alt="Sony" style="height: 90px;">
        </c:when>
        <c:when test="${product.brand eq 'LG'}">
          <img src="/resources/images/LG_logo_(2014).svg" alt="LG" style="height: 62px;">
        </c:when>
        <c:otherwise>
          <span class="text-muted">브랜드 없음</span>
        </c:otherwise>
      </c:choose>
    </div>
  </div>

  <div class="product-detail-container">
    <div class="product-images">
      <div class="main-image">
        <img src="<c:url value='/resources/${imgPaths.imgPath}'/>" alt="대표 이미지" />
      </div>
      <div class="thumb-list">
        <div class="thumb"><img src="<c:url value='${imgPaths.imgPath}'/>" alt="썸네일" /></div>
        <div class="thumb empty"></div>
        <div class="thumb empty"></div>
        <div class="thumb empty"></div>
      </div>
    </div>

    <div class="product-info">
      <nav class="breadcrumb">
        <a href="<c:url value='/'/>">HOME</a> &gt;
        <a href="<c:url value='/paldo/list'/>">구매하기</a> &gt;
        <span><c:out value="${board.board.title}"/></span>
      </nav>

      <div class="product-title">
        <c:out value="${board.board.title}"/>
      </div>
      <div class="model">
        <c:out value="${board.product.product}"/>
      </div>

      <div class="mb-3">
        <span class="sale-price">
          <fmt:formatNumber value="${board.product.price}" pattern="#,###"/>원
        </span>
        <span class="reg-date">
          등록일: <fmt:formatDate value="${board.board.regDate}" pattern="yyyy-MM-dd"/>
        </span>
      </div>

      <div>
        <span class="option active">${board.product.condition}</span>
        <span class="grade active">${board.board.status}</span>
      </div>

      <div class="status-message">
        판매상태: ${board.board.status}
      </div>

      <div class="brand">
        브랜드: ${board.product.brand}
      </div>

      <div style="margin-bottom:15px; font-size:15px;">
        판매자: ${board.user.nickname}
      </div>

      <form class="purchase-form" onsubmit="return false;">
        <div class="purchase-row">
          <label for="quantity">구매 수량</label>
          <input type="text" id="quantity" class="qty" value="1" readonly>
          <span class="sum-price">
            총 금액: <fmt:formatNumber value="${board.product.price}" pattern="#,###"/>원
          </span>
        </div>
        <button type="button" class="btn-blue btn-purchase" disabled>즉시 구매하기</button>
      </form>

      <div class="product-description">
        <p><c:out value="${board.product.description}"/></p>
      </div>

      <div class="d-flex justify-content-end gap-2 mt-4">
        <button data-oper='modify' class="btn btn-warning">수정</button>
        <button data-oper='list' class="btn btn-outline-secondary">목록</button>
      </div>

      <form id="operForm" action="/board/modify" method="get">
        <input type="hidden" name="boardid" value='<c:out value="${board.board.boardid}"/>'>
        <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
        <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
        <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
        <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
      </form>
    </div>
  </div>
</div>

<!-- 댓글 영역 및 모달 그대로 유지 -->
<!-- 이하 생략된 스크립트 및 푸터는 변경 없이 유지 -->
