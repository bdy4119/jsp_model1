<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	MemberDao dao = MemberDao.getInstance();
	
	MemberDto mem = dao.login(id, pwd);
	
	if(mem != null) {
		//로그인 회원정보
		//session 얻어오기
		//request.getSession().setAttribute(name, value);
		session.setAttribute("login", mem);
	//	session.setMaxInactiveInterval(60 * 60 * 2); //session의 유효기간 -> 2시간
%>
	<script type="text/javascript">
		alert("환영합니다. <%=mem.getId()%>님");
	//	location.href="";
	</script>
	<%
	} else{
	%>
	<script type="text/javascript">
		alert("아이디나 패스워드를 확인해주세요.");
		location.href = "login.jsp";
	</script>
	<%
	}
	%>
	
	
	

