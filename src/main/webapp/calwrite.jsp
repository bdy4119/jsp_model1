<%@page import="util.Calendarutil"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	MemberDto login = (MemberDto)session.getAttribute("login");
	if(login == null){
	%>
	<script type="text/javascript">
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
	
	//한자리 -> 두자리로 만들기
	month = Calendarutil.two(month);
	day = Calendarutil.two(day);
	%>
	
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>일정추가</h2>
		<div align="center">
			<form action="calwriteAf.jsp" method="post">
				<table border="1">
					<col width="200">
					<col width="500">
					<tr>
						<th>아이디</th>
						<td>
							<%=login.getId() %>
							<input type="hidden" name="id" value="<%=login.getId() %>">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" size="80" name="title">
						</td>
					</tr>
					<tr>
						<th>일정</th>
						<td>
							<input type="date" name="date" id="date">&nbsp;
							<input type="time" name="time" id="time">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="20" cols="80" name="content"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="일정추가">
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<script type="text/javascript">
			let year = "<%=year %>";
			let month = "<%=month %>";
			let day = "<%=day %>";
			//값넣기
			document.getElementById("date").value = year + "-" + month + "-" + day;
			
			let date = new Date();
			document.getElementById("time").value = date.getHours() + ":" + date.getMinutes();

		</script>
	</body>
</html>