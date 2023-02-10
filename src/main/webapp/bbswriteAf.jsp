<%@page import="dto.bbsDTO"%>
<%@page import="dao.Bbsdao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 	request.setCharacterEncoding("utf-8");	
 
 	 String id = request.getParameter("id");
 	 String title = request.getParameter("title");
 	 String content = request.getParameter("content");
 	 
 	 Bbsdao dao = Bbsdao.getInstance();
 	 
 	 boolean isS = dao.writeBbs(new bbsDTO(id, title, content));
 	 if(isS == true) { 		 
		 %>
		<script type="text/javascript">
			alert("추가되었습니다.");
			location.href = "bbsList.jsp";
		</script>
	 	<%
 	 } else {
	 	%>
		<script type="text/javascript">
			alert("다시 작성해주세요");
			location.href = "bbswrite.jsp";
		</script>
	 	<%
 	 }
 	%>