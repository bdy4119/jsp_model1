<%@page import="java.util.List"%>
<%@page import="dao.CalendarDao"%>
<%@page import="dto.CalendarDto"%>
<%@page import="util.Calendarutil"%>
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
		//검색
		String choice = request.getParameter("choice");
		String search = request.getParameter("search");
		
		if(choice == null) {
			choice = "";
		}
		if(search == null) {
			search = "";
		}
		%>
		<%
		CalendarDao dao = CalendarDao.getInstance();
		
		List<CalendarDto> list = dao.getCalSearchList(choice, search);
		%>
		<h1><%=search %>로 검색된 일정</h1>
		<hr>
		<br>
		<div align="center">
			<select id="choice">
				<option value="">검색</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
					
			<input type="text" id="search" value="<%=search %>">
					
			<button type="button" onclick="searchBtn()">검색</button>	
		</div>
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
						<td colspan="4" align="center">검색된 일정이 없습니다.</td>
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
		<script type="text/javascript">
			//자바용 변수 -> 스크립트로 넘겨받기
			let search = "<%=search %>";
			if(search != "") {
				let obj = document.getElementById("choice");
				obj.value = "<%=choice %>"; 
				
				//값을 넣어주면서 바꿔줘라
				obj.setAttribute("selected", selected); 
			}
			
			function searchBtn() {
				let choice = document.getElementById('choice').value;
				let search = document.getElementById('search').value;
				
				location.href = "searchlist.jsp?choice=" + choice + "&search=" + search;
			}
		</script>
	</body>
</html>