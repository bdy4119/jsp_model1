<%@page import="util.Calendarutil"%>
<%@page import="java.util.List"%>
<%@page import="dto.CalendarDto"%>
<%@page import="dao.CalendarDao"%>
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
	
	<%
	String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
    
    String yyyymmdd = year + Calendarutil.two(month + "") + Calendarutil.two(day + "");
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
	
	List<CalendarDto> list = dao.getDayList(login.getId(), yyyymmdd);
	%>
		<h1><%=year %>년 <%=month %>월 <%=day %>일 일정</h1>
		<hr>
		<br>
		
		<div align="center">
			<table border="1">
				<col width="100">
				<col width="450">
				<col width="300">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>시간</th>
					</tr>
					<%
					if(list == null || list.size() == 0) {
						%>
						<tr>
							<td colspan="4" align="center">일정이 없습니다.</td>
						</tr>
						<%
					} else {
						for(int i = 0;i < list.size(); i++){
							CalendarDto dto = list.get(i);
							%>
							<tr>
								<th><%=i+1 %></th>
								<td>
									<a href="caldetail.jsp?seq=<%=dto.getSeq() %>">
										<%=dto.getTitle() %>
									</a>
								</td>
								<td><%=Calendarutil.toDates(dto.getRdate()) %></td>
							</tr>	
							<%
						}
					}
					%>
			</table>
			<br>
			<br>
			<button type="button" onclick="location.href='calendar.jsp'">일정관리</button>
		</div>
	</body>
</html>