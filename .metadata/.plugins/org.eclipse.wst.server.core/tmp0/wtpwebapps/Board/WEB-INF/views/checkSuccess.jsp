<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		if(window.name == "update"){
			window.opener.parent.location.href="/board/update?num=${num}";
				
		}else if(window.name == "delete"){ //boardView에 게시글 삭제에 있는 delete가 여기로 전달된거임 
			alert("삭제되었습니다.");
			window.opener.parent.location.href="/board/delete?num=${num}";
		}
		window.close();
	</script>
</body>
</html>