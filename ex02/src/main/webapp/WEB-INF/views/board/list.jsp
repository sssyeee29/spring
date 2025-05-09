<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ include file="../includes/header.jsp" %> <!-- header 임포트 시키기 -->

         <div class="row">
             <div class="col-lg-12">
                 <h1 class="page-header">Tables</h1>
             </div>
             <!-- /.col-lg-12 -->
         </div>
         <!-- /.row -->
         <div class="row">
             <div class="col-lg-12">
                 <div class="panel panel-default">
                     <div class="panel-heading">
                        	Board List Page
                        	<button id="regBtn" type="button" class="btn btn-xs btn btn-info pull-right">새글 등록</button>
                     </div>
                     <!-- /.panel-heading -->
                     <div class="panel-body">
                         <table width="100%" class="table table-striped table-bordered table-hover">
                             <thead>
                                 <tr>
                                     <th>#번호</th>
                                     <th>제목</th>
                                     <th>작성자</th>
                                     <th>작성일</th>
                                     <th>수정일</th>
                                 </tr>
                             </thead>
                             
                             <c:forEach var="board" items="${list }">
                             	<tr><!-- el태그 대신 씨아웃 사용하는 이유는 XSS(링크)를 방어하기 위해  -->
                             		<td><c:out value="${board.bno }" /></td>
                             		
                             		<%-- <td><a href='/board/get?bno=${board.bno' }>${board.title}></a></td> --%>
                             		
                             		<!-- 상세페이지 이동 -->
                             		<td><a class='move' href='<c:out value="${board.bno }"/>'>
                             		<c:out value="${board.title }" /></a></td>
                             		
                             		<td><c:out value="${board.writer }" /></td>
                             		
                             		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate }"/></td>
                             		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }"/></td>
                             	</tr>
                             </c:forEach>
                         </table>
                         
                         <!-- 검색조건 -->
						<div class="row">
							<div class="col-lg-12"> <!-- 12등분 -->
								<form action="/board/list" id="searchForm">
									<select name="type">
										<option value="" 
											<c:out value="${pageMaker.cri.type == null ? 'selected' : '' }" />>--</option>
										<option value="T"
											<c:out value="${pageMaker.cri.type == 'T' ? 'selected' : '' }" />>제목</option>
										<option value="C"
											<c:out value="${pageMaker.cri.type == 'C' ? 'selected' : '' }" />>내용</option>
										<option value="W"
											<c:out value="${pageMaker.cri.type == 'W' ? 'selected' : '' }" />>작성자</option>
										<option value="TC"
											<c:out value="${pageMaker.cri.type == 'TC' ? 'selected' : '' }" />>제목 or 내용</option>
										<option value="TW"
											<c:out value="${pageMaker.cri.type == 'TW' ? 'selected' : '' }" />>제목 or 작성자</option>
										<option value="TWC"
											<c:out value="${pageMaker.cri.type == 'TWC' ? 'selected' : '' }" />>제목 or 내용 or 작성자</option>
									</select>
									<input type="text" name="keyword"
										value=<c:out value="${pageMaker.cri.keyword}" /> >
									<input type="hidden" name="pageNum" value='${pageMaker.cri.pageNum }'>
									<input type="hidden" name="amount" value='${pageMaker.cri.amount }'>
									<button class='btn btn-default'>검색</button>
								</form>
							</div>
						
						</div>
                         <!-- end 검색조건 -->
                         
                         
                         <!-- 페이징 처리 -->
                         <div class="container">
							<div class="pull-right">
							  <ul class="pagination">
							   
								<c:if test="${pageMaker.prev }">							   
							    	<li class="page-item"><a class="page-link" 
							    			href="${pageMaker.startPage-1 }">이전</a></li>
							    </c:if>
							   
							   	<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
							   	 	<li class="page-item ${pageMaker.cri.pageNum == num ? "active" : ""}">
							   	 		<a class="page-link" href="${num }">${num }</a></li>
							    </c:forEach>
							    <c:if test="${pageMaker.next }">	
							   	 	<li class="page-item"><a class="page-link" 
							   	 			href="${pageMaker.endPage+1 }">다음</a></li>
							 	</c:if>
							  </ul>
							</div>
						 </div>
                         <!-- 페이징 종료  -->
                         
                         <!-- 페이지 조회한 값을 다른 페이지 이동시에도 유지해야함 -->
                         <form id="actionForm" action="/board/list" method="get">
                         	<input type="hidden" name="pageNum" value= "${pageMaker.cri.pageNum }">
                         	<input type="hidden" name="amount" value= "${pageMaker.cri.amount }">
                        	<input type="hidden" name="keyword"
										value=<c:out value="${pageMaker.cri.keyword}" /> >
                        	<input type="hidden" name="type"
										value=<c:out value="${pageMaker.cri.type}" /> >
                         </form>
                         
                     </div>
                     <!-- /.panel-body -->
                 </div>
                 <!-- /.panel -->
             </div>
             <!-- /.col-lg-6 -->
         </div>
         <!-- /.row -->
  
    <!-- The Modal -->
	  <div class="modal" id="myModal">
	    <div class="modal-dialog">
	      <div class="modal-content">
	      
	        <!-- Modal Header -->
	        <div class="modal-header">
	          <h4 class="modal-title">Modal Title</h4>
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        
	        <!-- Modal body -->
	        <div class="modal-body">
	         ${result}
	        </div>
	        
	        <!-- Modal footer -->
	        <div class="modal-footer">
	          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	          <button type="button" class="btn btn-primary">Save Changes</button>
	        </div>
	        
	      </div>
	    </div>
	  </div> <!-- end The Modal -->
   
     
