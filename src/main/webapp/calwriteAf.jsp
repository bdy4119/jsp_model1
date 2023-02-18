<%@page import="dto.CalendarDto"%>
<%@page import="dao.CalendarDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	String title = request.getParameter("title");
	String content = request.getParameter("content");

	//연 월 시 분 나눠야됨
	String date = request.getParameter("date");
	String time = request.getParameter("time");
	System.out.println("time:" + time);

	//날짜자르기
	String datesplit[] = date.split("-");
	String year = datesplit[0];
	String month = datesplit[1];
	String day = datesplit[2];
	
	//시간 자르기
	String timesplit[] = time.split(":");
	String hour = timesplit[0];
	String min = timesplit[1];
	
	String rdate = year + month + day + hour + min ; //202302161518
	System.out.println(rdate);
	
	CalendarDao dao = CalendarDao.getInstance();
	boolean isS = dao.addCalendar(new CalendarDto(id, title, content, rdate));
	if(isS) {
		%>
		<script type="text/javascript">
		alert("일정이 추가되었습니다.");
		location.href="calendar.jsp";
		</script>
		<%
	} else {
		%>
		<script type="text/javascript">
		alert("일정이 추가되지 않았습니다.");
		location.href="calwrite.jsp?year=" + year + "&month=" + month +"&day=" + day;
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
	<script type="text/javascript"></script>
	</body>
</html>