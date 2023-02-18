<%@page import="dto.bbsDTO"%>
<%@page import="dao.Bbsdao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	int seq = Integer.parseInt(request.getParameter("seq"));
    
	Bbsdao dao = Bbsdao.getInstance();
	boolean isS = dao.deleteBbs(seq);
	if(isS) {
	%>
		<script>
		alert('삭제되었습니다.');
		location.href = "bbsList.jsp";
		</script>
	<%
	} else {
	%>
		<script>
		alert('삭제되지않았습니다.');
		let seq = "<%=seq %>";
		location.href = "bbsdetail.jsp?seq=" + seq;
		</script>
	<%
	}
	%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	
	</body>
</html>