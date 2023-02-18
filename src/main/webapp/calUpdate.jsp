<%@page import="dto.CalendarDto"%>
<%@page import="dao.CalendarDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
int seq = Integer.parseInt( request.getParameter("seq") );
%>

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
	CalendarDao dao = CalendarDao.getInstance();
	CalendarDto dto = dao.getCalendar(seq);
	
	//date
	String year = dto.getRdate().substring(0,4);
	String month = dto.getRdate().substring(4,6);
	String day = dto.getRdate().substring(6,8);
	String date = year + "-" + month + "-" + day;
	//time
	String hour = dto.getRdate().substring(8,10);
	String min = dto.getRdate().substring(10);
	String time = hour + ":" + min;
	%>
	<h2>일정수정</h2>
		<div align="center">
			<form action="calUpdateAf.jsp" method="post">
				<table border="1">
				<col width="200"><col width="500">
					<tr>
						<th>아이디</th>
						<td>
							<%=dto.getId() %>
							<input type="hidden" name="seq" value="<%=dto.getSeq() %>">	<!-- 업데이트되면 보내줘야하므로 -->
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="title" size="50" value="<%=dto.getTitle()%>">
						</td>	
					</tr>
					<tr>
						<th>일정</th>
						<td>
							<input type="date" name="date" value="<%=date %>">&nbsp;
							<input type="time" name="time" value="<%=time %>">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="20" cols="60" name="content"><%=dto.getContent() %></textarea>
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