<%@ include file="../includes/footer.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
	   var result = '<c:out value="${result}"/>';
		
	   checkModal(result); //위에서 받은 결과값을 checkModal 함수에 넘겨서 모달창의 유무를 결정하는거
	   
	   //브라우저의 현재 히스토리 항목을 새로운 상태로 대체.(history부분을 추가함으로써)
	   //페이지를 새로고침 하거나 뒤로가기 했을 때 모달창이 다시 표시되지 않도록함.
	   history.replaceState({}, null, null);
	   
	   function checkModal(result){
		   if(result === '' || history.state) return;//null값이면 리턴 // result가 없거나, 이미 상태가 바뀐 페이지라면 아무것도안함 
		   
		   if(parseInt(result)>0){ //result가 숫자일때 -> 게시글을 쓰면 1번부터 번호가 생기니까 0보다 커서 실행됨
			   $(".modal-body").html("게시글" + result + "번이 등록되었습니다.");
		   }else{
			   $(".modal-body").html("게시글" + result);
		   }
		   
		   $("#myModal").modal("show"); // 모달창을 실제로 띄우는 부분 
	   } //end checkModal
	   
	   $("#regBtn").on("click", function(){ //글쓰기 버튼을 클릭하면 /board/register로 이동 
		   self.location = "/board/register";
	   });
	   
	   //페이지 번호 이벤트 처리 
	   let actionForm = $("#actionForm");
	   $(".page-item a").on("click", function(e){
		   e.preventDefault();
		   console.log('click');
		   actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		   actionForm.submit();
	   }); //end 페이지 번호 이벤트 처리
	   
	   //상세페이지 이동시 pageNum, amount값 전달 
	   $(".move").on("click", function(e){
		  
		   e.preventDefault(); //전파막기 
		   
		   actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href")+"'>");
		   actionForm.attr("action", "/board/get");
		   actionForm.submit();
	   }); //end 상세페이지 이동시 pageNum, amount값 전달 
	   
	   //검색 버튼 이벤트 처리 
	   let searchForm = $("#searchForm");
	   
	   $("#searchForm button").on("click", function(e){
		
		  if(!searchForm.find("option:selected").val()){
			 alert("검색 종류를 선택하세요");
			  return false;
		  } 

		  if(!searchForm.find("input[name='keyword']").val()){
			 alert("키워드를 입력하세요");
			  return false;
		  } 
		  
		  //검색 조건 필터링 된 상태에서 1페이지로 이동 => 반드시 넣어줘야함
		  searchForm.find("input[name='pageNum']").val("1"); //pageNum을 1로바꿔라 
		 													//ex)7페이지에서 검색누르면 그 해당 검색내용들을 1페이지부터 보여줌 
		  e.preventDefault(); //검색버튼 누르면 /board/list로 이동하는걸 막아줌 
		  
		  searchForm.submit();
		   
	   });
	
	});
</script>
































