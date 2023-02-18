<%@page import="dto.CalendarDto"%>
<%@page import="dao.CalendarDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

int seq = Integer.parseInt(request.getParameter("seq"));
String title = request.getParameter("title");
String content = request.getParameter("content");

String date = request.getParameter("date");
String time = request.getParameter("time");

String datesplit[] = date.split("-");
String timesplit[] = time.split(":");

String rdate = datesplit[0] + datesplit[1] + datesplit[2] + timesplit[0] + timesplit[1];

CalendarDao dao = CalendarDao.getInstance();
boolean isS = dao.calUpdate(new CalendarDto(seq, null, title, content, rdate, null));
if(isS){
	%>    
	<script type="text/javascript">
	alert('성공적으로 수정되었습니다');
	location.href = "calendar.jsp";
	</script>
	<%
}else{	
	%>
	<script type="text/javascript">
	alert('수정되지 않았습니다');
	location.href = "calupdate.jsp?seq=" + "<%=seq %>";
	</script>
	<%
}	
%>