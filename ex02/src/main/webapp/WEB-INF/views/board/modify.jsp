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
                       	Board Read Page
                    </div>
                   <!-- /.panel-heading -->
                   <div class="panel-body">
                      <form role="form" action="/board/modify" method="post">
                      		<input type="hidden" name="pageNum" value= '<c:out value="${cri.pageNum}"/>'>
                         	<input type="hidden" name="amount" value= '<c:out value="${cri.amount}"/>'>
                         	<input type="hidden" name="keyword" value= '<c:out value="${cri.keyword}"/>'>
                         	<input type="hidden" name="type" value= '<c:out value="${cri.type}"/>'>
                      			
                       
                       	<div class="form-group">
                       		<label>Bno</label><input class="form-control" name="bno"
                       							value="<c:out value='${board.bno }'/>" readonly="readonly">
                       	</div>
                       
                       	<div class="form-group">
                       		<label>Title</label><input class="form-control" name="title"
                       							value="<c:out value='${board.title }'/>">
                       	</div>
                       	
                       	<div class="form-group">
                       		<label>Text area</label>
                       		<textarea rows="3" class="form-control" name="content">
                       			<c:out value='${board.content }'/>
                       		</textarea>
                      	</div>
                      		
                      	<div class="form-group">
                      			<label>Writer</label><input class="form-control" name="writer"
                      								value="<c:out value='${board.writer }'/>">
                      	</div>
                      		
                      	<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
                      	<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
                      	<button type="submit" data-oper='list' class="btn btn-info">List</button>
                       </form>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-6 -->
        </div>
        <!-- /.row -->

<script type="text/javascript">
$(document).ready(function(){
	let formObj = $("form"); //위에 role부분에 있는 form
	
	$('button').on("click", function(e){
		e.preventDefault(); // 이벤트의 기본 동작을 막는 역할 - Remove 버튼을 누르면 remove로 이동해야하니까 위에 22번 줄에 action이 실행되는걸 막아줌
		
		let operation = $(this).data("oper");
		
		if(operation === 'remove'){
			
			formObj.attr("action", "/board/remove")
			
		}else if(operation === 'list'){
			
			formObj.attr("action", "/board/list").attr("method", "get");
			let pageNumTag = $("input[name='pageNum']").clone();
			let amountTag = $("input[name='amount']").clone();
			let typeTag = $("input[name='type']").clone();
			let keywordTag = $("input[name='keyword']").clone();
			
			formObj.empty();//input태그 name속성값을 클리어 //이걸 씀으로써 다시 list로 돌아갈때 url창에 내용들이 안뜸

			formObj.append(pageNumTag); //append해서 ()안의 값을 추가함 
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);
			
		}
		formObj.submit(); //위에 22번째줄에 있는 action으로 돌아감 
	});
});


</script>
     
<%@ include file="../includes/footer.jsp" %>