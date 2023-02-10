<%@page import="dto.bbsDTO"%>
<%@page import="java.util.List"%>
<%@page import="dao.Bbsdao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberDto login = (MemberDto)session.getAttribute("login");
	if(login == null){
	%>
	<script>
		alert('로그인 해 주십시오');
		location.href = "login.jsp";
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
		<%
		Bbsdao dao = Bbsdao.getInstance();
		
		List<bbsDTO> list = dao.getBbsList();
		%>
	
		<h1>게시판</h1>
		<div align="center">
			<table border="1">
				<col width="70">
				<col width="600">
				<col width="100">
				<col width="150">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>조회수</th>
						<th>작성자</th>
					</tr>
				</thead>
				<tbody>
					<%
					if(list == null || list.size() == 0) {
						%>
						<tr>
							<td colspan="4">작성된 글이 없습니다.</td>
						</tr>
						<%
					} else {
						for(int i = 0; i < list.size(); i++) {
							bbsDTO dto = list.get(i);
						%>
						<tr>
							<th><%=i+1 %></th>
							<td><%=dto.getTitle() %></td>
							<td><%=dto.getReadcount() %></td>
							<td><%=dto.getId()%></td>
						</tr>
						<%
						}
					}
					%>
				</tbody>
			</table>
			<br>
			<a href="bbswrite.jsp">글쓰기</a>
		</div>
	<script type="text/javascript"></script>
	</body>
</html>