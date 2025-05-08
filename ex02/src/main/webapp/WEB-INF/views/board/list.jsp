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
										<option value="">--</option>
										<option value="T">제목</option>
										<option value="C">내용</option>
										<option value="W">작성자</option>
										<option value="TC">제목 or 내용</option>
										<option value="TW">제목 or 작성자</option>
										<option value="TWC">제목 or 내용 or 작성자</option>
									</select>
									<input type="text" name="keyword">
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
                         
                         <form id="actionForm" action="/board/list" method="get">
                         	<input type="hidden" name="pageNum" value= "${pageMaker.cri.pageNum }">
                         	<input type="hidden" name="amount" value= "${pageMaker.cri.amount }">
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
		
	   checkModal(result);
	   
	   //브라우저의 현재 히스토리 항목을 새로운 상태로 대체.(history부분을 추가함으로써)
	   //페이지를 새로고침 하거나 뒤로가기 했을 때 모달창이 다시 표시되지 않도록함.
	   history.replaceState({}, null, null);
	   
	   function checkModal(result){
		   if(result === '' || history.state) return;//null값이면 리턴
		   
		   if(parseInt(result)>0){
			   $(".modal-body").html("게시글" + result + "번이 등록되었습니다.");
		   }else{
			   $(".modal-body").html("게시글" + result);
		   }
		   
		   $("#myModal").modal("show");
	   } //end checkModal
	   
	   $("#regBtn").on("click", function(){
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
	   });
	
	});
</script>













