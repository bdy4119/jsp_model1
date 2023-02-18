<%@page import="dto.MemberDto"%>
<%@page import="dao.Bbsdao"%>
<%@page import="dto.bbsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
int seq = Integer.parseInt( request.getParameter("seq") );
%>
 
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
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
		Bbsdao dao = Bbsdao.getInstance(); //싱글톤 가져오기
		bbsDTO dto = dao.getBbs(seq); //다오에서 얻은값 DTO에 저장
		%>
		<h2>수정</h2>
		<div align="center">
			<form action="bbsupdateAf.jsp" method="post">
				<input type="hidden" name="seq" value="<%=dto.getSeq() %>"> <!-- seq보내기 -->
				
				<table border="1">
				<col width="200"><col width="500">
					<tr>
						<th>아이디</th>
						<td><%=dto.getId() %></td>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="title" size="50" value="<%=dto.getTitle()%>">
						</td>	
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="10" cols="50" name="content"><%=dto.getContent()%></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="수정완료">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>