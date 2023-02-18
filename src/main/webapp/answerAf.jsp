<%@page import="dto.bbsDTO"%>
<%@page import="dao.Bbsdao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
		request.setCharacterEncoding("utf-8");
	
		int seq = Integer.parseInt(request.getParameter("seq"));
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Bbsdao dao = Bbsdao.getInstance();
		
		boolean isS = dao.answer(seq, new bbsDTO(id, title, content));
		if(isS) {
		%>
		<script type="text/javascript">
			alert("답글입력 성공!");
			location.href = "bbsList.jsp";
		</script>
		<%
			} else {
		%>
		<script type="text/javascript">
			alert("답글입력 실패");
			location.href = "bbsList.jsp";
		</script>
		<%
			}
		%>