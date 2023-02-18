<%@page import="dao.CalendarDao"%>
<%@page import="dao.Bbsdao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int seq = Integer.parseInt(request.getParameter("seq"));
    
	CalendarDao dao = CalendarDao.getInstance();	//싱글톤 받아오기

	boolean isS = dao.calDelete(seq);
	if(isS) {
	%>
		<script>
		alert('삭제되었습니다.');
		location.href = "calendar.jsp";
		</script>
	<%
	} else {
	%>
		<script>
		alert('삭제되지않았습니다.');
		location.href = "caldetail.jsp?seq=" + "<%=seq %>";
		</script>
	<%
	}
	%>